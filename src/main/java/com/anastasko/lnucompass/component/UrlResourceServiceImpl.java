package com.anastasko.lnucompass.component;

import org.springframework.stereotype.Service;

import com.anastasko.lnucompass.implementation.AbstractContentEntityServiceImpl;
import com.anastasko.lnucompass.infrastructure.UrlResourceService;
import com.anastasko.lnucompass.model.domain.UrlResource;
import com.anastasko.lnucompass.model.enums.EntityTypeName;

@Service
public class UrlResourceServiceImpl
	extends AbstractContentEntityServiceImpl<UrlResource>
	implements UrlResourceService {
	
	@Override
	public Class<UrlResource> getEntityClass() {
		return UrlResource.class;
	}

	@Override
	public UrlResource newInstance() {
		return new UrlResource();
	}

	@Override
	public EntityTypeName getEntityTypeName() {
		return EntityTypeName.URL_RESOURCE;
	}

}
