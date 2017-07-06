package com.kriss.design.observor;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BallisticsPanel extends JPanel{

	double tPeak;
	JLabel label;
	
	public BallisticsPanel(double tPeak) {
		this.tPeak = tPeak;
		label = new JLabel(new Double(tPeak).toString());
		this.add(label);
	}
	
	public void setTPeak(double tPeak) {
		this.tPeak = tPeak;
		label.setText(new Double(tPeak).toString());
	}
}
