
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.ItemKindService;
import com.anastasko.lnucompass.api.infrastructure.MapItemService;
import com.anastasko.lnucompass.api.infrastructure.MapItemViewService;
import com.anastasko.lnucompass.api.infrastructure.MapService;
import com.anastasko.lnucompass.api.model.domain.EntityMapItem;
import com.anastasko.lnucompass.api.model.view.EntityMapItemViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MapItemViewServiceImpl
    extends AbstractViewServiceImpl<EntityMapItem, EntityMapItemViewModel>
    implements MapItemViewService
{

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UrlResourceViewService urlResourceViewService;
    @Autowired
    private MapService mapService;
    @Autowired
    private ItemKindService itemKindService;
    @Autowired
    private MapItemService mapItemService;

    @Override
    @Transactional
    public EntityMapItemViewModel toView(EntityMapItem entity) {
        return new EntityMapItemViewModel(entity);
    }

    @Override
    @Transactional
    public ObjectNode toSynchronisationView(EntityMapItem entity) {
        ObjectNode item = objectMapper.createObjectNode();
        item.put("id", entity.getId());
        item.put("version", entity.getItem().getModified().getTime());
        return item;
    }

    @Override
    @Transactional
    public void mergeFields(EntityMapItem entity, EntityMapItemViewModel item) {
        entity.setOwner(mapService.getReference(item.getOwner().getId()));
        entity.setName(item.getName());
        entity.setX(item.getX());
        entity.setY(item.getY());
        entity.setSquare(item.getSquare());
        entity.setRoom(item.getRoom());
        entity.setKind(itemKindService.getReference(item.getKind().getId()));
    }

    @Override
    @Transactional
    public ContentEntityService<EntityMapItem> getEntityService() {
        return mapItemService;
    }

}
