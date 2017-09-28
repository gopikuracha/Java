package com.kriss.sample.java;

public class CustomException {

	public static void main(String[] args) {
		process();
	}
	
	public static void process() {
		throw new NullPointerException("Ya i did it");
	}
}

class RException extends Exception {
	String message;
	
	public RException() {
		// TODO Auto-generated constructor stub
	}
	
	public RException(String message) {
		this.message = message;
	}
}
