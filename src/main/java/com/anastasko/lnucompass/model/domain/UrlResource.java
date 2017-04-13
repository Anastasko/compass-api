package com.anastasko.lnucompass.model.domain;

import com.anastasko.lnucompass.configuration.WebConfig;
import org.jsondoc.core.annotation.ApiObject;

import java.io.File;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

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

	@Override
	public Long getVersion() {
		File file = new File(WebConfig.COMPASS_DIR + this.getUrl());
		return file.exists() ? file.lastModified() : 0;
	}
}
