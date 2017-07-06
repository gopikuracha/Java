package com.kriss.design.composite.cycle;

import java.util.Set;

public class ProcessSequence extends ProcessComposite {

	public ProcessSequence(String name) {
		super(name);
	}
	
	@Override
	public int getStepCount(Set<ProcessComponent> steps) {
		// TODO Auto-generated method stub
		return super.getStepCount(steps);
	}

}
