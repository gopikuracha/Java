package com.kriss.sample.collection.adt;

public class Queue {
	
	private int front;
	private int rear;
	private int capacity;
	private int[] array;
	
	public Queue(int size) {
		front = -1;
		rear = -1;
		capacity = size;
		array = new int[size];
	}
	
	public boolean isEmpty() {
		return (front == -1);
	}
	
	public boolean isFull() {
		return ((rear+1)%capacity == front);
	}
	
	public int getQueueSize() {
		return rear-front+1;
	}
	
	public void enQueue(int data) {
		rear = (rear+1)%capacity;
		array[rear]=data;
		if (front == -1) {
			front = rear;
		}
	}
	
	public int deQueue() {
		int data = 0;
		if (!isEmpty()) {
			data = array[front];
			if (front == rear) front = rear-1;
			else front=(front+1)%capacity;
		}
		return data;
	}
	
	public void printArray() {
		for (int i=0; i<capacity; i++) {
			System.out.print(array[i]);
			System.out.print(" ");
		}
	}
	
}