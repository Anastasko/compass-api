package com.anastasko.lnucompass.validation.exceptions;

@SuppressWarnings("serial")
public class DomainModelException extends RuntimeException {
	
	public DomainModelException() {}
	
	public DomainModelException(String text) {
		super(text);
	}
	
}
