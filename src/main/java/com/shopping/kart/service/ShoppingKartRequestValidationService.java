/**
 * 
 */
package com.shopping.kart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.kart.product.service.ProductValidationService;

/**
 * @author Kiran Kumar Kota
 *
 * Jan 21, 2021 10:17:33 AM
 *
 * TODO : This is a validation service class which is used to validate the input request
 *        This class will be used only for Kart API, means all the operations related to Kart
 *
 * 
 */
@Service
public class ShoppingKartRequestValidationService {
	
	@Autowired
	ProductValidationService productValidationService;
	
	/*
	 * This method is used to validate the input request
	 */
	public void validateRequest(String productNames) {
		productValidationService.validateProducts(productNames);
	}
	
	

}
