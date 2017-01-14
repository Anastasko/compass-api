package com.anastasko.lnucompass.model.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class UrlResource extends AbstractEntity {

	@Basic
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
