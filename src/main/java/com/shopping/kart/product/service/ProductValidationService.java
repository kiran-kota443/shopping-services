/**
 * 
 */
package com.shopping.kart.product.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.shopping.kart.exception.ShoppingKartException;
import com.shopping.kart.util.ProductPriceInfo;

/**
 * @author Kiran Kumar Kota
 *
 *         Jan 21, 2021 10:28:40 AM
 *
 *         TODO : This class is used to validate the product details 
 *
 * 
 */
@Service
public class ProductValidationService {

	public void validateProducts(String products) {
		String[] productList=products.split(" ");
		for (String productName : productList) {
			if (ProductPriceInfo.getByName(productName) == null) {
				throw new ShoppingKartException(productName+" is not a valid product",HttpStatus.BAD_REQUEST.value());
			}
		}
	}

}
