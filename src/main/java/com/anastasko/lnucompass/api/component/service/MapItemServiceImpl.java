
package com.anastasko.lnucompass.api.component.service;

import com.anastasko.lnucompass.api.infrastructure.service.MapItemService;
import com.anastasko.lnucompass.api.model.domain.EntityMapItem;
import com.anastasko.lnucompass.implementation.AbstractContentEntityServiceImpl;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.springframework.stereotype.Service;

@Service
public class MapItemServiceImpl
    extends AbstractContentEntityServiceImpl<EntityMapItem>
    implements MapItemService
{


    @Override
    public Class<EntityMapItem> getEntityClass() {
        return EntityMapItem.class;
    }

    @Override
    public EntityTypeName getEntityTypeName() {
        return EntityTypeName.MAP_ITEM;
    }

    @Override
    public EntityMapItem newInstance() {
        return new EntityMapItem();
    }

}
