package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.model.domain.UrlResource;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UrlResourceViewModel implements EntityViewModel<Long> {

	private String url;

    public UrlResourceViewModel() {

    }

	public UrlResourceViewModel(UrlResource r) {
		if (r != null) {
			setUrl(r.getUrl());
		}
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
