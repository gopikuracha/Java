package com.kriss.util.poi;

import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil2 {

	public static void main(String[] args) {
		
		XSSFWorkbook wb = null;
		try{
			File file = new File("C:/Gopi/google-excel.xlsx");
			wb = new XSSFWorkbook(file);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row = sheet.getRow(5);
			XSSFCell cell = row.getCell(0);
			//System.out.println("Cell val : "+cell.getRawValue());
			//System.out.println("Text Val : "+cell.getStringCellValue());
			System.out.println("Val : "+cell.getStringCellValue());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				wb.close();
			}catch(Exception e){
				System.out.println("Couldnt close...");
				e.printStackTrace();
			}
		}
		
	}
}
