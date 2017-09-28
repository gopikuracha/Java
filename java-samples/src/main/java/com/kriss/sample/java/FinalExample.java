package com.kriss.sample.java;

public class FinalExample {
	
	final static int size = 10;
	//final int count; - <Invalid>

	public static void main(String[] args) {
		
		//size = 20; - Once assigned cannot be reassigned
		
		
		final int i;
		i = 10;
		//i = 20; - Once assigned cannot be reassigned
		
		final Thread t;
		t = new Thread();
		t.setName("TestThread");
		
		//t = new Thread(); - Once assigned cannot be reassigned
		
	}
}
