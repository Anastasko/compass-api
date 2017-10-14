package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.EntityRoot;

public interface RootService extends EntityPersistenceService<EntityRoot> {

    Long lastTransactionNumber();

    Long currentTransactionNumber();
}
