package com.kriss.design.observor.mvc;

import java.util.List;

public interface Observable {

	void addObserver(Observer o);
	
	void notifyObservers();
	
	List<Observer> getObservers();
	
	Object getValue();
	
	void setValue(Object value);
}
