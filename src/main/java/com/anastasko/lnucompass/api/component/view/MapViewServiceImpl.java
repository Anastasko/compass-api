
package com.anastasko.lnucompass.api.component.view;

import com.anastasko.lnucompass.api.infrastructure.service.CityItemService;
import com.anastasko.lnucompass.api.infrastructure.service.MapService;
import com.anastasko.lnucompass.api.infrastructure.view.MapViewService;
import com.anastasko.lnucompass.api.model.domain.EntityMap;
import com.anastasko.lnucompass.api.model.view.EntityMapViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MapViewServiceImpl
    extends AbstractViewServiceImpl<EntityMap, EntityMapViewModel>
    implements MapViewService
{

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UrlResourceViewService urlResourceViewService;
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
        urlResourceViewService.mergeFields(entity.getImage(), item.getImage());
        entity.setFloor(item.getFloor());
        entity.setOwner(((item.getOwner() == null)?null:cityItemService.getReference(item.getOwner().getId())));
    }

    @Override
    @Transactional
    public ContentEntityService<EntityMap> getEntityService() {
        return mapService;
    }

}
