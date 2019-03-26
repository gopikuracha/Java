package com.kriss.util.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kriss.collection.adt.DynamicTDS;
import com.kriss.collection.adt.TabularDS;

public class ExcelFileReader {
	
	public TabularDS readFile(String fileName, int sheetNumber, int rows, int columns, boolean hasHeader) {
		//TabularDS tds = new StaticTDS(rows, columns, true);
		TabularDS tds = new DynamicTDS(columns, true);
		Workbook workbook = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(inputStream);
	        Sheet sheet = workbook.getSheetAt(sheetNumber);
	        Iterator<Row> iterator = sheet.iterator();
	        
	        int i = 0;
	        if(hasHeader) i = -1;
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	            
	            int j = 0;
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                 
	                switch (cell.getCellType()) {
	                    case Cell.CELL_TYPE_STRING:
	                    	if(i == -1) tds.columnHeaders[j] = cell.getStringCellValue(); 
	                    	else tds.setValue(i, j, cell.getStringCellValue());
	                        break;
	                    case Cell.CELL_TYPE_BOOLEAN:
	                    	if(i == -1) tds.columnHeaders[j] = Boolean.toString(cell.getBooleanCellValue());
	                    	else tds.setValue(i, j, cell.getBooleanCellValue());
	                        break;
	                    case Cell.CELL_TYPE_NUMERIC:
	                    	Double cellVal = cell.getNumericCellValue();
	                    	String str = Double.toString(cellVal);
	                    	String value = str.substring(0, str.indexOf("."));
	                    	if(i == -1) tds.columnHeaders[j] = value;
	                    	else tds.setValue(i, j, value);
	                        break;
	                    default:
	                    	if(i == -1) tds.columnHeaders[j] = null;
	                    	else tds.setValue(i, j, null);
	                }
	                j++;
	            }
	            i++;
	        }
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(workbook!=null) workbook.close(); } catch(IOException ioe) {ioe.printStackTrace();}
			try { if(inputStream!=null) inputStream.close(); } catch(IOException ioe) {ioe.printStackTrace();}
		}
		return tds;
	}
}
