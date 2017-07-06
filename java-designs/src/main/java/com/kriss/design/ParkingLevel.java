package com.kriss.design;

public class ParkingLevel {

	private int index;
	private int capacity;
	private Vehicle[] slots;
	
	public ParkingLevel(int capacity) {
		this.capacity = capacity;
		slots = new Vehicle[capacity];
	}
	
	public boolean park(Vehicle v) {
		if(isFull()) return false;
		if(getParkedSlot(v) != -1) return false;
		int slot = getNextAvailableSlot();
		slots[slot] = v;
		index++;
		return true;
	}
	
	public boolean unPark(Vehicle v) {
		int slot = getParkedSlot(v);
		if(slot == -1) return false;
		slots[slot] = null;
		index--;
		return true;
	}
	
	private int getNextAvailableSlot() {
		for (int i=0; i<capacity; i++){
			if(slots[i] == null) return i;
		}
		return -1;
	}
	
	public int getParkedSlot(Vehicle v) {
		for (int i=0; i<capacity; i++){
			if(v.equals(slots[i])) return i;
		}
		return -1;
	}
	
	public boolean isFull() {
		return (index == capacity);
	}

	public Vehicle[] getSlots() {
		return slots;
	}

	public void setSlots(Vehicle[] slots) {
		this.slots = slots;
	}
	
}
