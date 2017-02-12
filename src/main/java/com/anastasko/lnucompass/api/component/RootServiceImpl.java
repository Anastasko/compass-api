
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.RootService;
import com.anastasko.lnucompass.api.model.domain.EntityRoot;
import com.anastasko.lnucompass.implementation.AbstractContentEntityServiceImpl;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.springframework.stereotype.Service;

@Service
public class RootServiceImpl
    extends AbstractContentEntityServiceImpl<EntityRoot>
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

}
