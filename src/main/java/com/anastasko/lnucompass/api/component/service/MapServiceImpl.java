
package com.anastasko.lnucompass.api.component.service;

import com.anastasko.lnucompass.api.infrastructure.service.MapService;
import com.anastasko.lnucompass.api.model.domain.EntityMap;
import com.anastasko.lnucompass.implementation.AbstractContentEntityServiceImpl;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl
    extends AbstractContentEntityServiceImpl<EntityMap>
    implements MapService
{


    @Override
    public Class<EntityMap> getEntityClass() {
        return EntityMap.class;
    }

    @Override
    public EntityTypeName getEntityTypeName() {
        return EntityTypeName.MAP;
    }

    @Override
    public EntityMap newInstance() {
        return new EntityMap();
    }

}
