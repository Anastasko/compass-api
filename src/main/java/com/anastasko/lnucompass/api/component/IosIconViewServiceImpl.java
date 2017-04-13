
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.IosIconService;
import com.anastasko.lnucompass.api.infrastructure.IosIconViewService;
import com.anastasko.lnucompass.api.model.domain.EntityIosIcon;
import com.anastasko.lnucompass.api.model.view.EntityIosIconViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IosIconViewServiceImpl
    extends AbstractViewServiceImpl<EntityIosIcon, EntityIosIconViewModel>
    implements IosIconViewService
{

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UrlResourceViewService urlResourceViewService;
    @Autowired
    private IosIconService iosIconService;

    @Override
    @Transactional
    public EntityIosIconViewModel toView(EntityIosIcon entity) {
        return new EntityIosIconViewModel(entity);
    }

    @Override
    @Transactional
    public ObjectNode toSynchronisationView(EntityIosIcon entity) {
        ObjectNode item = objectMapper.createObjectNode();
        item.put("id", entity.getId());
        item.put("version", entity.getItem().getModified().getTime());
        item.putPOJO("size2x", urlResourceViewService.toSynchronisationView(entity.getSize2x()));
        item.putPOJO("size3x", urlResourceViewService.toSynchronisationView(entity.getSize3x()));
        return item;
    }

    @Override
    @Transactional
    public void mergeFields(EntityIosIcon entity, EntityIosIconViewModel item) {
        urlResourceViewService.mergeFields(entity.getSize2x(), item.getSize2x());
        urlResourceViewService.mergeFields(entity.getSize3x(), item.getSize3x());
    }

    @Override
    @Transactional
    public ContentEntityService<EntityIosIcon> getEntityService() {
        return iosIconService;
    }

}
