package com.kriss.design.adapter.objectadapter;

public class Skyrocket {
	
	double mass;
	double thrust;
	double burnTime;
	
	protected double simTime;
	
	public Skyrocket(double mass, double thrust, double burnTime) {
		this.mass = mass;
		this.thrust = thrust;
		this.burnTime = burnTime;
	}

	public double getMass() {
		return mass;
	}

	public double getThrust() {
		return thrust;
	}
	
	public void setSimTime(double t) {
		this.simTime = t;
	}
}
