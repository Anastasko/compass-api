package com.anastasko.lnucompass.model.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.anastasko.lnucompass.infrastructure.IEntity;

@MappedSuperclass
public class AbstractEntity implements IEntity<Long> {
	
	@Id
	@GeneratedValue
	@Access(AccessType.PROPERTY)
	private Long id;
	
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
