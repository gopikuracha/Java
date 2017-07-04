package com.kriss.design;

public class ParkingLot {
 
	private int levelCapacity;
	private int levelCount;
	private ParkingLevel[] levels;
	
	public ParkingLot(int levelCount, int levelCapacity) {
		this.levelCapacity = levelCapacity;
		this.levelCount = levelCount;
		levels = new ParkingLevel[levelCount];
	}
	
	public boolean park(Vehicle v) {
		if(isFull()) return false;
		if(getParkedLevel(v) != -1) return false;
		int level = getNextAvailableLevel();
		levels[level].park(v);
		return true;
	}
	
	public boolean unPark(Vehicle v) {
		int level = getParkedLevel(v);
		if(level == -1) return false;
		levels[level].unPark(v);
		return true;
	}
	
	public int getNextAvailableLevel() {
		for(int i=0; i<levelCount; i++) {
			if(levels[i] == null) {
				ParkingLevel level = new ParkingLevel(levelCapacity);
				levels[i] = level;
				return i;
			} else if(!levels[i].isFull()) return i;
		}
		return -1;
	}
	
	public int getParkedLevel(Vehicle v) {
		for(int i=0; i<levelCount; i++) {
			if(levels[i] == null) return -1;
			if (levels[i].getParkedSlot(v) != -1) return i;
		}
		return -1;
	}
	
	public boolean isFull() {
		for (int i=0; i<levelCount; i++) {
			if(levels[i] == null) return false;
			if(!levels[i].isFull()) return false;
		}
		return true;
	}

	public int getLevelCapacity() {
		return levelCapacity;
	}

	public void setLevelCapacity(int levelCapacity) {
		this.levelCapacity = levelCapacity;
	}

	public int getLevelCount() {
		return levelCount;
	}

	public void setLevelCount(int levelCount) {
		this.levelCount = levelCount;
	}

	public ParkingLevel[] getLevels() {
		return levels;
	}

	public void setLevels(ParkingLevel[] levels) {
		this.levels = levels;
	}
}
