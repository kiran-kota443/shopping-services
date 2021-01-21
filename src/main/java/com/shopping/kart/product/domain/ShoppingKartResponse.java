/**
 * 
 */
package com.shopping.kart.product.domain;

import lombok.Data;

/**
 * @author Kiran Kumar Kota
 *
 * Jan 21, 2021 9:23:34 AM
 *
 * TODO
 *
 * 
 */
@Data
public class ShoppingKartResponse {
	
	private String subTotal;
	private String tax;
	private String total;
	private String[] discounts;
	

}
