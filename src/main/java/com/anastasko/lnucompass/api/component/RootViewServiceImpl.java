
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.RootService;
import com.anastasko.lnucompass.api.infrastructure.RootViewService;
import com.anastasko.lnucompass.api.model.domain.EntityRoot;
import com.anastasko.lnucompass.api.model.view.EntityRootViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void mergeFields(EntityRoot entity, EntityRootViewModel item) {
    }

    @Override
    @Transactional
    public ContentEntityService<EntityRoot> getEntityService() {
        return rootService;
    }

}
