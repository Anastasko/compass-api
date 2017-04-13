package com.anastasko.lnucompass.model.domain.converter;

import javax.persistence.AttributeConverter;

public class LongConverter implements
		AttributeConverter<Long, Long> {

	
	@Override
	public Long convertToDatabaseColumn(Long entityValue) {
		return entityValue;
	}

	@Override
	public Long convertToEntityAttribute(Long databaseValue) {	
		if (databaseValue == null) {
			return 0L;
		}
		return databaseValue;
	}

}
