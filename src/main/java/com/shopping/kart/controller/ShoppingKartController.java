/**
 * 
 */
package com.shopping.kart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.kart.core.ApplicationResponse;
import com.shopping.kart.exception.ShoppingKartException;
import com.shopping.kart.product.domain.ShoppingKartResponse;
import com.shopping.kart.service.ShoppingKartService;
/**
 * @author Kiran Kumar Kota
 *
 * Jan 21, 2021 9:02:20 AM
 *
 * TODO
 *
 * 
 */
/*
 * ShoppingKartController is used to build rest API's for the products which added in Shopping Kart
 */
@RestController
@RequestMapping("/api/v1/kart")
public class ShoppingKartController {
	
	@Autowired
	ShoppingKartService shoppingKartService;
	
	/*
	 * This is the GET API to calculate the total price for the give order
	 */
	@RequestMapping(value = "/products/{products}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApplicationResponse<ShoppingKartResponse> getOrderTotal(@PathVariable String products)
            throws ShoppingKartException {
		
        ShoppingKartResponse kartResponse=shoppingKartService.calculateOrderTotal(products);
        ApplicationResponse<ShoppingKartResponse> applicationResponse = new ApplicationResponse<>();
        applicationResponse.setServiceResponse(kartResponse);
        return applicationResponse;
    }

}
