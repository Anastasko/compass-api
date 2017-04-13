package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.domain.AbstractEntity;
import org.jsondoc.core.annotation.ApiObject;

@ApiObject(name="ModifiedEntities", show=false)
public class ItemsVersionViewModel extends EntitiesViewModel<ItemVersionViewModel> {

	public ItemsVersionViewModel() {}

	public ItemsVersionViewModel(Iterable<? extends AbstractContentEntity> list){
		for(AbstractContentEntity entity : list){
			add(new ItemVersionViewModel(entity));
		}
	}

}
