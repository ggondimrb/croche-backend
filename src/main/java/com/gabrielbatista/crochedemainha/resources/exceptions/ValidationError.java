package com.gabrielbatista.crochedemainha.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private List<FieldMessage> errors = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;
	
	public ValidationError(Long timeStamp, Integer status, String error, String message, String path) {
		super(timeStamp, status, error, message, path);
		this.errors = errors;
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	public void addError(String fieldName, String mensagem) {
		errors.add(new FieldMessage(fieldName,mensagem));
		
	}
}
