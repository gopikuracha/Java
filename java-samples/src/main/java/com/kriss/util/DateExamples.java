package com.kriss.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateExamples {

	public static void main(String[] args) {
		System.out.println(getDate());
	}
	
	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy_hh-mm-ss");
		Date date = new Date();
		String sDate= sdf.format(date);
		System.out.println(sDate);
		return null;
	}
}
