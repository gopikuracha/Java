package com.kriss.sample.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ClassVariablesRetriever {
	
	public static List<String> printFields(Object obj, boolean print) throws Exception {
		Class<?> objClass = obj.getClass();
	    return printFields(objClass, print);
	}
	
	public static List<String> printFields(String obj, boolean print) throws Exception {
		Class<?> objClass = Class.forName(obj);
	    return printFields(objClass, print);
	}
	
	private static List<String> printFields(Class<?> cls, boolean print) throws Exception {
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Inside printFields...");
		List<String> list = new ArrayList<String>();
	    Field[] fields = cls.getDeclaredFields();
	    for(Field field : fields) {
	        String name = field.getName();
	        if(print) System.out.println(name);
	        list.add(name);
	    }
	    System.out.println("Exit printFields...");
	    System.out.println("---------------------------------------------------------------------------------------");
	    return list;
	}
	
	public static Map<String, String> printFieldsAndValues(String obj, boolean print, boolean logDebug, boolean logError) {
		Class<?> objClass = null;
		try {
			objClass = Class.forName(obj);
		} catch (ClassNotFoundException e) {
			if(logError) System.out.println(e.getMessage());
			return null;
		}
	    return printFieldsAndValues(objClass, null, print, logDebug, logError);
	}
	
	public static Map<String, String> printFieldsAndValues(Object obj, boolean print, boolean logDebug, boolean logError) {
		Class<?> objClass = obj.getClass();
	    return printFieldsAndValues(objClass, obj, print, logDebug, logError);
	}
	
	public static Map<String, String> printFieldsAndValues(Class<?> cls, Object obj, boolean print, boolean logDebug, boolean logError) {
		if(logDebug) System.out.println("------Inside Print Fields and Values---------------------------------------------------");
		Map<String, String> map = null;
		try {
			map = new HashMap<String, String>();
		    Field[] fields = cls.getDeclaredFields();
		    for(Field field : fields) {
		    	try {
		    		String name = field.getName();
			        String value = null;
			        if(obj != null) value = field.get(obj).toString();
			        if(print) {
			        	System.out.println(name + " | " + value);
			        }
			        map.put(name, value);
		    	} catch (IllegalAccessException iae) {
					if(logError) System.out.println("Illegal Access - " + iae.getMessage());
				} catch (IllegalArgumentException e) {
					if (logError) System.out.println("Illegal Arguments - " + e.getMessage());
				}
		    }
		} catch (SecurityException se) {
			if(logError) System.out.println(se.getMessage());
		}
		if(logDebug) System.out.println("------Exiting Print Fields and Values---------------------------------------------------");
	    return map;
	}
	
	public static List<String> printMethods(Object obj, boolean print, boolean split) throws Exception {
		Class<?> objClass = obj.getClass();
	    return printMethods(objClass, print, split);
	}
	
	public static List<String> printMethods(String obj, boolean print, boolean split) throws Exception {
		Class<?> objClass = Class.forName(obj);
	    return printMethods(objClass, print, split);
	}
	
	private static List<String> printMethods(Class<?> cls, boolean print, boolean split) throws Exception {
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Inside printMethods..."); 
		List<String> list = new ArrayList<String>();
	    Method[] methods = cls.getDeclaredMethods();
	    for(Method method : methods) {
	        String name = method.getName();
	        if (split) {
	        	String[] names = StringUtils.splitByCharacterTypeCamelCase(name);
	        	name = StringUtils.join(names, " ");
	        }
	        if(print) System.out.println(name);
	        list.add(name);
	    }
	    System.out.println("Exit printMethods...");
	    System.out.println("---------------------------------------------------------------------------------------");
	    return list;
	}
	
	public static Map<String, String> printMethodsAndValues(Object obj, boolean print, boolean split, boolean logDebug, boolean logError) {
		Class<?> objClass = obj.getClass();
	    return printMethodsAndValues(objClass, obj, print, split, logDebug, logError);
	}
	
	public static Map<String, String> printMethodsAndValues(String obj, boolean print, boolean split, boolean logDebug, boolean logError) {
		Class<?> objClass = null;
		try {
			objClass = Class.forName(obj);
		} catch (ClassNotFoundException e) {
			if(logError) System.out.println(e.getMessage());
			return null;
		}
	    return printMethodsAndValues(objClass, null, print, split, logDebug, logError);
	}
	
	private static Map<String, String> printMethodsAndValues(Class<?> cls, Object obj, boolean print, boolean split, boolean logDebug, boolean logError) {
		if(logDebug) System.out.println("------ Inside Print Methods and Values -------------------------------------------------");
		Map<String, String> map = null;
		try {
			map = new HashMap<String, String>();
		    Method[] methods = cls.getDeclaredMethods();
		    for(Method method : methods) {
		    	String name = null;
		    	try {
		    		name = method.getName();
			        String value = null;
			        if (split) {
			        	String[] names = StringUtils.splitByCharacterTypeCamelCase(name);
			        	name = StringUtils.join(names, " ");
			        }
			        if(obj != null && name.contains("get")) value = method.invoke(obj, (Object[])null).toString();
			        if(print && value != null ) {
			        	System.out.println(name + " | " + value);
			        }
			        map.put(name, value);
		    	} catch (InvocationTargetException ite) {
		    		if(logError) System.out.println(name + " | " + ite.getMessage());
		    	} catch (IllegalAccessException iae) {
		    		if(logError) System.out.println(name + " | " + iae.getMessage());
		    	} catch (IllegalArgumentException ie) {
		    		if(logError) System.out.println(name + " | " + ie.getMessage());
		    	} catch (Exception e) {
		    		if(logError) {
		    			System.out.println(name);
		    			e.printStackTrace();
		    		}
		    	}
		    }
		} catch (SecurityException se) {
			if(logError) System.out.println(se.getMessage());
		}
	    if(logDebug) System.out.println("------ Exiting Print Methods and Values -------------------------------------------------");
	    return map;
	}
	
}