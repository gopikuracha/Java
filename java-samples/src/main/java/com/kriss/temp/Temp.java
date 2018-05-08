package com.kriss.temp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Temp {

	static Date now = new Date();
	
	public static void main(String[] args) {
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(format.format(now));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printRandomNumber() {
		int count = 0;
		for(int i=0;i<10;i++) {
			String pin = String.format("%04d", new Random().nextInt(9999));
			System.out.println(pin.length() + " " + pin);
			if (pin.length() == 4) {
				count++;
			}
		}
		System.out.println("Count : " + count);
	}
}
