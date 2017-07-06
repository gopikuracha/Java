package com.kriss.design.adapter.objectadapter;

import javax.swing.table.AbstractTableModel;

/**
 * @author kriss
 *	A class that extends the abstract client class and associates the existing class
 *		to display the array of existing class in a tabular format
 *	Redirects the requests from the client to the existing class
 *	object ADAPTER pattern
 *	This pattern is used when the adapter class must draw information from more than one object
 *	
 *	Client - TableModel (I) , AbstractTableModel (C)
 *	Existing class - Rocket
 */
public class RocketTableModel extends AbstractTableModel {

	public Rocket[] rockets;
	public String[] columnNames = {"Name", "Price", "Apogee"};
	
	public RocketTableModel(Rocket[] rockets) {
		this.rockets = rockets;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int i) {
		return columnNames[i];
	}

	public int getRowCount() {
		return rockets.length;
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return rockets[row].getName();
		case 1:
			return rockets[row].getPrice();
		case 2:
			return rockets[row].getApogee();
		default:
			return null;
		}
	}
}
