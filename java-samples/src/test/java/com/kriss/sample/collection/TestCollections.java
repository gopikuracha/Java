package com.kriss.sample.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import aaa.data.Address;
import aaa.data.Person;

@Ignore
public class TestCollections {

	@Test
	public void testArrayList() {
		List<String> list = new ArrayList<String>();
		
		for(int i=0; i<1000000; i++){
			list.add(""+i);
		}
		
		Long start = System.currentTimeMillis();
		System.out.println(list.size());
		System.out.println(list.get(76));
		list.add(76, "Gopi");
		System.out.println(list.get(78));
		System.out.println(list);
		System.out.println(System.currentTimeMillis() - start);
	}
	
	@Test
	public void testLinkedList() {
		List<String> list = new LinkedList<String>();
		
		for(int i=0; i<1000000; i++){
			list.add(""+i);
		}
		
		Long start = System.currentTimeMillis();
		System.out.println(list.size());
		System.out.println(list.get(76));
		list.add(76, "Gopi");
		System.out.println(list.get(78));
		System.out.println(list);
		System.out.println(System.currentTimeMillis() - start);
	}
	
	@Test
	public void testCollectionsComparatorSort() {
		Person p1 = new Person("Gopi", "Krishna", "Kuracha", 32, new Address("Mills Ln", "Irving"));
		Person p2 = new Person("Gopi", null, "Kuracha", 32, new Address("Mills Ln", "Dallas"));
		Person p3 = new Person("Radha", "Krishna", "Kuracha", 32, new Address("O Conor Rd", "Austin"));
		Person p4 = new Person("Bharat", "Kumar", "Veluri", 32, new Address("N Mac Arthur Blvd", "Irving"));
		Person p5 = new Person("Naveen", "Kumar", "Darsi", 32, new Address("Wills Ln", "Pitsburg"));
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		persons.add(p4);
		persons.add(p5);
		
		Collections.sort(persons, new Comparator<Person>() {

			public int compare(Person o1, Person o2) {
				//return o1.compareTo(o2);
				//return o1.age - o2.age;
				return o1.home.city.compareTo(o2.home.city);
			}
		});
		
		for(Person p : persons) {
			System.out.println(p);
		}
	}
	
	@Test
	public void testCollectionsComparableSort() {
		
		Person p1 = new Person("Gopi", "Krishna", "Kuracha", 32, new Address("Mills Ln", "Irving"));
		Person p2 = new Person("Gopi", null, "Kuracha", 32, new Address("Mills Ln", "Dallas"));
		Person p3 = new Person("Radha", "Krishna", "Kuracha", 32, new Address("O Conor Rd", "Austin"));
		Person p4 = new Person("Bharat", "Kumar", "Veluri", 32, new Address("N Mac Arthur Blvd", "Irving"));
		Person p5 = new Person("Naveen", "Kumar", "Darsi", 32, new Address("Wills Ln", "Pitsburg"));
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		persons.add(p4);
		persons.add(p5);
		
		Collections.sort(persons);
		
		for(Person p : persons) {
			System.out.println(p);
		}
	}
}
