package com.kriss.sample.io;

import java.io.Serializable;

/**
 * @author kriss
 *	Implements Serialization concepts
 *			Serializing a Parent that is not Serializable
 *			De-serializing a Parent that is not Serializable
 */
public class NonSerializableParentSample extends NonSerializableParent implements Serializable {
	
	/**
	 * default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	
	public NonSerializableParentSample() {}
	
	public NonSerializableParentSample(int id, String name) {
		this.id = id;
		this.name = name;
		setParentName("Updated-Parent");
		setParentId(20);
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
}
