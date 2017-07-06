package com.kriss.design.observor.mvc.extn;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kriss.design.observor.mvc.Observable;
import com.kriss.design.observor.mvc.Observer;

public class BallisticsPanel extends JPanel implements Observer {

	JLabel label;
	double min;
	
	public BallisticsPanel(TPeak tPeak, double min) {
		this.min = min;
		tPeak.addObserver(this);
		label = new JLabel(new Double(min).toString());
		this.add(label);
	}

	public void update(Observable o, Object arg) {
		double value = (Double) o.getValue();
		label.setText("" + (min + value));
		repaint();
	}
}
