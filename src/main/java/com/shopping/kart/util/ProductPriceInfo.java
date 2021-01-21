/**
 * 
 */
package com.shopping.kart.util;

import lombok.Getter;

/**
 * @author Kiran Kumar Kota
 *
 * Jan 21, 2021 9:18:25 AM
 *
 * TODO
 *
 * 
 */
@Getter
public enum ProductPriceInfo {
	
	TSHIRT("T-shirt","500"),TROUSERS("Trousers","1500"),JACKET("Jacket","2500"),SHOES("Shoes","5000");
	
	private String productName;
	private String price;
	
	ProductPriceInfo(String productName,String price) {
		this.productName=productName;
		this.price = price;
	}
	
	public static ProductPriceInfo getByName(String productName) {
		ProductPriceInfo[] values = ProductPriceInfo.values();
		for (ProductPriceInfo status : values) {
			if (status.getProductName().equalsIgnoreCase(productName)) {
				return status;
			}
		}
		return null;
	}
}
