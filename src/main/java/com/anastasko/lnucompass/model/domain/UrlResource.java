package com.anastasko.lnucompass.model.domain;

import com.anastasko.lnucompass.configuration.WebConfig;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.io.File;

@Entity
public class UrlResource extends AbstractContentEntity {
	
	@Basic
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getVersion() {
		File file = new File(WebConfig.COMPASS_DIR + this.getUrl());
		return file.exists() ? file.lastModified() : 0;
	}
}
