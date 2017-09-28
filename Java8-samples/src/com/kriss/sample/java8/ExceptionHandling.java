package com.kriss.sample.java8;

import java.util.function.BiConsumer;

public class ExceptionHandling {

	public static void main(String[] args) {
		int[] numbers = {1,2,3,4};
		int key = 0;
		
		/*process(numbers, key, (v, k) -> {
			try { System.out.println(v/k); }
			catch (ArithmeticException ae) { System.out.println(ae.getMessage()); }
		});*/
		
		//Better way of handling the exception
		process(numbers, key, wrapperLambda((v, k) -> System.out.println(v/k)));
		
		//Using generic wrapper lambda
		process(numbers, key, genericWrapperLambda((v, k) -> System.out.println(v/k)));
	}
	
	public static void process(int[] numbers, int key, BiConsumer<Integer, Integer> consumer) {
		for (int i : numbers) {
			consumer.accept(i, key);
		}
	}
	
	//Wrapper lambda
	public static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> consumer) {
		return (v, k) -> {
			try { consumer.accept(v, k); }
			catch(ArithmeticException ae) { System.out.println("Got arithematic Exception"); }
		};
	}
	
	//Making a generic Wrapper Lambda
	public static <T, U> BiConsumer<T, U> genericWrapperLambda(BiConsumer<T, U> consumer) {
		return (v, k) -> {
			try { consumer.accept(v, k); }
			catch(ArithmeticException ae) { System.out.println("Got arithematic Exception"); }
		};
	}
}
