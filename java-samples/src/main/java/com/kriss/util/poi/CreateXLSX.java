package com.kriss.util.poi;

import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateXLSX {
	
	static String[][] cols = {{"Date", "Fruits", "Groceries", "Non Veg", "Milk / Curd", "Maintenance", "Others"}
								,{"1","2","2","2","2","2","2"},{"4","12","12","12","12","12","12"}};
	
	static Workbook wb = new XSSFWorkbook();
	
	static Map<String, CellStyle> map = ExcelUtil.getCellStyles(wb);

	public static void main(String[] args) {
		Sheet sh = wb.createSheet("New");
		ExcelUtil.createRow(sh, 0, map.get("style1"), cols);
		ExcelUtil.writeOutput("C:/Gopi/first-excel.xlsx", wb);
	}

}
