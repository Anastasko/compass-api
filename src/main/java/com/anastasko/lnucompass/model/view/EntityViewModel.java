package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name="AbstractEntity", show=false)
public interface EntityViewModel<K> {

	K getId();
	
	void setId(K id);
	
}
