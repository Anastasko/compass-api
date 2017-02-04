package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.domain.AbstractEntity;
import org.jsondoc.core.annotation.ApiObject;

@ApiObject(name="ModifiedEntities", show=false)
public class ModifiedEntitiesViewModel extends EntitiesViewModel<ModifiedEntityViewModel> {

	public ModifiedEntitiesViewModel() {}

	public ModifiedEntitiesViewModel(Iterable<? extends AbstractContentEntity> list){
		for(AbstractContentEntity entity : list){
			add(new ModifiedEntityViewModel(entity));
		}
	}

}
