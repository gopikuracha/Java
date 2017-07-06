package com.kriss.design.composite;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MachineComposite extends MachineComponent {
	
	public List<MachineComponent> components;
	
	public int getMachineCount() {
		int count = 0;
		for(MachineComponent c : components) {
			count += c.getMachineCount();
		}
		return count;
	}
	
	@Override
	public boolean isCompletelyUp() {
		for(MachineComponent c : components) {
			if(!c.isCompletelyUp()) return false; 
		}
		return true;
	}
	
	@Override
	public void stopAll() {
		for(MachineComponent c : components) {
			c.stopAll();
		}
	}
	
	@Override
	public Set<Owner> getOwners() {
		Set<Owner> owners = new HashSet<Owner>();
		for(MachineComponent c : components) {
			owners.addAll(c.getOwners());
		}
		return owners;
	}
	
	@Override
	public boolean isTree(Set<MachineComponent> visited) {
		visited.add(this);
		for(MachineComponent c : components) {
			if(visited.contains(c) || !c.isTree(visited)) return false;
		}
		return true;
	}

}
