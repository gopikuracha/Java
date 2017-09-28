package com.kriss.sample.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class JavaInterfaceExamples {

	public static void main(String[] args) {
		// Thread example - You do not need to create a Runnable interface anonymous class
		Thread t = new Thread(() -> System.out.println("Running a Thread..."));
		t.start();
		
		List<Person> people = Arrays.asList(
				new Person("Kuracha", 34),
				new Person("Krishna", 32),
				new Person("Gopi", 29),
				new Person("Krishna Kuracha, Gopi", 33)
				);
		
		// Sort example - You do not need to create a Comparator interface anonymous class
		Collections.sort(people, (o1, o2) -> o1.getName().compareTo(o2.getName()));
		
		System.out.println("Complete Sorted List");
		printAllConditionally(people, p -> true);
		
		System.out.println();
		printAllConditionally(people, p -> p.getName().startsWith("K"));
		System.out.println();
		printAllConditionallyUsingPredicate(people, p -> p.getName().startsWith("G"));
		System.out.println();
		performConditionally(people, p -> p.getName().startsWith("Kri"), p -> System.out.println(p));
		performConditionally(people, p -> p.getName().contains("Krishna"), p -> p.setName(p.getName().replace("Krishna", "Kriss")));
		
		System.out.println();
		System.out.println("Modified list");
		printAllConditionally(people, p -> true);
	}
	
	public static void printAllConditionally(List<Person> people, Condition<Person> c) {
		for (Person person : people) {
			if(c.test(person)) System.out.println(person);
		}
	}
	
	//Java provides some default interfaces for some operations
	public static void printAllConditionallyUsingPredicate(List<Person> people, Predicate<Person> c) {
		for (Person person : people) {
			if(c.test(person)) System.out.println(person);
		}
	}
	
	public static void performConditionally(List<Person> people, Predicate<Person> c, Consumer<Person> cs) {
		for (Person person : people) {
			if(c.test(person)) cs.accept(person);
		}
	}
	
	
}

interface Condition<T> {
	boolean test(T t);
}

class Person {
	
	private String name;
	private int age;
	
	public Person() {}
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}
