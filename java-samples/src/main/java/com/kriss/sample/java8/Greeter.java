package com.kriss.sample.java8;

public class Greeter {

	public static void main(String[] args) {
		Greeting greet = () -> System.out.println("Hello World!");
		Greeting innerClassGreeting = new Greeting() {
			@Override
			public void perform() {
				System.out.println("Hello World");
			}
		};
		
		//Both does the same thing
		greet.perform();
		innerClassGreeting.perform();
		
		//Other examples
		AddLambda al = (int a, int b) -> a+b;
		System.out.println(al.add(2, 3));
		
		StringLengthLambda sLength = s -> s.length();
		System.out.println(sLength.getLength("Hello World!"));
		printLambda(s -> s.length());
	}
	
	public static void printLambda(StringLengthLambda sl) {
		System.out.println(sl.getLength("Hello World..."));
	}

}

interface Greeting {
	void perform();
}

interface AddLambda {
	int add(int a, int b); 
}

interface StringLengthLambda {
	int getLength(String s);
}
