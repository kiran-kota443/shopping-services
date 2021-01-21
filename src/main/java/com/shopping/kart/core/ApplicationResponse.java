/**
 * 
 */
package com.shopping.kart.core;

/**
 * @author Kiran Kumar Kota
 *
 * Jan 21, 2021 9:44:00 AM
 *
 * TODO
 *
 * 
 */
public class ApplicationResponse <T> {

	private T serviceResponse;

	public T getServiceResponse() {
		return serviceResponse;
	}

	public void setServiceResponse(T serviceResponse) {
		this.serviceResponse = serviceResponse;
	}
	
}