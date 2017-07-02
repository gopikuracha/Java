package com.kriss.sample.collection.adt;

import java.util.ArrayList;
import java.util.List;

public class NodeTree<T> {

	private T data;
	private List<NodeTree<T>> childreen;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<NodeTree<T>> getChildreen() {
		if (childreen==null) childreen = new ArrayList<NodeTree<T>>();
		return childreen;
	}

	public void setChildreen(List<NodeTree<T>> childreen) {
		this.childreen = childreen;
	}

	public void addChildreen(NodeTree<T> child) {
		if (childreen==null) childreen = new ArrayList<NodeTree<T>>();
		childreen.add(child);
	}
}

