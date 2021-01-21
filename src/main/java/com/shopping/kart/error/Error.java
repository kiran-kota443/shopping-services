package com.shopping.kart.error;

import lombok.Data;

@Data
public class Error {
	private String code;
	private String message;
	public String timestamp;
}
