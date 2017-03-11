
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.AndroidIconService;
import com.anastasko.lnucompass.api.infrastructure.IosIconService;
import com.anastasko.lnucompass.api.infrastructure.ItemKindService;
import com.anastasko.lnucompass.api.infrastructure.ItemKindViewService;
import com.anastasko.lnucompass.api.infrastructure.RootService;
import com.anastasko.lnucompass.api.model.domain.EntityItemKind;
import com.anastasko.lnucompass.api.model.view.EntityItemKindViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemKindViewServiceImpl
    extends AbstractViewServiceImpl<EntityItemKind, EntityItemKindViewModel>
    implements ItemKindViewService
{

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UrlResourceViewService urlResourceViewService;
    @Autowired
    private IosIconService iosIconService;
    @Autowired
    private AndroidIconService androidIconService;
    @Autowired
    private RootService rootService;
    @Autowired
    private ItemKindService itemKindService;

    @Override
    @Transactional
    public EntityItemKindViewModel toView(EntityItemKind entity) {
        return new EntityItemKindViewModel(entity);
    }

    @Override
    @Transactional
    public ObjectNode toSynchronisationView(EntityItemKind entity) {
        ObjectNode item = objectMapper.createObjectNode();
        item.put("id", entity.getId());
        item.put("version", entity.getItem().getModified().getTime());
        return item;
    }

    @Override
    @Transactional
    public void mergeFields(EntityItemKind entity, EntityItemKindViewModel item) {
        entity.setName(item.getName());
        entity.setIosIcon(iosIconService.getReference(item.getIosIcon().getId()));
        entity.setIosSelectedIcon(iosIconService.getReference(item.getIosSelectedIcon().getId()));
        entity.setAndroidIcon(androidIconService.getReference(item.getAndroidIcon().getId()));
        entity.setAndroidSelectedIcon(iosIconService.getReference(item.getAndroidSelectedIcon().getId()));
        entity.setOwner(rootService.getReference(item.getOwner().getId()));
    }

    @Override
    @Transactional
    public ContentEntityService<EntityItemKind> getEntityService() {
        return itemKindService;
    }

}
