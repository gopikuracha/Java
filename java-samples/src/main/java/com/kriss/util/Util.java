package com.kriss.util;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.CollationKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Util {
	
	String s;
	Integer i;
	Float f;
	Character c;
	Long l;
	Byte b;
	Short s1;
	BigInteger bi;
	Boolean bl;
	File fl;
	BigDecimal bd;
	CollationKey key;
	Thread t;
	Class cls;
	
	Collection<String> collection;
	Set<String> set;
	SortedSet<String> sortedSet;
	NavigableSet<String> naviSet;
	List<String> list;
	Queue<String> queue;
	Map<String, String> map;
	SortedMap<String, String> sortedMap;
	NavigableMap<String, String> naviMap;
	
	HashSet<String> hashSet;
	LinkedHashSet<String> linkHashSet;
	TreeSet<String> treeSet;
	ArrayList<String> arrayList;
	Vector<String> vector;
	LinkedList<String> linkList;
	PriorityQueue<String> priorityQueue;
	HashMap<String, String> hashMap;
	Hashtable<String, String> hashtable;
	LinkedHashMap<String, String> linkHashMap;
	TreeMap<String, String> treeMap;
	ConcurrentHashMap<String, String> concurrentMap;
	
	Exception ex;
	Error error;
	
	
	
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
