package com.kriss.sample.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.kriss.sample.collection.adt.Queue;

public class ConsoleInput {
	
	//TODO - Need to be re-factored
	
    public static void main(String args[] ) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        Queue que = new Queue(N);
        char[] ch = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
        	int data = (int) Character.getNumericValue(ch[i]);
        	que.enQueue(data);
        }
        String inputLine = null;
        while(!("".equals(inputLine = br.readLine()))) {
        	int opr = Integer.parseInt(inputLine);
        	int data = Integer.parseInt(br.readLine());
        	if (opr == 1) {
        		if(que.isFull()) {
        			System.out.println("Queue is full");
        			int newSize = que.getQueueSize()+1;
        			System.out.println("New Size.. " + newSize);
        			Queue tempQue = new Queue(newSize);
        			for (int i=1; i<newSize; i++) {
        				System.out.println("moving...");
        				tempQue.enQueue(que.deQueue());
        			}
        			que = tempQue;
        		}
        		que.enQueue(data);
        	}
        	else que.deQueue();
        }
        que.printArray();
    }
}
