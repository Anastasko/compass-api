
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.AndroidIconService;
import com.anastasko.lnucompass.api.infrastructure.IosIconService;
import com.anastasko.lnucompass.api.infrastructure.ItemKindService;
import com.anastasko.lnucompass.api.infrastructure.ItemKindViewService;
import com.anastasko.lnucompass.api.model.domain.EntityItemKind;
import com.anastasko.lnucompass.api.model.view.EntityItemKindViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private ItemKindService itemKindService;

    @Override
    @Transactional
    public EntityItemKindViewModel toView(EntityItemKind entity) {
        return new EntityItemKindViewModel(entity);
    }

    @Override
    @Transactional
    public void mergeFields(EntityItemKind entity, EntityItemKindViewModel item) {
        entity.setName(item.getName());
        entity.setIosIcon(((item.getIosIcon() == null)?null:iosIconService.getReference(item.getIosIcon().getId())));
        entity.setIosSelectedIcon(((item.getIosSelectedIcon() == null)?null:iosIconService.getReference(item.getIosSelectedIcon().getId())));
        entity.setAndroidIcon(((item.getAndroidIcon() == null)?null:androidIconService.getReference(item.getAndroidIcon().getId())));
        entity.setAndroidSelectedIcon(((item.getAndroidSelectedIcon() == null)?null:androidIconService.getReference(item.getAndroidSelectedIcon().getId())));
    }

    @Override
    @Transactional
    public ContentEntityService<EntityItemKind> getEntityService() {
        return itemKindService;
    }

}
