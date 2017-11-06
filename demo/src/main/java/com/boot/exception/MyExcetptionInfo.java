package com.boot.exception;

public enum MyExcetptionInfo {

	noSuchMessage("","","未知异常"),
	uodateFail("","",""),
	;
	
	
	private MyExcetptionInfo(String title, String returnCode, String message) {
		this.title = title;
		this.returnCode = returnCode;
		this.message = message;
	}
	private String title;
	private String returnCode;
	private String message;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
