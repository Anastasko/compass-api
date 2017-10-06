package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.model.domain.AbstractEntity;

public class AbstractEntitiesViewModel extends EntitiesViewModel<AbstractEntityViewModel> {
	
	public AbstractEntitiesViewModel() {}
	
	public AbstractEntitiesViewModel(Iterable<? extends AbstractEntity> list){
		for(AbstractEntity entity : list){
			add(new AbstractEntityViewModel(entity));
		}
	}

}
