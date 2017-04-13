package com.anastasko.lnucompass.web.controller;

import com.anastasko.lnucompass.model.domain.AbstractEntity;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.anastasko.lnucompass.infrastructure.PropertyService;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractController<T extends AbstractEntity, V extends AbstractEntityViewModel> {

	@Autowired
	protected PropertyService properties;
	
	@Autowired
    protected MessageSource messageSource;

}
