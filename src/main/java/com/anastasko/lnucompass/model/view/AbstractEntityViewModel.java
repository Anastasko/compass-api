package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.model.domain.AbstractEntity;

public class AbstractEntityViewModel implements EntityViewModel<Long> {

	private Long id;
	
	public AbstractEntityViewModel() {
		
	}

	public AbstractEntityViewModel(AbstractEntity entity) {
		if (entity != null){
			setId(entity.getId());
		}
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
