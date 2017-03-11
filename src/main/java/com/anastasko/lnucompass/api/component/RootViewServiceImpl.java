
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.RootService;
import com.anastasko.lnucompass.api.infrastructure.RootViewService;
import com.anastasko.lnucompass.api.model.domain.EntityRoot;
import com.anastasko.lnucompass.api.model.view.EntityRootViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.anastasko.lnucompass.model.view.ItemsVersionViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RootViewServiceImpl
    extends AbstractViewServiceImpl<EntityRoot, EntityRootViewModel>
    implements RootViewService
{

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UrlResourceViewService urlResourceViewService;
    @Autowired
    private RootService rootService;

    @Override
    @Transactional
    public EntityRootViewModel toView(EntityRoot entity) {
        return new EntityRootViewModel(entity);
    }

    @Override
    @Transactional
    public ObjectNode toSynchronisationView(EntityRoot entity) {
        ObjectNode item = objectMapper.createObjectNode();
        item.put("id", entity.getId());
        item.put("version", entity.getItem().getModified().getTime());
        item.putPOJO("cityItems", new ItemsVersionViewModel(entity.getCityItems()));
        return item;
    }

    @Override
    @Transactional
    public void mergeFields(EntityRoot entity, EntityRootViewModel item) {
    }

    @Override
    @Transactional
    public ContentEntityService<EntityRoot> getEntityService() {
        return rootService;
    }

}
