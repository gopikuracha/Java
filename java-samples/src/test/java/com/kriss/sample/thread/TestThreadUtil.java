package com.kriss.sample.thread;

import org.junit.Test;

public class TestThreadUtil {

	@Test
	public void testPrintStackTrace() {
		ThreadUtil.printStackTrace();
	}
	
	@Test
	public void testPrintAllStackTrace() {
		ThreadUtil.printAllStackTrace();
	}
	
	@Test
	public void testPrintAllStackTraceOnPriority() {
		ThreadUtil.printAllStackTraceOnPriority();
	}
}
