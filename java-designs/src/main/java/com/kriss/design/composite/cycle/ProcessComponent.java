package com.kriss.design.composite.cycle;

import java.util.HashSet;
import java.util.Set;

public abstract class ProcessComponent {
	
	private String name;

	public ProcessComponent(String name) {
		this.name = name;
	}
	
	public int getStepCount() {
		return getStepCount(new HashSet<ProcessComponent>());
	}
	
	public abstract int getStepCount(Set<ProcessComponent> steps);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
