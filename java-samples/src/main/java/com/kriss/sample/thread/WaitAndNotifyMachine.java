package com.kriss.sample.thread;


/**
 * @author kriss
 *	The Machine thread, that waits for the Operator thread to delegate the action
 *	This thread will be on wait() till the Operator thread notifies it with notify()
 *	This thread reads the instructions that are capture and does the action accordingly
 */
public class WaitAndNotifyMachine implements Runnable {

	private WaitAndNotifyOperator operator;
	
	public WaitAndNotifyMachine() {};
	
	public WaitAndNotifyMachine(WaitAndNotifyOperator operator) {
		this.operator = operator;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		synchronized (operator) {
			try {
				operator.wait();
				// TODO - Finish instructions
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			
		}
	}

}
