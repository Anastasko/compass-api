package com.anastasko.lnucompass.implementation;

import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
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
