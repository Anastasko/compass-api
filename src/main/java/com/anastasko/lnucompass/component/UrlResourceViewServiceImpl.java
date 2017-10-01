package com.anastasko.lnucompass.component;

import com.anastasko.lnucompass.configuration.WebConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.anastasko.lnucompass.model.domain.UrlResource;
import com.anastasko.lnucompass.model.view.UrlResourceViewModel;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

@Service
public class UrlResourceViewServiceImpl
        extends AbstractViewServiceImpl<UrlResource, UrlResourceViewModel>
        implements UrlResourceViewService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UrlResourceService urlResourceService;

    @Override
    public UrlResourceViewModel toView(UrlResource r) {
        return new UrlResourceViewModel(r);
    }

    @Override
    public void mergeFields(UrlResource l, UrlResourceViewModel r) {
        l.setUrl(r.getUrl());
    }

    @Override
    public ContentEntityService<UrlResource> getEntityService() {
        return urlResourceService;
    }

}
