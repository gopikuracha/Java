package com.kriss.design.observor.mvc;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ShowBallistics {
	
	private TPeak tPeak;
	private JSlider slider;
	private int sliderMin;
	private int sliderMax;
	private BallisticsLabel valueLabel;
	
	private BallisticsPanel burnPanel;
	private BallisticsPanel thrustPanel;
	
	public static void main(String[] args) {
		
		ShowBallistics bal = new ShowBallistics();
		bal.tPeak = new TPeak(0);
		bal.burnPanel = new BallisticsPanel(bal.tPeak, 20);
		bal.thrustPanel = new BallisticsPanel(bal.tPeak, 50);
		bal.valueLabel = new BallisticsLabel(bal.tPeak);
		
		JFrame frame = new JFrame("Hi Kriss");
		
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
			slider.addChangeListener(new ChangeListener() {
				
				public void stateChanged(ChangeEvent e) {
					double value = slider.getValue();
					tPeak.setTPeak(value);
				}
			});
			slider.setValue(slider.getMinimum());
		}
		return slider;
	}
	
}
