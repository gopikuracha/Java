package com.kriss.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class StringUtil {
	
	/**
	 * Gets the count of each Character in the input String
	 * @param String
	 * @return Map with Key as Character and Value as the count of the Character in the input String
	 */
	public static Map<Character, Integer> getCharsCount(String str){
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] chrs = str.toCharArray();
		for(Character ch : chrs) {
			if(map.containsKey(ch)) map.put(ch, map.get(ch)+1);
			else map.put(ch, 1);
		}
		return map;
	}
	
	public static void printSplitString(String str, String splitStr){
		int splitIndex = str.indexOf(splitStr);
		if(splitIndex != -1) printSplitString(str, splitIndex);
		else System.out.println(str);
	}
	
	public static void printSplitString(String str, int index){
		System.out.println(str.substring(0, index));
		System.out.println(str.substring(index+3));
	}
	
	
	public static void printStringsList(List<String> strs) {
		for(String str : strs) {
			System.out.println(str);
		}
		System.out.println("Size : "+strs.size());
	}
	
	
	public static List<String> printModifiedStringsList(List<String> strs, String preTrim, String preAppend, String postTrim, 
			String postAppend, boolean sortReqd, boolean printReq) {
		List<String> newStrs = new ArrayList<String>();
		if(sortReqd) Collections.sort(strs);
		for(String str : strs) {
			str = str.trim();
			if(preTrim != null) str = str.replace(preTrim, "");
			if(postTrim != null) str = str.replace(postTrim, "");
			if(preAppend != null) str = preAppend.concat(str);
			if(postAppend != null) str = str.concat(postAppend);
			if(printReq) System.out.println(str);
			newStrs.add(str);
		}
		if(printReq) System.out.println("Size : "+strs.size());
		return newStrs;
	}
	
	
	/**
	 * Prints the complete details of the String
	 * @param String
	 */
	public static void printStringDetails(String s) {
		System.out.println("Value: " + s + " Hash Code: " + s.hashCode() + " To String: " + s.toString());
	}
	
	public void pritnToken(String str, String delimiter) {
		StringTokenizer tokenizer = new StringTokenizer(str, delimiter);
		while(tokenizer.hasMoreTokens()) System.out.println(tokenizer.nextToken());
	}

}
