package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.anastasko.lnucompass.model.domain.AbstractContentEntity;

@ApiObject(name="AbstractContentEntity", show=false)
public class AbstractContentEntityViewModel extends AbstractEntityViewModel {

	@ApiObjectField(required=true)
	private Long version;

	public AbstractContentEntityViewModel() {
		
	}

	public AbstractContentEntityViewModel(AbstractContentEntity entity) {
		setVersion(entity.getItem().getModified().getTime());
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
}
