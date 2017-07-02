package com.kriss.sample.collection.util;

import java.util.List;
import java.util.Map;

public class ListUtil {

	public static <T> void iterateList(List<T> list) {
		for(T obj : list) {
			if(obj instanceof Map) {
				MapUtil.iterateMap((Map)obj);
			}
			System.out.println();
		}
		System.out.println("Size : " + list.size());
	}
}
