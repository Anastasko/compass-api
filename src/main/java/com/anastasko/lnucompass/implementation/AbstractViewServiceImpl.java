package com.anastasko.lnucompass.implementation;

import com.anastasko.lnucompass.infrastructure.EntityWithHistoryService;
import com.anastasko.lnucompass.infrastructure.ViewService;
import com.anastasko.lnucompass.model.domain.AbstractEntity;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import com.anastasko.lnucompass.validation.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * controller will get here
 */
public abstract class AbstractViewServiceImpl<T extends AbstractEntity, V extends AbstractEntityViewModel> implements ViewService<T, V> {

    public abstract V toView(T e);

    public abstract void mergeFields(T e, V v);

    public abstract EntityWithHistoryService<T> getEntityService();

    public List<V> viewModels(Collection<T> list){
        return list.stream().map(e -> toView(e)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<V> findAll() {
        return viewModels(getEntityService().findAll());
    }

    @Override
    @Transactional
    public V findOne(Long id) {
        T entity = getEntityService().findOne(id);
        if (entity == null){
            throw new ResourceNotFoundException("This " + getEntityService().getEntityClass().getSimpleName() + " does not exist (id=" + id + ")");
        }
        return toView(entity);
    }

    @Override
    @Transactional
    public Long create(V item) {
        T entity = getEntityService().newInstance();
        mergeFields(entity, item);
        getEntityService().create(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public void update(V item) {
        T entity = getEntityService().findOne(item.getId());
        mergeFields(entity, item);
        getEntityService().update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getEntityService().deleteOne(id);
    }

}
