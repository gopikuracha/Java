package com.kriss.design.observor.mvc.extn;

import com.kriss.design.observor.mvc.Observable;
import com.kriss.design.observor.mvc.Observer;

public class TPeak {

	protected double tPeak;
	Observable obs;
	
	public TPeak(double tPeak) {
		this.tPeak = tPeak;
		obs = new ObservableSupport(tPeak);
	}
	
	public double getTPeak() {
		return tPeak;
	}
	
	public void setTPeak(double tPeak) {
		this.tPeak = tPeak;
		obs.setValue(tPeak);
		notifyObservers();
	}
	
	public void addObserver(Observer o) {
		obs.addObserver(o);
	}

	public void notifyObservers() {
		obs.notifyObservers();
	}

}
