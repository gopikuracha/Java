package com.kriss.sample.thread;

/**
 * @author kriss
 *	Implements Synchronized concepts of Multi-threading
 *		Synchronized block
 *		Synchronized on non static method
 *		Synchronized on static method
 *		Object Lock
 *		java.lang.Class Lock
 *
 */
public class SynchronizedSample implements Runnable {

	public StringBuffer str;
	
	public int balance;
	
	public static int currency = 0;
	
	public SynchronizedSample() {}
	
	public SynchronizedSample(StringBuffer sb) {
		this.str = sb;
	}
	
	/**
	 * 	Lock is on the instance variable str and not on the Object
	 */
	public void run() {
		System.out.println("I am thread " + Thread.currentThread().getName());
		//init();
		synchronized(str) {
			for (int i=0; i<10; i++) {
				System.out.println(str + "-Processing... " + Thread.currentThread().getName());
			}
		}
		addBalance();
		addMoreCurrency();
	}
	
	/**
	 * run() method holds the lock of str by other threads running in parallel
	 * 
	 * Since its not an Object lock, other threads can still run parallelProcess() method	
	 * 
	 * In the line 2, other threads will be waiting for the lock on the str (StringBuffer) 
	 * 		since its toString() method is synchronized
	 *  
	 * 	If str is removed from the code in line 2, parallel processing will happen irrespective
	 * 		of synchronized block in run() method 
	 */
	public void init() {
		for(int i=0; i<10; i++) {
			System.out.println(str + "-Initiallizing the process... " + Thread.currentThread().getName());
		}
		str.append("_"+Thread.currentThread().getName());
	}
	
	public synchronized void addBalance() {
		for(int i=0; i<10; i++) {
			balance+=20;
			System.out.println("Balance - " + Thread.currentThread().getName() + " - " + balance);
		}
	}
	
	public synchronized static void addMoreCurrency() {
		for(int i=0; i<20; i++) {
			currency+=10;
			System.out.println("Currency - " + Thread.currentThread().getName() + " - " + currency);
		}
	}
	
}
