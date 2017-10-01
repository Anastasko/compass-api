package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.anastasko.lnucompass.model.domain.AbstractContentEntity;

@ApiObject(name="AbstractContentEntity", show=false)
public class AbstractContentEntityViewModel extends AbstractEntityViewModel {

	public AbstractContentEntityViewModel() {
		
	}

	public AbstractContentEntityViewModel(AbstractContentEntity entity) {
		super(entity);
	}

}
