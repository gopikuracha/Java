package com.kriss.design.composite;

import java.util.HashSet;
import java.util.Set;

public abstract class MachineComponent {

	public abstract int getMachineCount();
	
	public abstract boolean isCompletelyUp();
	
	public abstract void stopAll();
	
	public abstract Set<Owner> getOwners();
	
	public boolean isTree() {
		return isTree(new HashSet<MachineComponent>());
	}
	
	public abstract boolean isTree(Set<MachineComponent> visited);
}

class Owner {
	private String name;
	
	public Owner(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
