package com.kriss.sample.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReferences {

	public static void main(String[] args) {
		
		Thread t = new Thread(MethodReferences::printMessage);
		
		List<Person> people = Arrays.asList(
				new Person("Kuracha", 34),
				new Person("Krishna", 32),
				new Person("Gopi", 29),
				new Person("Krishna Kuracha, Gopi", 33)
				);
		
		System.out.println("Perform Conditionally");
		performConditionally(people, p -> true, System.out::println);
	}
	
	public static void printMessage() {
		System.out.println("Executing the thread..");
	}
	
	public static void performConditionally(List<Person> people, Predicate<Person> c, Consumer<Person> cs) {
		for (Person person : people) {
			if(c.test(person)) cs.accept(person);
		}
	}
}
