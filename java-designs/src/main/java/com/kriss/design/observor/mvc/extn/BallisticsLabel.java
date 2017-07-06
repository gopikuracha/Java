package com.kriss.design.observor.mvc.extn;

import javax.swing.JLabel;

import com.kriss.design.observor.mvc.Observable;
import com.kriss.design.observor.mvc.Observer;

public class BallisticsLabel extends JLabel implements Observer {

	public BallisticsLabel(TPeak tPeak) {
		tPeak.addObserver(this);
	}
	
	public void update(Observable o, Object arg) {
		double value = (Double) o.getValue();
		setText("" + value);
		repaint();
	}

}
