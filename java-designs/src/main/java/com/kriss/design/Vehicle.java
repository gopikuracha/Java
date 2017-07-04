package com.kriss.design;

public class Vehicle {

	private String name;
	
	public Vehicle(String name) {
		this.name = name;
	}

	public boolean park(ParkingLot lot) {
		return lot.park(this);
	}
	
	public boolean unPark(ParkingLot lot) {
		return lot.unPark(this);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
