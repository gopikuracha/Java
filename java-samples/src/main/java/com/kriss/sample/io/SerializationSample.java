package com.kriss.sample.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import aaa.data.Person;

/**
 * @author kriss
 *	Implements Serialization concepts
 *			Serializing an Object that has Primitives/Wrappers and reference to other Objects
 *			De-serializing an Object that has Primitives/Wrappers and reference to other Objects
 *
 *			Serializing a transient variable (int & String)
 *			De-serializing a transient variable
 *
 *			Serializing a transient Object's instance variable (int & String)
 *			De-serializing a transient Object's instance variable
 *
 *			Serializing a Parent that is Serializable
 *			De-serializing a Parent that is Serializable
 */
public class SerializationSample extends SerializableParent implements Serializable {
	
	/**
	 * default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	transient private String name;
	
	private Student owner;
	transient private Person client;
	
	public SerializationSample() {}
	
	public SerializationSample(int id, String name, Student person, Person client) {
		this.id = id;
		this.name = name;
		this.owner = person;
		this.client = client;
		setParentName("Updated-Parent");
		setParentId(20);
	}
	
	/**
	 * A private method invoked if present, during Serialization
	 * @param ObjectOutputStream
	 */
	private void writeObject(ObjectOutputStream os){
		try {
			os.defaultWriteObject();
			os.writeInt(this.client.getAge());
			os.writeChars(this.client.getFirstName());
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	/**
	 * A private method invoked if present, during De-serialization
	 * @param ObjectInputStream
	 */
	private void readObject(ObjectInputStream is){
		try {
			is.defaultReadObject();
			this.client = new Person();
			client.age = is.readInt();
			//client.firstName = (String) is.read
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getOwner() {
		return owner;
	}

	public void setOwner(Student owner) {
		this.owner = owner;
	}

	public Person getClient() {
		return client;
	}

	public void setClient(Person client) {
		this.client = client;
	}
}

class Student implements Serializable {
	/**
	 * default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	
	public Student() {}
	
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
