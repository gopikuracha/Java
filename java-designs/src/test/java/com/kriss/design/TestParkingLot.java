package com.kriss.design;

import org.junit.Test;

public class TestParkingLot {

	@Test
	public void testParkingLot() {
		ParkingLot lot = new ParkingLot(3, 3);
		
		String[] names = {"Chevy", "Ford", "Honda", "Infy", "Hyund", "Merc", "BMW", "Audi", "Jag", "Rover"};
		for(String name : names) {
			//System.out.println(new Vehicle(name).park(lot));	One other way to park a Vehicle
			System.out.println(lot.park(new Vehicle(name)));
		}
		//System.out.println(new Vehicle("Chevy").unPark(lot)); One other way to unPark a Vehicle
		System.out.println(lot.unPark(new Vehicle("Merc")));
		System.out.println(lot.park(new Vehicle("Infy")));
		System.out.println(lot.park(new Vehicle("Hummer")));
		printParkingLot(lot);
	}
	
	public void printParkingLot(ParkingLot lot) {
		for(int i=0; i<lot.getLevelCount(); i++) {
			if(lot.getLevels()[i] != null) {
				for(int j=0; j<lot.getLevelCapacity(); j++) {
					String name = null;
					if(lot.getLevels()[i].getSlots()[j] != null) name = lot.getLevels()[i].getSlots()[j].getName();
					System.out.print("\t\t"+name);
				}
				System.out.println();
			}
		}
	}
}
