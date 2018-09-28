package com.kriss.collection.adt;

import java.util.Arrays;

/**
 * @author krishgo
 *	
 *	Row and Column indexes starts from 0
 */
public class StaticTDS extends TabularDS {

	/**
	 * 	The values of table items in a Tabular fashion.
	 */
	private Object[][] values;
	
	public StaticTDS() {}
	
	public StaticTDS(int rows, int columns, boolean hasHeader) {
		super(rows, columns, hasHeader);
		values = new Object[rows][columns];
	}
	
	@Override
	public Object[][] getValues() {
		return values;
	}

	public void setValues(Object[][] values) {
		this.values = values;
	}
	
	@Override
	public void setValue(int row, int column, Object obj) {
		this.values[row][column] = obj;
	}
	
	@Override
	public Object getValue(int row, int column) {
		return this.values[row][column];
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("TabularDS ");
		builder.append("[rows=" + rows + ", columns=" + columns + ", columnHeaders=" + Arrays.toString(columnHeaders) + "]");
		builder.append("\n");
		builder.append("Values= [");
		builder.append("\n");
		if (values != null) {
			for(int i=0; i<rows; i++) {
				builder.append(i + " : [");
				for(int j=0; j<columns; j++) {
					if(j != 0) builder.append(" ,");
					builder.append(values[i][j]);
				}
				builder.append("]");
				builder.append("\n");
			}
		}
		builder.append("]");
		return builder.toString();
	}
	
}
