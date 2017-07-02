package com.kriss.sample.collection.util;

import java.util.Map;

/**
 * @author Gopi Krishna
 *
 *  Code Snippet to iterate a Map 
 *  How to use Generics here?
 */

public class MapUtil {

	public static <K,V> void iterateMap(Map<K,V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) { 
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	}
	
}
