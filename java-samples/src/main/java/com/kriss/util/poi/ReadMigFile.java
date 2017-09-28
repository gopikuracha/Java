package com.kriss.util.poi;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadMigFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileInputStream inputStream = new FileInputStream("C:/Work/Projects_ODM_Migration/Docs_Analysis/ODM-Migration.xlsx");
	        Workbook workbook = new XSSFWorkbook(inputStream);
	        Sheet firstSheet = workbook.getSheetAt(0);
	        
	        for(int i=1; i<114; i++) {
	        	Row r = firstSheet.getRow(i);
	        	int count = 0;
	        	for (int j=0; j<29;j++) {
	        		Cell c = r.getCell(j);
	        		if (c == null) {
	        			System.out.println("Null... " + i + " " + j);
	        		} else {
	        			if (c.getStringCellValue() != null && !("".equals(c.getStringCellValue()))) {
		        			System.out.println("" + i + "-" + j);
		        			count++;
		        		}
	        		}
	        	}
	        	if (count == 0 ) System.out.println("Not filled : " + i + " " + count);
	        	if (count > 1 ) System.out.println("Over filled : " + i + " " + count);
	        }
	        
	        workbook.close();
	        inputStream.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
