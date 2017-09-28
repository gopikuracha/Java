package com.kriss.sample.java;

public class StaticExample {

	public static void main(String[] args) {
		Student s1 = new Student(2);
		System.out.println(s1.getRollNo());
		System.out.println(s1.getCollege());
		System.out.println(s1.COLLEGE_ID);
	}
}

class Student {
	private int rollNo;
	private static String COLLEGE_NAME = "VIGNAN";
	public static int COLLEGE_ID = 5;
	
	public Student(int rollNo) {
		this.rollNo = rollNo;
	}
	
	public static String getCollege() {
		//int x = rollNo; <Invalid> - Cannot make a Static Reference to non-static field
		//System.out.println(rollNo); <Invalid> - Cannot make a Static Reference to non-static field
		//this.rollNo; <Invalid> - Cannot use this in static context
		return COLLEGE_NAME;
	}
	
	public int getRollNo() {
		return rollNo;
	}
}
