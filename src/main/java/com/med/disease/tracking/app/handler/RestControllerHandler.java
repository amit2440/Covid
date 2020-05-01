package com.med.disease.tracking.app.handler;

import org.springframework.validation.BindingResult;

public abstract class RestControllerHandler {

	protected BindingResult bindingResult;
	
	protected abstract void prepareRequest(Object request, BindingResult result, String... pathParam);

	protected abstract void validateRequest();

	protected abstract Object processRequest() throws Exception;

	public final Object handle(Object request, BindingResult result, String... pathParam) throws Exception {
		prepareRequest(request, result, pathParam);
		validateRequest();
		return processRequest();
	}
}
