package com.shopping.kart.exception;

public class ShoppingKartUnhandledException extends Exception {

	private static final long serialVersionUID = 1L;

	private int errorCd;
		
	public ShoppingKartUnhandledException() {
		super();
	}

	public ShoppingKartUnhandledException(String msg) {
		super(msg);
	}
	
	public ShoppingKartUnhandledException(String msg, Throwable t) {
		super(msg, t);
	}

	public ShoppingKartUnhandledException(String msg, int errorCode) {
		super(msg);
		this.errorCd = errorCode;		
	}
	
	public int getErrorCd() {
		return errorCd;
	}

	public void setErrorCd(int errorCd) {
		this.errorCd = errorCd;
	}

}
