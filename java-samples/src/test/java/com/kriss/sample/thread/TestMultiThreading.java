package com.kriss.sample.thread;

import org.junit.Test;

public class TestMultiThreading {

	@Test
	public void testSynchronizedBlock() {
		StringBuffer sb = new StringBuffer("SB");
		SynchronizedSample pr = new SynchronizedSample(sb);
		
		Thread a = new Thread(pr, "1");
		Thread b = new Thread(pr, "2");
		Thread c = new Thread(pr, "3");
		Thread d = new Thread(pr, "4");
		
		a.start();
		b.start();
		c.start();
		d.start();
	}
	
	@Test
	public void testStaticSynchronizedMethod() {
		StringBuffer sb = new StringBuffer("SB");
		SynchronizedSample pr = new SynchronizedSample(sb);
		
		StringBuffer sb2 = new StringBuffer("SB2");
		SynchronizedSample pr2 = new SynchronizedSample(sb2);
		
		Thread a = new Thread(pr, "1");
		Thread b = new Thread(pr2, "2");
		/*Thread c = new Thread(pr2, "3");
		Thread d = new Thread(pr2, "4");*/
		
		a.start();
		b.start();
		/*c.start();
		d.start();*/
	}
	
	@Test
	public void testWaitAndNotify() {
		WaitAndNotifyOperator operator = new WaitAndNotifyOperator();
		WaitAndNotifyMachine machine = new WaitAndNotifyMachine();
		
		Thread a = new Thread(operator);
		Thread b = new Thread(machine);
		
		a.start();
		b.start();
	}
}
