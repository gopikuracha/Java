package com.kriss.sample.collection.adt;

import java.util.ArrayList;
import java.util.List;

public class ParamsTree {

	private String name;
	private String value;
	private String desc;
	private List<ParamsTree> params;
	
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<ParamsTree> getParams() {
		if (params!=null) params = new ArrayList<ParamsTree>();
		return params;
	}
}
