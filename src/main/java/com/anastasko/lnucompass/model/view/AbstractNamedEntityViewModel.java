package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.anastasko.lnucompass.model.domain.AbstractNamedEntity;

@ApiObject(name="AbstractNamedEntity", show=false)
public class AbstractNamedEntityViewModel extends AbstractEntityViewModel {
	
	@ApiObjectField
	private String name;

	public AbstractNamedEntityViewModel() {

	}

	public AbstractNamedEntityViewModel(AbstractNamedEntity namedEntity) {
		super(namedEntity);
		setName(namedEntity.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
