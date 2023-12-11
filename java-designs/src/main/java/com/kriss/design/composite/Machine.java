package com.kriss.design.composite;

import java.util.Set;

public class Machine extends MachineComponent {
	
	private boolean isUp;
	private boolean stopped;
	private boolean temp;
	
	private Set<Owner> owners;
	
	public Machine() {
		
	}

	@Override
	public int getMachineCount() {
		return 1;
	}
	
	@Override
	public boolean isCompletelyUp() {
		return isUp;
	}
	
	@Override
	public void stopAll() {
		this.stopped = true;
	}
	
	@Override
	public Set<Owner> getOwners() {
		return owners;
	}
	
	@Override
	public boolean isTree(Set<MachineComponent> visited) {
		visited.add(this);
		return true;
	}

}
