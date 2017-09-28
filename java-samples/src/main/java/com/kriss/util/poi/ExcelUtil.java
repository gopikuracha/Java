package com.kriss.util.poi;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {

	public static Map<String, CellStyle> getCellStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

        short borderColor = IndexedColors.GREY_50_PERCENT.getIndex();

        CellStyle style;
        
        Font font1 = wb.createFont();
        font1.setFontHeightInPoints((short)12);
        font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(font1);
        styles.put("style1", style);
        
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)48);
        titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)12);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        monthFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        styles.put("month", style);

        Font dayFont = wb.createFont();
        dayFont.setFontHeightInPoints((short)14);
        dayFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(borderColor);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(borderColor);
        style.setFont(dayFont);
        styles.put("weekend_left", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(borderColor);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(borderColor);
        styles.put("weekend_right", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setLeftBorderColor(borderColor);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(borderColor);
        style.setFont(dayFont);
        styles.put("workday_left", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(borderColor);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(borderColor);
        styles.put("workday_right", style);

        style = wb.createCellStyle();
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(borderColor);
        styles.put("grey_left", style);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(borderColor);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(borderColor);
        styles.put("grey_right", style);

        return styles;
    }

	public static void createRow(Sheet sh, int rowNum, CellStyle cs, 
			String[] cols, int rowHeight, int colWidth) {
		Row row = sh.createRow(rowNum);
		for(int i = 0; i < cols.length; i++) {
		    Cell cell = row.createCell(i);
		    cell.setCellValue(cols[i]);
		    if (cs != null) cell.setCellStyle(cs);
		    sh.setColumnWidth(i, colWidth*256); //the column is 13 characters wide
		}
	}
	
	public static void createRow(Sheet sh, int rowNum, CellStyle cs, String[][] cols) {
		int column = 0;
		Row row = sh.createRow(rowNum);
		for(int i = 0; i < cols[0].length; i++) {
			int columns = Integer.parseInt(cols[1][i]);
			if(columns == 0) {
				System.out.println("Invalid entry");
				return;
			} else if(columns > 1) {
				sh.addMergedRegion(new CellRangeAddress(rowNum, rowNum, column, column+columns-1));
			}
		    Cell cell = row.createCell(column);
		    cell.setCellValue(cols[0][i]);
		    if (cs != null) cell.setCellStyle(cs);
		    for (int j=0; j< columns; j++) {
				//set column widths, the width is measured in units of 1/256th of a character width
		    	int colWidth = Integer.parseInt(cols[2][i]);
		    	if((j % 2) == 0 ) sh.setColumnWidth(column, colWidth*256); //the column is 13 characters wide
		    	else sh.setColumnWidth(column, 6*256); //the column is 13 characters wide
			    column++;
			}
		}
	}
	
	public static void writeOutput(String fileName, Workbook wb) {
		FileOutputStream out = null;
		try {
			 out = new FileOutputStream(fileName);
			 wb.write(out);
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (out != null) out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (wb != null) wb.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void printCellStyle() {
		
	}
	
	public static void cloneCellStyle() {
		
	}
}
