package com.kriss.sample.reflection;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.kriss.sample.model.Person;

public class ApacheBeanUtils {
	
	public static void testBeanUtils() {
		try {
			System.out.println(BeanUtils.getProperty(new Person("Gopi", "Krishna", "Kuracha", 32, null), "middleName"));
			System.out.println();
			System.out.println();
			Map<String,String> map = BeanUtils.describe(new Person("Gopi", "Krishna", "Kuracha", 32, null));
			for (Map.Entry<String, String> entry : map.entrySet()) { 
				System.out.println(entry.getKey() + "/" + entry.getValue());
			}
			System.out.println();
			System.out.println();
			System.out.println(ClassVariablesRetriever.printFieldsAndValues(new Person("Gopi", "Krishna", "Kuracha", 32, null), true, true, true));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
