
package com.anastasko.lnucompass.component;

import com.anastasko.lnucompass.implementation.AbstractEntityPersistenceServiceImpl;
import com.anastasko.lnucompass.infrastructure.RootService;
import com.anastasko.lnucompass.model.domain.EntityRoot;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Root;

@Service
public class RootServiceImpl
    extends AbstractEntityPersistenceServiceImpl<EntityRoot>
    implements RootService
{

    @Override
    public Class<EntityRoot> getEntityClass() {
        return EntityRoot.class;
    }

    @Override
    public EntityTypeName getEntityTypeName() {
        return EntityTypeName.ROOT;
    }

    @Override
    public EntityRoot newInstance() {
        return new EntityRoot();
    }

    @Override
    public Long lastTransactionNumber() {
        return findAll().get(0).getId();
    }

    @Override
    public Long currentTransactionNumber() {
        deleteAll();
        EntityRoot root = new EntityRoot();
        create(root);
        return root.getId();
    }
}
