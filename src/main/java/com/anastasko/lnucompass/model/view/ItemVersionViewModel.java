package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.Date;

@ApiObject(name="ItemVersion", show=false)
public class ItemVersionViewModel extends AbstractEntityViewModel {

	@ApiObjectField
    private Long version;

	public ItemVersionViewModel() { }

    public ItemVersionViewModel(AbstractContentEntity entity){
        super(entity);
        setVersion(entity.getItem().getModified().getTime());
    }

    public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
}
