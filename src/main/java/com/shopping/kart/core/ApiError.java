package com.shopping.kart.core;

import lombok.Data;

@Data
public class ApiError {

	private String errorCode;
	private String errorMessage;
}
