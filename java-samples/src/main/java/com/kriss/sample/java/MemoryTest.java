package com.kriss.sample.java;

public class MemoryTest {
	
	private static final long MEGABYTE = 1024L * 1024L;

	public static long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}
	
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Free memory is bytes: "+runtime.freeMemory());
	    System.out.println("Used memory is bytes: " + memory);
	    System.out.println("Free memory is megabytes: " + bytesToMegabytes(runtime.freeMemory()));
	}
}
