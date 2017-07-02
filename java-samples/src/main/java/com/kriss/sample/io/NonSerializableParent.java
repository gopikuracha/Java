package com.kriss.sample.io;

public class NonSerializableParent {

	private int parentId;
	
	private String parentName;
	
	public NonSerializableParent() {
		parentId = 10;
		parentName = "Default-Parent";
	}
	
	public NonSerializableParent(int id, String name) {
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
