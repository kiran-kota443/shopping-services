/**
 * 
 */
package com.shopping.kart.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.kart.product.domain.ShoppingKartResponse;
import com.shopping.kart.util.ProductPriceInfo;

/**
 * @author Kiran Kumar Kota
 *
 *         Jan 21, 2021 10:12:23 AM
 *
 *         TODO: ShoppingKartService is businees service class
 *
 * 
 */
@Service
public class ShoppingKartService {

	@Autowired
	ShoppingKartRequestValidationService requestValidationService;
	
	/*
	 * calculateOrderTotal is used to get order total
	 */
	public ShoppingKartResponse calculateOrderTotal(String products) {

		requestValidationService.validateRequest(products);
		String[] productsArray = products.split(" ");
		Map<ProductPriceInfo, Integer> productQuantity = countProducts(productsArray);
		
		ShoppingKartResponse response=buildResponse(productQuantity);
		return response;
	}

	/*
	 * This method will take input the return the Map of Prodcuts and respective Qty
	 */
	Map<ProductPriceInfo, Integer> countProducts(String[] productList) {

		Map<ProductPriceInfo, Integer> productQuantity = new HashMap<>();

		for (String productName : productList) {
			updateProductQuantity(productQuantity, productName);
		}

		return productQuantity;
	}

	private void updateProductQuantity(Map<ProductPriceInfo, Integer> productQty, String productName) {
		int qty = 1;
		ProductPriceInfo productInfo = ProductPriceInfo.getByName(productName);
		if (productQty.get(productInfo) == null) {
			productQty.put(productInfo, qty);
		} else {
			qty += productQty.get(productInfo);
			productQty.put(productInfo, qty);
		}
	}

	/*
	 * This method will add the each product price and return the totalAmount
	 */
	private int calculateTotalPrice(Map<ProductPriceInfo, Integer> productQty) {
		int totalPrice = 0;
		for (ProductPriceInfo productInfo : productQty.keySet()) {
			int producPrice = Integer.parseInt(productInfo.getPrice());
			totalPrice += producPrice * productQty.get(productInfo);
		}
		return totalPrice;
	}
	
	/*
	 * This method will calculate the total Tax for  totalAmount
	 */
	private int calculateTotalTax(int totalPrice) {
		int totalTax = (totalPrice * 18) / 100;
		return totalTax;
	}
	
	/*
	 * This method will calculate the discount for Shoes
	 */
	private int calculateShoesDiscount(ProductPriceInfo shoes, int qty) {
		 int shoesDiscount = (Integer.parseInt(shoes.getPrice()) * 10) / 100;
		return shoesDiscount * qty;
	}
	/*
	 * This method will calculate the discount for Jacket
	 */
	private int calculateJacketDiscount(ProductPriceInfo jacket,int jacketQty,int tshirstQty) {
		int discount=0;
		int tshirtPairCount=tshirstQty/2;
		if(tshirtPairCount > jacketQty) {
			discount=(Integer.valueOf(jacket.getPrice())/2)*jacketQty;
		}else if(tshirtPairCount < jacketQty) {
			discount=(Integer.valueOf(jacket.getPrice())/2)*tshirtPairCount;
		}else if(tshirtPairCount == jacketQty){
			discount=(Integer.valueOf(jacket.getPrice())/2)*jacketQty;
		}
		return discount;
	  }
	
	private Map<ProductPriceInfo, Integer> productDiscount(Map<ProductPriceInfo, Integer> productQuantity) {
		Map<ProductPriceInfo, Integer> productDiscount=new HashMap<>();
		Integer shoesQty=productQuantity.get(ProductPriceInfo.SHOES);
		if(shoesQty != null) {
			int shoesDiscount=calculateShoesDiscount(ProductPriceInfo.SHOES,shoesQty);
			productDiscount.put(ProductPriceInfo.SHOES, shoesDiscount);
			
		}
		Integer jacketQty=productQuantity.get(ProductPriceInfo.JACKET);
		if(jacketQty != null) {
		int jacketDiscount=calculateJacketDiscount(ProductPriceInfo.JACKET,jacketQty, productQuantity.get(ProductPriceInfo.TSHIRT));
		productDiscount.put(ProductPriceInfo.JACKET, jacketDiscount);
		}
		return productDiscount;
	}
	 
	/*
	 * This method is used to build the response
	 */
	private ShoppingKartResponse buildResponse(Map<ProductPriceInfo, Integer> productQuantity) {
		int subTotal = calculateTotalPrice(productQuantity);
		int totalTax = calculateTotalTax(subTotal);
		int totalDiscount=0;
		Map<ProductPriceInfo, Integer> productDiscount=productDiscount(productQuantity);
		String[] discountDesc=new String[]{"",""};
		if(!productDiscount.isEmpty()) {
		Integer shoesDiscount=productDiscount.get(ProductPriceInfo.SHOES);
		if(shoesDiscount !=null) {
			totalDiscount=totalDiscount+shoesDiscount;
			discountDesc[0]="10% off on shoes: -Rs. "+shoesDiscount;
		}
		Integer jacketDiscount=productDiscount.get(ProductPriceInfo.JACKET);
		if(jacketDiscount !=null) {
			totalDiscount=totalDiscount+jacketDiscount;
			discountDesc[1]="50% off on jacket: -Rs. "+jacketDiscount;
		}
		}
		ShoppingKartResponse response = new ShoppingKartResponse();
		response.setSubTotal(String.valueOf(subTotal));
		response.setTax(String.valueOf(totalTax));
		response.setTotal(String.valueOf(subTotal+totalTax-totalDiscount));
		response.setDiscounts(discountDesc);
		
		return response;
	}

}
