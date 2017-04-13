package com.anastasko.lnucompass.infrastructure;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface IEntity<K extends Serializable> {

	K getId();
	
}
