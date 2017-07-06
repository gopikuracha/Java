package com.kriss.design.adapter.objectadapter;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class ShowRocketTable {

	public static void main(String[] args) {
		setFonts();
		JTable table = new JTable(getRocketTable());
		table.setRowHeight(36);
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(300, 100));
		display(pane, " Rockets");
	}
	
	public static void display(Component c, String title) {
		JFrame frame = new JFrame(title);
		frame.getContentPane().add(c);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private static RocketTableModel getRocketTable() {
		Rocket r1 = new Rocket("Gopi", new Dollars(100), 20);
		Rocket r2 = new Rocket("Krishna", new Dollars(60), 30);
		return new RocketTableModel(new Rocket[] {r1, r2});
	}
	
	private static void setFonts() {
		Font font = new Font("Dialog", Font.PLAIN, 18);
		UIManager.put("Table.font", font);
		UIManager.put("TableHeader.font", font);
	}
}
