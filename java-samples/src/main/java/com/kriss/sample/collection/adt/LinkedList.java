package com.kriss.sample.collection.adt;

public class LinkedList {
	
	int data;
	LinkedList next;
	
	public static LinkedList insertData(LinkedList head, int data) {
		LinkedList newList = new LinkedList();
		newList.data = data;
		if (head == null) {
			head = newList;
			return head;
		}
		LinkedList temp = head;
		while (temp.next != null) {
			temp = temp.next;
	    }
	    temp.next = newList;
	    
	    return head;
	}
	
	public static void printList(LinkedList head) {
		while (head != null) {
			System.out.println(head.data);
			head = head.next;
		}
	}
	
	public static void main(String[] args) {
		LinkedList head = LinkedList.insertData(null, 2);
		head = LinkedList.insertData(head, 1);
		LinkedList.printList(LinkedList.insertData(head, 3));
	}
	
}
