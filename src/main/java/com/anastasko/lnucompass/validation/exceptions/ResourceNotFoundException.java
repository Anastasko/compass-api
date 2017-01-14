package com.anastasko.lnucompass.validation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String text){
		super(text);
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
