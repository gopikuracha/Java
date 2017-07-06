package com.kriss.design.composite.cycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProcessComposite extends ProcessComponent {
	
	List<ProcessComponent> subProcesses;

	public ProcessComposite(String name) {
		super(name);
		subProcesses = new ArrayList<ProcessComponent>();
	}
	
	public void add(ProcessComponent c) {
		subProcesses.add(c);
	}

	@Override
	public int getStepCount(Set<ProcessComponent> steps) {
		// TODO Auto-generated method stub
		return 0;
	}

}
