
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.CityItemService;
import com.anastasko.lnucompass.api.infrastructure.CityItemViewService;
import com.anastasko.lnucompass.api.infrastructure.MapService;
import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.api.model.view.EntityCityItemViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import com.anastasko.lnucompass.model.view.ModifiedEntitiesViewModel;
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
    public ObjectNode toSynchronisationView(EntityCityItem entity) {
        ObjectNode dataNode = objectMapper.createObjectNode();
        dataNode.putPOJO("maps", new ModifiedEntitiesViewModel(entity.getMaps()));
        ObjectNode item = objectMapper.createObjectNode();
        item.put("id", entity.getId());
        item.put("modified", entity.getProperties().getModified().getTime());
        item.set("data", dataNode);
        return item;
    }

    @Override
    @Transactional
    public void mergeFields(EntityCityItem entity, EntityCityItemViewModel item) {
        entity.setName(item.getName());
        entity.setLongitude(item.getLongitude());
        entity.setLatitude(item.getLatitude());
        entity.getMaps().clear();
        for (AbstractEntityViewModel view: item.getMaps()) {
            entity.getMaps().add(mapService.getReference(view.getId()));
        }
        entity.setKind(item.getKind());
    }

    @Override
    @Transactional
    public ContentEntityService<EntityCityItem> getEntityService() {
        return cityItemService;
    }

}
