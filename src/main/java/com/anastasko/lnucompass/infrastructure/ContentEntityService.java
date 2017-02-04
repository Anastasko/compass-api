package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.AbstractContentEntity;

import java.util.List;

public interface ContentEntityService<T extends AbstractContentEntity> extends EntityPersistenceService<T> {

    @Override
    void create(T item);

    @Override
    void update(T item);

}
