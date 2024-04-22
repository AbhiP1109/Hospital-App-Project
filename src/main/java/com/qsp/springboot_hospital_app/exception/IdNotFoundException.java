package com.qsp.springboot_hospital_app.exception;

public class IdNotFoundException extends RuntimeException{

	private String message;
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public IdNotFoundException(String message) {
		super();
		this.message=message;
	}
	
}
