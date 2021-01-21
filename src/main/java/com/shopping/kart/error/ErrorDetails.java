package com.shopping.kart.error;

import java.util.List;

public class ErrorDetails {

	private List<Error> errors;
	private List<ValidationError> validationErrors;
	
	public List<Error> getErrors() {
		return errors;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	public List<ValidationError> getValidationErrors() {
		return validationErrors;
	}
	public void setValidationErrors(List<ValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}
}
