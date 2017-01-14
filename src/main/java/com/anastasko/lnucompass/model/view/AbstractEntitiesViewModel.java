package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;

import com.anastasko.lnucompass.model.domain.AbstractEntity;

@ApiObject(name="AbstractEntities", show=false)
public class AbstractEntitiesViewModel extends EntitiesViewModel<AbstractEntityViewModel> {
	
	public AbstractEntitiesViewModel() {}
	
	public AbstractEntitiesViewModel(Iterable<? extends AbstractEntity> list){
		for(AbstractEntity entity : list){
			add(new AbstractEntityViewModel(entity));
		}
	}

}
