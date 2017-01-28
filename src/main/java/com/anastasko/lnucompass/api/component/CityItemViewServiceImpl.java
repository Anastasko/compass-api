
package com.anastasko.lnucompass.api.component;

import java.util.HashSet;
import com.anastasko.lnucompass.api.infrastructure.CityItemService;
import com.anastasko.lnucompass.api.infrastructure.CityItemViewService;
import com.anastasko.lnucompass.api.infrastructure.MapService;
import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.api.model.domain.EntityMap;
import com.anastasko.lnucompass.api.model.view.EntityCityItemViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.EntityWithHistoryService;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityItemViewServiceImpl
    extends AbstractViewServiceImpl<EntityCityItem, EntityCityItemViewModel>
    implements CityItemViewService
{

    @Autowired
    private MapService mapService;
    @Autowired
    private CityItemService cityItemService;

    @Override
    @Transactional
    public EntityCityItemViewModel toView(EntityCityItem entity) {
        return new EntityCityItemViewModel(entity);
    }

    @Override
    @Transactional
    public void mergeFields(EntityCityItem entity, EntityCityItemViewModel item) {
        entity.setName(item.getName());
        entity.setLongitude(item.getLongitude());
        entity.setLatitude(item.getLatitude());
        entity.setMaps(new HashSet<EntityMap>());
        for (AbstractEntityViewModel view: item.getMaps()) {
            entity.getMaps().add(mapService.getReference(view.getId()));
        }
    }

    @Override
    @Transactional
    public EntityWithHistoryService<EntityCityItem> getEntityService() {
        return cityItemService;
    }

}
