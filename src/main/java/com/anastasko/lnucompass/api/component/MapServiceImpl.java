
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.MapService;
import com.anastasko.lnucompass.api.model.domain.EntityMap;
import com.anastasko.lnucompass.implementation.AbstractEntityWithHistoryServiceImpl;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl
    extends AbstractEntityWithHistoryServiceImpl<EntityMap>
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
