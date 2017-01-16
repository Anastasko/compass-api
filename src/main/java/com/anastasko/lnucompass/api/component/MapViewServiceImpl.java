
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.MapService;
import com.anastasko.lnucompass.api.infrastructure.MapViewService;
import com.anastasko.lnucompass.api.model.domain.EntityMap;
import com.anastasko.lnucompass.api.model.view.EntityMapViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.EntityWithHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MapViewServiceImpl
    extends AbstractViewServiceImpl<EntityMap, EntityMapViewModel>
    implements MapViewService
{

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
        entity.setName(item.getName());
        entity.setFloor(item.getFloor());
    }

    @Override
    @Transactional
    public EntityWithHistoryService<EntityMap> getEntityService() {
        return mapService;
    }

}
