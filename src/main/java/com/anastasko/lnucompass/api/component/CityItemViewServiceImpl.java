
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.CityItemService;
import com.anastasko.lnucompass.api.infrastructure.CityItemViewService;
import com.anastasko.lnucompass.api.infrastructure.ItemKindService;
import com.anastasko.lnucompass.api.infrastructure.MapService;
import com.anastasko.lnucompass.api.infrastructure.RootService;
import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.api.model.view.EntityCityItemViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import com.anastasko.lnucompass.model.view.ItemsVersionViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    private RootService rootService;
    @Autowired
    private MapService mapService;
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
    public ObjectNode toSynchronisationView(EntityCityItem entity) {
        ObjectNode item = objectMapper.createObjectNode();
        item.put("id", entity.getId());
        item.put("version", entity.getItem().getModified().getTime());
        item.putPOJO("maps", new ItemsVersionViewModel(entity.getMaps()));
        return item;
    }

    @Override
    @Transactional
    public void mergeFields(EntityCityItem entity, EntityCityItemViewModel item) {
        entity.setOwner(rootService.getReference(item.getOwner().getId()));
        entity.setName(item.getName());
        entity.setLongitude(item.getLongitude());
        entity.setLatitude(item.getLatitude());
        entity.getMaps().clear();
        for (AbstractEntityViewModel view: item.getMaps()) {
            entity.getMaps().add(mapService.getReference(view.getId()));
        }
        entity.setKind(itemKindService.getReference(item.getKind().getId()));
    }

    @Override
    @Transactional
    public ContentEntityService<EntityCityItem> getEntityService() {
        return cityItemService;
    }

}
