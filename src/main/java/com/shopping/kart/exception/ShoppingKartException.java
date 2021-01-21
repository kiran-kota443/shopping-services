package com.shopping.kart.exception;

public class ShoppingKartException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCd;

	public ShoppingKartException() {
		super();
	}

	public ShoppingKartException(String msg) {
		super(msg);
	}

	public ShoppingKartException(String msg, Throwable t) {
		super(msg, t);
	}

	public ShoppingKartException(String msg, int errorCode) {
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
