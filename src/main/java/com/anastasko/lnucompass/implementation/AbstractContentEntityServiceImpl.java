package com.anastasko.lnucompass.implementation;

import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.RootService;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.domain.Item;
import com.anastasko.lnucompass.model.enums.EntityState;
import com.anastasko.lnucompass.model.view.SyncModels;
import com.anastasko.lnucompass.sync.model.Range;
import com.anastasko.lnucompass.sync.model.SyncModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public abstract class AbstractContentEntityServiceImpl<T extends AbstractContentEntity>
        extends AbstractEntityPersistenceServiceImpl<T>
        implements ContentEntityService<T> {

    @Autowired
    private RootService rootService;

    @Override
    @Transactional
    public final List<T> findSync(SyncModels models){
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
        Root<T> root = query.from(getEntityClass());

        Predicate predicate = inRange(root, criteriaBuilder, models.get(0).getRange());
        predicate = criteriaBuilder.and(predicate, inRemoteItems(root, criteriaBuilder, models.get(0)));

        if (models.size() == 2) {
            Predicate predicate1 = inRange(root, criteriaBuilder, models.get(1).getRange());
            predicate1 = criteriaBuilder.and(predicate1, inRemoteItems(root, criteriaBuilder, models.get(1)));
            predicate = criteriaBuilder.or(predicate, predicate1);
        }

        query = query.select(root).where(predicate);
        TypedQuery<T> typedQuery = getEntityManager().createQuery(query);
        List<T> result = typedQuery.getResultList();
        return result;
    }

    private Expression<Boolean> inRemoteItems(Root<T> root, CriteriaBuilder criteriaBuilder, SyncModel syncModel) {
        Predicate predicate = criteriaBuilder.isNull(root.get("item").get("cityItem"));
        if (!syncModel.getCityItems().isEmpty()) {
            predicate = criteriaBuilder.or(predicate,
                    root.get("item").get("cityItem").get("id").in(syncModel.getCityItems()));
        }
        return predicate;
    }

    private Predicate inRange(Root<T> root, CriteriaBuilder criteriaBuilder, Range range) {
        Expression<? extends Number> transaction = root.get("item").get("lastTransaction");
        Predicate predicate = criteriaBuilder.gt(transaction, range.getFrom());
        return criteriaBuilder.and(predicate, criteriaBuilder.le(transaction, range.getTo()));
    }

    @Override
    @Transactional
    public List<T> findAll(boolean withDeleted) {
        if (withDeleted == true) {
            return findAll();
        } else {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
            Root<T> root = query.from(getEntityClass());
            Predicate predicate = criteriaBuilder.equal(root.get("item").get("state"), EntityState.ACTIVE);
            query = query.select(root).where(predicate);
            TypedQuery<T> typedQuery = getEntityManager().createQuery(query);
            List<T> result = typedQuery.getResultList();
            return result;
        }
    }

    @Override
    @Transactional
    public void create(T item){
        item.getItem().setType(getEntityTypeName());
        item.getItem().setCreated(new Date());
        onItemChange(item.getItem());
        super.create(item);
    }

    @Override
    @Transactional
    public void update(T item){
        onItemChange(item.getItem());
        super.update(item);
    }

    @Override
    @Transactional
    public void deleteOne(T item) {
        item.getItem().setState(EntityState.DELETED);
        onItemChange(item.getItem());
        super.update(item);
    }

    public void onItemChange(Item item) {
        item.setModified(new Date());
        item.setLastTransaction(rootService.currentTransactionNumber());
    }


}
