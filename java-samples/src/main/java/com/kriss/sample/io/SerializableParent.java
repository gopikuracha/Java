package com.kriss.sample.io;

import java.io.Serializable;

public class SerializableParent implements Serializable {

	/**
	 * default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private int parentId;
	
	private String parentName;
	
	public SerializableParent() {
		parentId = 10;
		parentName = "Default-Parent";
	}
	
	public SerializableParent(int id, String name) {
		this.parentId = id;
		this.parentName = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
