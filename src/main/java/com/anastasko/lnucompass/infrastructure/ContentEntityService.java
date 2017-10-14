package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.view.SyncModels;

import java.util.List;

public interface ContentEntityService<T extends AbstractContentEntity> extends EntityPersistenceService<T> {

    List<T> findSync(SyncModels models);

    List<T> findAll(boolean withDeleted);

}
