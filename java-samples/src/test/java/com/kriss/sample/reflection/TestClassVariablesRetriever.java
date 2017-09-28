package com.kriss.sample.reflection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestClassVariablesRetriever {

	
	@Test
	public static void compareClassVariables() throws Exception {
		List<String> l1 = ClassVariablesRetriever.printMethods("", false, true);
		List<String> l2 = ClassVariablesRetriever.printMethods("", false, true);
		
		List<String> similar = new ArrayList<String>(l1);
		similar.retainAll(l2);
		l1.removeAll(similar);
		l2.removeAll(similar);
		System.out.println("Similar methods...");
		for (String str : similar) System.out.println(str);
		System.out.println("Left out in L1...");
		for (String str : l1) System.out.println(str);
		System.out.println("Left out in L2...");
		for (String str : l2) System.out.println(str);
	}
}
