
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.AndroidIconService;
import com.anastasko.lnucompass.api.infrastructure.AndroidIconViewService;
import com.anastasko.lnucompass.api.model.domain.EntityAndroidIcon;
import com.anastasko.lnucompass.api.model.view.EntityAndroidIconViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AndroidIconViewServiceImpl
    extends AbstractViewServiceImpl<EntityAndroidIcon, EntityAndroidIconViewModel>
    implements AndroidIconViewService
{

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UrlResourceViewService urlResourceViewService;
    @Autowired
    private AndroidIconService androidIconService;

    @Override
    @Transactional
    public EntityAndroidIconViewModel toView(EntityAndroidIcon entity) {
        return new EntityAndroidIconViewModel(entity);
    }

    @Override
    @Transactional
    public void mergeFields(EntityAndroidIcon entity, EntityAndroidIconViewModel item) {
        urlResourceViewService.mergeFields(entity.getXxxhdpi(), item.getXxxhdpi());
        urlResourceViewService.mergeFields(entity.getXxhdpi(), item.getXxhdpi());
        urlResourceViewService.mergeFields(entity.getXhdpi(), item.getXhdpi());
        urlResourceViewService.mergeFields(entity.getMdpi(), item.getMdpi());
        urlResourceViewService.mergeFields(entity.getHdpi(), item.getHdpi());
    }

    @Override
    @Transactional
    public ContentEntityService<EntityAndroidIcon> getEntityService() {
        return androidIconService;
    }

}
