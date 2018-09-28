package com.kriss.collection.adt;

public class TabularDS {
	
	/**
	 * 	The total # of table rows
	 */
	public int rows;
	
	/**
	 * 	The total # of table columns
	 */
	public int columns;
	
	/**
	 * 	The table Column Headers
	 */
	public String[] columnHeaders;
	
	public TabularDS() {}
	
	public TabularDS(int columns, boolean hasHeader) {
		if(columns == 0) {
			System.out.println("Invalid Columns Data...");
			return;
		}
		this.columns = columns;
		if(hasHeader) columnHeaders = new String[columns];
	}
	
	public TabularDS(int rows, int columns, boolean hasHeader) {
		if(rows == 0 || columns == 0) {
			System.out.println("Invalid input Data...");
			return;
		}
		this.rows = rows;
		this.columns = columns;
		if(hasHeader) columnHeaders = new String[columns];
	}

	public String[] getColumnHeaders() {
		return columnHeaders;
	}

	public void setColumnHeaders(String[] columnHeaders) {
		this.columnHeaders = columnHeaders;
	}
	
	public Object getValue(int row, int column) {
		return 0;
	}
	
	public void setValue(int row, int column, Object obj) {
		
	}
	
	public Object getValues() { 
		return null;
	}
}
