package com.anastasko.lnucompass.model.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@MappedSuperclass
public class AbstractNamedEntity extends AbstractEntity {

	@Column
	@Basic(fetch=FetchType.LAZY)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
