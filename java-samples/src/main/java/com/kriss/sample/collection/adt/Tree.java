package com.kriss.sample.collection.adt;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }
    
    public Tree(T rootData, Node<T> parent) {
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
        root.parent = parent;
    }

    public static class Node<T> {
        public T data;
        public Node<T> parent;
        public List<Node<T>> children;
    }
}
