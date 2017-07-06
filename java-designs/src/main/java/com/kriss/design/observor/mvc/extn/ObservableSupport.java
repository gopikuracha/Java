package com.kriss.design.observor.mvc.extn;

import java.util.ArrayList;
import java.util.List;

import com.kriss.design.observor.mvc.Observable;
import com.kriss.design.observor.mvc.Observer;

public class ObservableSupport implements Observable {

	public Object value;
	public List<Observer> obs;
	
	public ObservableSupport(Object value) {
		obs = new ArrayList<Observer>();
		this.value = value;
	}
	
	public void addObserver(Observer o) {
		obs.add(o);
	}

	public void notifyObservers() {
		for(Observer o : obs) {
			o.update(this, null);
		}
	}

	public List<Observer> getObservers() {
		return obs;
	}

	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

}
