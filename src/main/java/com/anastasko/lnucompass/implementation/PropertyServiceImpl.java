package com.anastasko.lnucompass.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.anastasko.lnucompass.infrastructure.PropertyService;

public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private Environment environment;
	
	@Override
	public String get(String name) {
		return environment.getProperty(name);
	}

}
