package com.kriss.sample.java;

public class Operations {

	public static void main(String[] args) {
		
		int [] num = {'1', '2', 'h'};
		
		final char[] digits = {
			'0' , '1' , '2' , '3' , '4' , '5' ,
			'6' , '7' , '8' , '9' , 'a' , 'b' ,
			'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
			'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
			'o' , 'p' , 'q' , 'r' , 's' , 't' ,
			'u' , 'v' , 'w' , 'x' , 'y' , 'z'
		};
		
		int x = 1 << 4;
		System.out.println(x);
		
		System.out.println(17 & 15);
		
		int y = num[2 & 2];
		System.out.println(y);
		
		char ch = digits[16 & 15];
		System.out.println(ch);
	}
}
