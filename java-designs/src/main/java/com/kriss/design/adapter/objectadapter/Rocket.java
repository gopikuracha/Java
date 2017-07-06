package com.kriss.design.adapter.objectadapter;

public class Rocket {
	
	private String name;
	private Dollars price;
	private double apogee;
	
	public Rocket(String name, Dollars dollars, double apogee) {
		this.name = name;
		this.price = dollars;
		this.apogee = apogee;
	}

	public String getName() {
		return name;
	}
	
	public Dollars getPrice() {
		return price;
	}
	
	public double getApogee() {
		return apogee;
	}
	
}

class Dollars {
	double value;
	
	public Dollars(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return new Double(value).toString();
	}
}
