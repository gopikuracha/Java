package com.kriss.design.observor.mvc;

import java.util.ArrayList;
import java.util.List;

public class TPeak implements Observable {

	protected double tPeak;
	List<Observer> obs;
	
	public TPeak(double tPeak) {
		this.tPeak = tPeak;
		obs = new ArrayList<Observer>();
	}
	
	public double getTPeak() {
		return tPeak;
	}
	
	public void setTPeak(double tPeak) {
		this.tPeak = tPeak;
		notifyObservers();
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
		return getTPeak();
	}
	
	public void setValue(Object value) {
		setTPeak((Double)value);
	}

}
