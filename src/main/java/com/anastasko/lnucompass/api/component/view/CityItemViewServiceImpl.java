
package com.anastasko.lnucompass.api.component.view;

import com.anastasko.lnucompass.api.infrastructure.service.CityItemService;
import com.anastasko.lnucompass.api.infrastructure.service.ItemKindService;
import com.anastasko.lnucompass.api.infrastructure.view.CityItemViewService;
import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.api.model.view.EntityCityItemViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityItemViewServiceImpl
    extends AbstractViewServiceImpl<EntityCityItem, EntityCityItemViewModel>
    implements CityItemViewService
{

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UrlResourceViewService urlResourceViewService;
    @Autowired
    private ItemKindService itemKindService;
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
        entity.setPlaceId(item.getPlaceId());
        entity.setLongitude(item.getLongitude());
        entity.setLatitude(item.getLatitude());
        entity.setAddress(item.getAddress());
        entity.setKind(((item.getKind() == null)?null:itemKindService.getReference(item.getKind().getId())));
    }

    @Override
    @Transactional
    public ContentEntityService<EntityCityItem> getEntityService() {
        return cityItemService;
    }

}
