package com.anastasko.lnucompass.validation.exceptions;

@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {
	
	public ServiceException() {}
	
	public ServiceException(String text) {
		super(text);
	}
	
}
