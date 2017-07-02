package com.kriss.sample.collection.util;

import java.util.Map;

import aaa.data.MapData;

/**
 * @author Gopi Krishna
 *
 *  Code Snippet to iterate a Map 
 *  How to use Generics here?
 */

public class MapUtil {
	
	public static void main(String[] args) {
		
		Map<String, String> m1 = MapData.getSampleHashMap();
		MapUtil.iterateMap(m1);
	}

	public static <K,V> void iterateMap(Map<K,V> map) {
		
		//TODO - Implement generics in this case
		for (Map.Entry<K, V> entry : map.entrySet()) { 
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	}
	
}
