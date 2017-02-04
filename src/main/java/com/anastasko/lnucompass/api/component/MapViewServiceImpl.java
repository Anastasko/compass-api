
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.CityItemService;
import com.anastasko.lnucompass.api.infrastructure.MapService;
import com.anastasko.lnucompass.api.infrastructure.MapViewService;
import com.anastasko.lnucompass.api.model.domain.EntityMap;
import com.anastasko.lnucompass.api.model.view.EntityMapViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MapViewServiceImpl
    extends AbstractViewServiceImpl<EntityMap, EntityMapViewModel>
    implements MapViewService
{

    @Autowired
    private CityItemService cityItemService;
    @Autowired
    private MapService mapService;

    @Override
    @Transactional
    public EntityMapViewModel toView(EntityMap entity) {
        return new EntityMapViewModel(entity);
    }

    @Override
    @Transactional
    public void mergeFields(EntityMap entity, EntityMapViewModel item) {
        entity.setImageUrl(item.getImageUrl());
        entity.setFloor(item.getFloor());
        entity.setOwner(cityItemService.getReference(item.getOwner().getId()));
    }

    @Override
    @Transactional
    public ContentEntityService<EntityMap> getEntityService() {
        return mapService;
    }

}
