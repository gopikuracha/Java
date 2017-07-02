package com.kriss.sample.collection.adt;

import java.util.List;

public class Person {
	
	private String name;
	private String gender;
	private Person father;
	private Person mother;
	
	private List<Person> spouses;
	private List<Person> siblings;
	private List<Person> childreen;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Person getFather() {
		return father;
	}
	public void setFather(Person father) {
		this.father = father;
	}
	
	public Person getMother() {
		return mother;
	}
	public void setMother(Person mother) {
		this.mother = mother;
	}
	
	public List<Person> getSpouses() {
		return spouses;
	}
	public void setSpouses(List<Person> spouses) {
		this.spouses = spouses;
	}
	
	public List<Person> getSiblings() {
		return siblings;
	}
	public void setSiblings(List<Person> siblings) {
		this.siblings = siblings;
	}
	
	public List<Person> getChildreen() {
		return childreen;
	}
	public void setChildreen(List<Person> childreen) {
		this.childreen = childreen;
	}
}
