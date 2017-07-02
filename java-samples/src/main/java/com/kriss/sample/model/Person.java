package com.kriss.sample.model;

public class Person implements Comparable<Person>{
	public String firstName;
	public String middleName;
	public String lastName;
	
	public int age;
	
	public Address home;
	
	public Person() {}
	
	public Person(String s1, String s2, String s3, int age, Address ad){
		this.firstName = s1;
		this.middleName = s2;
		this.lastName = s3;
		this.age = age;
		this.home = ad;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getHome() {
		return home;
	}

	public void setHome(Address home) {
		this.home = home;
	}
	
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", age="
				+ age + ", home=" + home + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((home == null) ? 0 : home.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (home == null) {
			if (other.home != null)
				return false;
		} else if (!home.equals(other.home))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		return true;
	}

	public int compareTo(Person o) {
		return this.home.street.compareTo(o.home.street);
	}
}
