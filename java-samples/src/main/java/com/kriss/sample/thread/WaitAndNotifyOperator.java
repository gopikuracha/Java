package com.kriss.sample.thread;


/**
 * @author kriss
 *	The Operator thread that takes the input from the end user
 *	Notifies the Machine thread once the instructions are captured
 */
public class WaitAndNotifyOperator implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		synchronized (this) {
			notify();
		}
	}

}
