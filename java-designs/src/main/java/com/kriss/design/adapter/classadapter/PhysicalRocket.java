package com.kriss.design.adapter.classadapter;


/**
 * @author kriss
 *	An existing class
 */
public class PhysicalRocket {
	
	public double burnArea;
	public double burnRate;
	public double fuelMass;
	public double totalMass;
	
	public PhysicalRocket(double burnArea, double burnRate, double fuelMass, double totalMass) {
		this.burnArea = burnArea;
		this.burnRate = burnRate;
		this.fuelMass = fuelMass;
		this.totalMass = totalMass;
	}
	
	public double getBurnTime() {
		return 0d;
	}
	
	public double getMass(double time) {
		return totalMass;
	}
	
	public double getThrust(double time) {
		return 0d;
	}
}
