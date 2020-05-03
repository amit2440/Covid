package com.med.disease.tracking.app.exception;

@SuppressWarnings("serial")
public class CovidAppException extends RuntimeException {

	private ErrorResponse errorResponse;
	private String errorMessage;

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public CovidAppException() {
		super();
	}
	
	public CovidAppException(String errorMessage) {
		super(errorMessage);
	}

	public CovidAppException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
}
