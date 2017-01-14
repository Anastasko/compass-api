package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.AbstractEntity;

public interface EntityWithHistoryService<T extends AbstractEntity> extends EntityPersistenceService<T> {

    @Override
    void deleteOne(Long id);

    @Override
    void create(T item);

    @Override
    void update(T item);

    T newInstance();
}
