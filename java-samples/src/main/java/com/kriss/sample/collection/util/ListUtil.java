package com.kriss.sample.collection.util;

import java.util.List;

public class ListUtil {

	public static <T> void iterateList(List<T> list) {
		for(T obj : list) {
			System.out.println(obj);
		}
		System.out.println("Size : " + list.size());
	}
}
