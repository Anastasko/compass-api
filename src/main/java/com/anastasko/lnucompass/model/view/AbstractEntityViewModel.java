package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.anastasko.lnucompass.model.domain.AbstractEntity;

@ApiObject(name="AbstractEntity", show=false)
public class AbstractEntityViewModel implements EntityViewModel<Long> {

	@ApiObjectField(required=true)
	private Long id;
	
	public AbstractEntityViewModel() {
		
	}

	public AbstractEntityViewModel(AbstractEntity entity) {
		setId(entity.getId());
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
}
