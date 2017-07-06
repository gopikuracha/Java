package com.kriss.design.observor;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ShowBallistics implements ChangeListener{
	
	private JSlider slider;
	private int sliderMin;
	private int sliderMax;
	private JLabel valueLabel;
	
	private BallisticsPanel burnPanel;
	private BallisticsPanel thrustPanel;
	
	public static void main(String[] args) {
		ShowBallistics bal = new ShowBallistics(new BallisticsPanel(20), new BallisticsPanel(30));
		JFrame frame = new JFrame("Hi");
		
		JPanel panel = new JPanel();
		panel.add(bal.valueLabel);
		panel.add(bal.slider());
		panel.add(bal.burnPanel);
		panel.add(bal.thrustPanel);
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public ShowBallistics(BallisticsPanel panel, BallisticsPanel panel2) {
		this.burnPanel = panel;
		this.thrustPanel = panel2;
		this.valueLabel = new JLabel(new Integer(sliderMin).toString());
	}

	public BallisticsPanel burnPanel() {
		return burnPanel;
	}
	
	public BallisticsPanel thrustPanel() {
		return thrustPanel;
	}
	
	public JLabel valueLabel() {
		return valueLabel;
	}
	
	public JSlider slider() {
		if(slider == null) {
			slider = new JSlider();
			sliderMin = slider.getMinimum();
			sliderMax = slider.getMaximum();
			slider.addChangeListener(this);
			slider.setValue(slider.getMinimum());
		}
		return slider;
	}
	
	public void stateChanged(ChangeEvent e) {
		double value = slider.getValue();
		//double tp = (value - sliderMin)/ (sliderMax - sliderMin);
		double tp = value;
		burnPanel().setTPeak(tp-sliderMin);
		thrustPanel().setTPeak(sliderMax-tp);
		valueLabel().setText(new Double(value).toString());
	}
}
