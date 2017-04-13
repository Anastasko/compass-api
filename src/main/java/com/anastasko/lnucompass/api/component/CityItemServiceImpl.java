
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.CityItemService;
import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.implementation.AbstractContentEntityServiceImpl;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.springframework.stereotype.Service;

@Service
public class CityItemServiceImpl
    extends AbstractContentEntityServiceImpl<EntityCityItem>
    implements CityItemService
{


    @Override
    public Class<EntityCityItem> getEntityClass() {
        return EntityCityItem.class;
    }

    @Override
    public EntityTypeName getEntityTypeName() {
        return EntityTypeName.CITY_ITEM;
    }

    @Override
    public EntityCityItem newInstance() {
        return new EntityCityItem();
    }

}
