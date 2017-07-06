package com.kriss.design.adapter.objectadapter;

public interface TableModel {
	
	void addTableModelListener();
	void getColumnClass();
	int getColumnCount();
	String getColumnName(int i);
	int getRowCount();
	Object getValueAt(int row, int col);
	void isCellEditable();
	void removeTableModelListener();
	void setValueAt();
	
}
