package com.kriss.sample.thread;


/**
 * @author kriss
 *	Implements DeadLock concept in Threads
 *	Independent on other classes
 */
public class DeadLock {
	
	public static class Resource {
		public int value;
	}
	
	private Resource resourceA = new Resource();
	private Resource resourceB = new Resource();
	
	public int read() {
		synchronized (resourceA) {
			synchronized (resourceB) {
				return resourceA.value + resourceA.value;
			}
		}
	}
	
	public void write(int a, int b) {
		synchronized (resourceB) {
			synchronized (resourceA) {
				resourceA.value = a;
				resourceB.value = b;
			}
		}
	}
	
}
