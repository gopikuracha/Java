package com.kriss.design.observor.mvc;

import javax.swing.JLabel;

public class BallisticsLabel extends JLabel implements Observer {

	public BallisticsLabel(TPeak tPeak) {
		tPeak.addObserver(this);
	}
	
	public void update(Observable o, Object arg) {
		setText("" + ((TPeak) o).getValue());
		repaint();
	}

}
