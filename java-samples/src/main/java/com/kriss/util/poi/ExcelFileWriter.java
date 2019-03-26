package com.kriss.util.poi;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kriss.collection.adt.DynamicTDS;
import com.kriss.collection.adt.TabularDS;

public class ExcelFileWriter {
	
	public boolean tryDynamicLogic;

	public void writeTDSToExcel(TabularDS tds, String fileName) {
		if(tds == null || tds.rows == 0 || tds.columns == 0 || tds.getValues() == null) { 
			System.out.println("Empty TDS Object passed...");
			return;
		}
		
		Workbook workbook = null;
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileName);
			workbook = new XSSFWorkbook();
			Sheet sh = workbook.createSheet("New");
			
			int headerIndex = 0;
			if (tds.columnHeaders != null) {
				Row header = sh.createRow(0);
				for (int h=0; h<tds.columns; h++) {
					Cell headerCell = header.createCell(h);
					headerCell.setCellValue(tds.columnHeaders[h]);
				}
				headerIndex++;
			}
			
			if (tryDynamicLogic) tryDynamicLogic(sh, (DynamicTDS) tds, headerIndex);

			for (int i=0; i<tds.rows; i++) {
				Row row = sh.createRow(i+headerIndex);
				for (int j=0; j<tds.columns; j++) {
					Cell cell = row.createCell(j);
					if (tds.getValue(i, j) != null) {
						cell.setCellValue(tds.getValue(i, j).toString());
					} else cell.setCellValue("");
				}
			}
			
			workbook.write(outputStream);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(workbook!=null) workbook.close(); } catch(IOException ioe) {ioe.printStackTrace();}
			try { if(outputStream!=null) outputStream.close(); } catch(IOException ioe) {ioe.printStackTrace();}
		}
	}
	
	// To be deprecated in future, the above method holds good for all the TDS instances.
	public void writeDynamicTDSToExcel(DynamicTDS tds, String fileName) {
		if(tds == null || tds.columns == 0 || tds.values == null) { 
			System.out.println("Empty TDS Object passed...");
			return;
		}
		
		Workbook workbook = null;
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileName);
			workbook = new XSSFWorkbook();
			Sheet sh = workbook.createSheet("New");
			
			int headerIndex = 0;
			if (tds.columnHeaders != null) {
				Row header = sh.createRow(0);
				for (int h=0; h<tds.columns; h++) {
					Cell headerCell = header.createCell(h);
					headerCell.setCellValue(tds.columnHeaders[h]);
				}
				headerIndex++;
			}

			tryDynamicLogic(sh, tds, headerIndex);
			
			workbook.write(outputStream);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(workbook!=null) workbook.close(); } catch(IOException ioe) {ioe.printStackTrace();}
			try { if(outputStream!=null) outputStream.close(); } catch(IOException ioe) {ioe.printStackTrace();}
		}
	}
	
	public void tryDynamicLogic(Sheet sh, DynamicTDS tds, int headerIndex) {
		System.out.println("Trying Dynamic Logic: ");
		int columnCounter = -1;
		int rowIndex = -1;
		Row row = null;
		for (Object obj : tds.values) {
			columnCounter++;
			if (columnCounter == 0) {
				rowIndex++;
				row = sh.createRow(rowIndex+headerIndex);
			}
			Cell cell = row.createCell(columnCounter);
			if (obj != null) cell.setCellValue(obj.toString());
			else cell.setCellValue("");
			
			if(columnCounter == (tds.columns-1)) columnCounter = -1;
		}
	}
}
