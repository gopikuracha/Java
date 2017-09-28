package com.kriss.util;

public class Util {
	
	private static final String INITIAL_OBJECT_DELIMITER = "| ";
	private static final String OBJECT_DELIMITER = " | ";
	private static final String KEY_VAL_DELIMITER = ": ";
	

	public static void print(Object[] objs) {
		if (objs == null || objs.length == 0) return;
		int size = objs.length;
		System.out.print(INITIAL_OBJECT_DELIMITER);
		for (int i=0; i<size; i++) {
			System.out.print(objs[i]);
			if(i % 2 == 0) System.out.print(KEY_VAL_DELIMITER);
			else System.out.print(OBJECT_DELIMITER);
		}
		System.out.println();
	}
	
	public static void printSmallLine() {
		System.out.println("----------------------------------------------------------------------");
	}
	
	public static void printBigLine() {
		System.out.println("---------------------------------------------------------------------------------------------------------------");
	}
}
