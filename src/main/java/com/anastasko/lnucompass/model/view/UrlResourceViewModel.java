package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.model.domain.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jsondoc.core.annotation.ApiObject;

import com.anastasko.lnucompass.model.domain.UrlResource;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "UrlResource", description = "UrlResource", show = false)
public class UrlResourceViewModel implements EntityViewModel<Long> {

	@ApiObjectField
	private String url;

    public UrlResourceViewModel() {

    }

	public UrlResourceViewModel(UrlResource r) {
		setUrl(r.getUrl());
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


    @Override
	@JsonIgnore
    public Long getId() {
        return null;
    }

    @Override
	@JsonIgnore
    public void setId(Long id) {

    }
}
