package com.kriss.sample.java8;

import java.util.Arrays;
import java.util.List;

public class CollectionIteration {

	public static void main(String[] args) {
		
		List<Person> people = Arrays.asList(
				new Person("Kuracha", 34),
				new Person("Krishna", 32),
				new Person("Gopi", 29),
				new Person("Krishna Kuracha, Gopi", 33)
				);
		
		//people.forEach(p -> System.out.println(p)); - Same as below
		people.forEach(System.out::println);
	}
}
