package com.kriss.sample.collection.adt;

import java.util.List;

public class InfoList {

	String name;
	String value;
	
	List<InfoList> childs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<InfoList> getChilds() {
		return childs;
	}

	public void setChilds(List<InfoList> childs) {
		this.childs = childs;
	}

	@Override
	public String toString() {
		return "InfoList [name=" + name + "]";
	}
	
}
