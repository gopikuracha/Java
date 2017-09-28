package com.kriss.sample.java;

public class Inheritance {

	public static void testInheritance() {
		
		System.out.println("Case 1 : V1 ");
		Vehicle v1 = new Vehicle("My Vehicle - v1");				//Memory allocated for only Vehicle object 		//

		System.out.println();
		System.out.println("Case 2 : V2 ");
		Vehicle v2 = new Car("Vehicle of type Car - v2");			//Memory allocated for both Car and Vehicle	//Default constructor of Vehicle is called first
		Vehicle v3 = null;											
		
		System.out.println();
		System.out.println("Case 3 : C1 ");
		Car c1 = new Car("Car 1");									//Memory allocated for both Car and Vehicle	//Default constructor of Vehicle is called first
		Car c2 = null;
		Car c3 = null;
		
		System.out.println();
		System.out.println("Case 4 : B1 ");
		Bike b1 = new Bike("Bike 1");								//Memory allocated for both Car and Vehicle	//Custom constructor of Vehicle is called using Super in Bike constructor
		Bike b2 = null;
		Bike b3 = null;
		
		c1.setNoOfWheels(4);
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
		
		System.out.println("Case 5 : B2 ");							// Custom constructor built
		if (c1 instanceof Vehicle) {
			b2 = new Bike((Vehicle) c1);
			b2.setName("Bike 2 from Car 1");
		}
		
		System.out.println();
		System.out.println("Case 6 : C2 ");							// Custom constructor built
		if (b1 instanceof Vehicle) {
			c2 = new Car((Vehicle) b1);
			c2.setName("Car 2 from Bike 1");
		}
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Case 7 : C3, V3 ");
		// Concept - Type Casting
		// Case 1 - Type Casting Parent to Child
		//c3 = (Vehicle) v1;  			- CompileException - Cannot convert from Vehicle to Car
		//c3 = v1						- CompileException - Cannot convert from Vehicle to Car
		//c3 = (Car) v1;				- RunTimeException - ClassCastException - Because v1 is not of type Car
		c3 = (Car) v2;					// Correct - v2 is of type Car - Type Casting is required - Both c3 and v2 point to same object
		
		// Case 2 - Type Casting Child to Parent
		v3 = c1;
		//v3 = (Car) c1;				- Correct but Not required, this is not type casting
		//v3 = (Vehicle) c1;			- Not required as Auto boxing to Parent will be done
		//v3 = (Bike) c1;				- CompileException - Cannot convert from Car to Bike
		
		// Case 3 - Type Casting Child to Child
		//b3 = c1;						- CompileException - Cannot convert from Car to Bike
		//b3 = (Car) c1;				- CompileException - Cannot convert from Car to Bike -- No Use of casting
		//b3 = (Bike) c1;				- CompileException - Cannot cast from Car to Bike

		System.out.println();
		System.out.println(v1);
		System.out.println(v2);
		System.out.println(v3);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		
		v1.printClass();
		v2.printClass();
		
	}
}


class Vehicle {

	public int noOfWheels;
	public String name;
	
	public Vehicle() {
		System.out.println("Vehicle Constructor : " + this);
	}
	
	public Vehicle(int noOfWheels) {
		this.noOfWheels = noOfWheels;
		System.out.println("Vehicle Constructor (Wheels) : " + this);
	}
	
	public Vehicle(String name) {
		this.name = name;
		System.out.println("Vehicle Constructor (Name) : " + this);
	}
	
	public Vehicle(int noOfWheels, String name) {
		this.noOfWheels = noOfWheels;
		this.name = name;
		System.out.println("Vehicle Constructor (Wheels,Name) : " + this);
	}
	
	public static void printClass() {
		System.out.println("Vehicle class...");
	}
	
	public int getNoOfWheels() {
		return noOfWheels;
	}
	
	public void setNoOfWheels(int noOfWheels) {
		this.noOfWheels = noOfWheels;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\"" + name + "\" - Vehicle-" + super.toString() + "[noOfWheels=" + noOfWheels + ", name=" + name + "]";
	}
	
}


class Bike extends Vehicle{
	
	public static final int NO_OF_WHEELS = 2;

	public String engineType;
	
	public Bike() {
		//super(NO_OF_WHEELS);
		System.out.println("Bike Constructor : " + this);
	}
	
	public Bike(String name) {
		super(NO_OF_WHEELS, name);													//Constructor call must be the first statement in the Constructor
		System.out.println("Bike Constructor (Name) : " + this);
	}
	
	public Bike(Vehicle v) {
		super(v.getNoOfWheels(), v.getName());
		System.out.println("Bike Constructor (Vehicle) : " + this);
	}
	
	public static void printClass() {
		System.out.println("Bike class...");
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	
	@Override
	public String toString() {
		return super.toString() + " Bike-" + "[engineType=" + engineType + ", noOfWheels=" + noOfWheels
				+ ", name=" + name + "]";
	}
	
}


class Car extends Vehicle{

	public static final int NO_OF_WHEELS = 4;
	
	public int noOfDoors;
	
	public Car() {
		System.out.println("Car Constructor : " + this);
	}
	
	public Car(String name) {
		this.name = name;
		System.out.println("Car Constructor (Name) : " + this);
	}
	
	public Car(int noOfDoors) {
		this.noOfDoors = noOfDoors;
		System.out.println("Car Constructor (Doors) : " + this);
	}
	
	public Car(Vehicle v) {
		super(v.getNoOfWheels(), v.getName());
		System.out.println("Car Constructor (Vehicle) : " + this);
	}

	public int getNoOfDoors() {
		return noOfDoors;
	}

	public void setNoOfDoors(int noOfDoors) {
		this.noOfDoors = noOfDoors;
	}

	@Override
	public String toString() {
		return super.toString() + " Car-" + "[noOfDoors=" + noOfDoors + ", noOfWheels=" + noOfWheels
				+ ", name=" + name + "]";
	}
	
}
