package com.kriss.design.adapter.objectadapter;

import com.kriss.design.adapter.classadapter.PhysicalRocket;


/**
 * @author kriss
 *	A class that extends the client class and associates the existing class
 *	Redirects the requests from the client to the existing class
 *	object ADAPTER pattern
 *	This pattern is used when the adapter class must draw information from more than one object
 *	
 *	Client - Skyrocket (C)
 *	Existing class - PhysicalRocket of previous example
 */
public class OozinozSkyrocket extends Skyrocket {

	private PhysicalRocket rocket;
	
	public OozinozSkyrocket(PhysicalRocket r) {
		super(r.getMass(0), r.getThrust(0), r.getBurnTime());
		this.rocket = r;
	}
	
	@Override
	public double getMass() {
		return rocket.getMass(simTime);
	}
	
	@Override
	public double getThrust() {
		return rocket.getThrust(simTime);
	}
}
