package com.kriss.sample.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.kriss.learning.ClassVariablesRetriever;
import com.kriss.util.Util;


/**
 * @author kriss
 *	Utility class for Threads
 */
public class ThreadUtil {
	
	/**
	 * Prints the Stack Trace of a Thread
	 * @param thread
	 */
	public static void printThreadStackTrace(Thread thread) {
		Util.printBigLine();
		//ClassVariablesRetriever.printFieldsAndValues(thread, true, false, false);
		ClassVariablesRetriever.printMethodsAndValues(thread, true, true, false, true);
		Util.print(new Object[] {"Thread Id", thread.getId(), "Thread Name", thread.getName(),
					"Thread State", thread.getState(), "Thread Priority", thread.getPriority(), "Thread alive", thread.isAlive()});
		for (StackTraceElement ste : thread.getStackTrace()) {
			System.out.println(ste);
		}
		Util.printBigLine();
	}
	
	
	/**
	 * Print the Stack Trace of a Thread
	 * @param thread
	 * @param StackTraceElement array
	 */
	public static void printThreadStackTrace(Thread thread, StackTraceElement[] steList) {
		Util.printBigLine();
		Util.print(new Object[] {"Thread Id", thread.getId(), "Thread Name", thread.getName(),
				"Thread State", thread.getState(), "Thread Priority", thread.getPriority(), "Thread alive", thread.isAlive()});
		for (StackTraceElement ste : steList) {
			System.out.println(ste);
		}
		Util.printBigLine();
	}
	
	
	/**
	 * Prints the Stack Trace for the Current Thread that is in progress
	 */
	public static void printStackTrace() {
		printThreadStackTrace(Thread.currentThread());
	}
	
	
	/**
	 * Prints the Stack Trace for all the Threads that are in progress
	 */
	public static void printAllStackTrace() {
		Map<Thread, StackTraceElement[]> allThreads = Thread.getAllStackTraces();
		for (Map.Entry<Thread, StackTraceElement[]> entry : allThreads.entrySet()) {
			//printThreadStackTrace(entry.getKey(), entry.getValue());
			printThreadStackTrace(entry.getKey());
		}
	}
	
	
	/**
	 * Prints the Stack Trace on priority order for all the Threads that are in progress
	 */
	public static void printAllStackTraceOnPriority() {
		List<Thread> threads = new ArrayList<Thread>();
		Map<Thread, StackTraceElement[]> allThreads = Thread.getAllStackTraces();
		for (Map.Entry<Thread, StackTraceElement[]> entry : allThreads.entrySet()) {
			threads.add(entry.getKey());
		}
		
		Collections.sort(threads, new Comparator<Thread>() {
			public int compare(Thread o1, Thread o2) {
				return o1.getPriority() - o2.getPriority();
			}
		});

		for(Thread t : threads) { 
			printThreadStackTrace(t);
		}
	}
	
}
