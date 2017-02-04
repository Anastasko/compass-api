package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.domain.AbstractEntity;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import com.anastasko.lnucompass.model.view.FindModifiedArgs;

import java.util.List;

public interface ContentEntityService<T extends AbstractContentEntity> extends EntityPersistenceService<T> {

    List<T> find(FindModifiedArgs args);

    @Override
    void create(T item);

    @Override
    void update(T item);

}
