package com.kriss.util.poi;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXLSX {

	public static void main(String[] args) {
		try {
			FileInputStream inputStream = new FileInputStream("C:/Gopi/first-excel-1.xlsx");
	        Workbook workbook = new XSSFWorkbook(inputStream);
	        Sheet firstSheet = workbook.getSheetAt(0);
	        Iterator<Row> iterator = firstSheet.iterator();
	        
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	             
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                 
	                switch (cell.getCellType()) {
	                    case Cell.CELL_TYPE_STRING:
	                        System.out.print(cell.getStringCellValue());
	                        break;
	                    case Cell.CELL_TYPE_BOOLEAN:
	                        System.out.print(cell.getBooleanCellValue());
	                        break;
	                    case Cell.CELL_TYPE_NUMERIC:
	                        System.out.print(cell.getNumericCellValue());
	                        break;
	                }
	                System.out.print(" - ");
	            }
	            System.out.println();
	        }
	        
	        Row r1 = firstSheet.getRow(0);
	        Cell c1 = r1.getCell(0);
	        CellStyle cs = c1.getCellStyle();
	        
	        //Workbook wb2 = new XSSFWorkbook();
	        Sheet sh = workbook.createSheet("New");
	        Row r2 = sh.createRow(0);
	        Cell c2 = r2.createCell(1);
	        Cell c3 = r2.createCell(2);
	        c2.setCellValue("Gopi");
	        c3.setCellValue("Kriss");
	        r2.setRowStyle(cs);
	        //c2.setCellStyle(cs);
	        
	        ExcelUtil.writeOutput("C:/Gopi/first-excel-1.xlsx", workbook);
	        
	        workbook.close();
	        inputStream.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
