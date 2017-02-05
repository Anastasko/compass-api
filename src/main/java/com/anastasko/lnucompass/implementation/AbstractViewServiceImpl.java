package com.anastasko.lnucompass.implementation;

import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.ViewService;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import com.anastasko.lnucompass.model.view.Pair;
import com.anastasko.lnucompass.validation.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * controller will invoke methods from here
 */
public abstract class AbstractViewServiceImpl<T extends AbstractContentEntity, V extends AbstractEntityViewModel> implements ViewService<T, V> {

    public abstract V toView(T e);

    public abstract ObjectNode toSynchronisationView(T e);

    public abstract void mergeFields(T e, V v);

    public abstract ContentEntityService<T> getEntityService();

    public List<V> viewModels(Collection<T> list){
        return list.stream().map(e -> toView(e)).collect(Collectors.toList());
    }

    public List<ObjectNode> synchronisationViewModels(List<T> list){
        return list.stream().map(e -> toSynchronisationView(e)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<V> findAll() {
        return viewModels(getEntityService().findAll());
    }

    @Override
    @Transactional
    public List<V> findMany(Iterable<Long> ids) {
        return viewModels(getEntityService().findMany(ids));
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

    @Override
    @Transactional
    public List<ObjectNode> findForSynchronisation(List<Long> ids){
        return synchronisationViewModels(getEntityService().findMany(ids));
    }


}
