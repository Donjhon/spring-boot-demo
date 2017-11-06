package com.boot.exception;

public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public MyException(String returnCode, String title, String message) {
		super();
		this.returnCode = returnCode;
		this.title = title;
		this.message = message;
	}
	private final String returnCode;
	private final String title;
	private final String message;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public String getTitle() {
		return title;
	}
	public String getMessage() {
		return message;
	}
	
	
}
