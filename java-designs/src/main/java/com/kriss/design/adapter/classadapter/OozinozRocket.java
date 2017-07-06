package com.kriss.design.adapter.classadapter;


/**
 * @author kriss
 *	A class that extends the existing class and implements the client interface
 *	Redirects the requests from the client to the existing class
 *	class ADAPTER pattern
 *
 *	Client - RocketSim (I)
 *	Existing class - PhysicalRocket
 */
public class OozinozRocket extends PhysicalRocket implements RocketSim {

	private double time;
	
	public OozinozRocket(double burnArea, double burnRate, double fuelMass, double totalMass) {
		super(burnArea, burnRate, fuelMass, totalMass);
	}
	
	public void setSimTime(double t) {
		this.time = t;
	}

	public double getMass() {
		return getMass(time);
	}

	public double getThrust() {
		return getThrust(time);
	}

}
