package com.kriss.sample.soap.testmart;

public class InvalidInputException extends Exception {
	
	private String errorDetails;
	
	public InvalidInputException(String reason, String errorDetails) {
		super(reason);
		this.errorDetails = errorDetails;
	}
	
	public String getFaultInfo() {
		return errorDetails;
	}

	public String getFaultCode() {
		return "(500) Internal Server Error";
	}
}
