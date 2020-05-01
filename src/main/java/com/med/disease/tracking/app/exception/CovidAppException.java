package com.med.disease.tracking.app.exception;

@SuppressWarnings("serial")
public class CovidAppException extends RuntimeException {

	private ErrorResponse errorResponse;

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public CovidAppException() {
		super();
	}

	public CovidAppException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
}
