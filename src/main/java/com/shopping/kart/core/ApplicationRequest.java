package com.shopping.kart.core;

public class ApplicationRequest<T> {

	private RequestContext requestContext;
	private T serviceRequest;
	
	public RequestContext getRequestContext() {
		return requestContext;
	}
	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
	}
	public T getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(T serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	
}
