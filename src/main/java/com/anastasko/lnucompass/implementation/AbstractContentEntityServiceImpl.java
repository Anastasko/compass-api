package com.anastasko.lnucompass.implementation;

import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.view.FindModifiedArgs;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public abstract class AbstractContentEntityServiceImpl<T extends AbstractContentEntity>
        extends AbstractEntityPersistenceServiceImpl<T>
        implements ContentEntityService<T> {

    @Override
    @Transactional(readOnly = true)
    public List<T> find(FindModifiedArgs args) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(this.getEntityClass());
        Root<T> root = query.from(getEntityClass());
        Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.join("properties").get("modified"), new Date(args.getAfterDate()));
        if (args.getOwners() != null && args.getOwners().size() > 0){
            predicate = criteriaBuilder.and(predicate, root.get("owner").get("id").in(args.getOwners()));
        }
        query = query.select(root).where(predicate);
        TypedQuery<T> typedQuery = getEntityManager().createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public void create(T item){
        Date now = new Date();
        item.getProperties().setType(getEntityTypeName());
        item.getProperties().setCreated(now);
        item.getProperties().setModified(now);
        super.create(item);
    }

    @Override
    @Transactional
    public void update(T item){
        item.getProperties().setModified(new Date());
        super.create(item);
    }

}
