package com.example.datastructureprojectthree;

public class AVL<T extends Comparable<T>> extends BSTree<T> {

	public void insert(T data) {
		if (isEmpty())
			root = new TNode<>(data);
		else {
			TNode<T> rootNode = root;
			addEntry(data, rootNode);
			root = rebalance(rootNode);
		}
	}

	public void addEntry(T data, TNode<T> rootNode) {
		assert rootNode != null;
		if (data.compareTo((T) rootNode.data) < 0) { // right into left subtree
			if (rootNode.hasLeft()) {
				TNode<T> leftChild = rootNode.left;
				addEntry(data, leftChild);
				rootNode.left = rebalance(leftChild);
			} else
				rootNode.left = new TNode<>(data);
		} else { // right into right subtree
			if (rootNode.hasRight()) {
				TNode<T> rightChild = rootNode.right;
				addEntry(data, rightChild);
				rootNode.right = rebalance(rightChild);
			} else
				rootNode.right = new TNode<>(data);
		}
	}

	public TNode<T> deletem(T data) {
		TNode<T> temp = super.deletem(data);
		if (temp != null) {
			TNode<T> rootNode = root;
			root = rebalance(rootNode);
		}
		return temp;
	}

	public int height(TNode<T> node) {
		if (node == null)
			return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	public int getHeightDifference(TNode<T> root) {
		return height(root.left) - height(root.right);
	}

	private TNode<T> rotateRight(TNode<T> root) {
		TNode<T> c = root.left;
		root.left = c.right;
		c.right = root;
		return c;
	}

	private TNode<T> rotateLeft(TNode<T> root) {
		TNode<T> c = root.right;
		root.right = c.left;
		c.left = root;
		return c;
	}

	private TNode<T> rotateRightLeft(TNode<T> root) {
		TNode<T> c = root.right;
		root.right = rotateRight(c);
		return rotateLeft(root);
	}

	private TNode<T> rotateLeftRight(TNode<T> root) {
		TNode<T> c = root.left;
		root.left = rotateLeft(c);
		return rotateRight(root);
	}

	private TNode<T> rebalance(TNode<T> nodeN) {
		int diff = getHeightDifference(nodeN);
		if (diff > 1) { // addition was in node's left subtree
			if (getHeightDifference(nodeN.left) > 0)
				nodeN = rotateRight(nodeN);
			else
				nodeN = rotateLeftRight(nodeN);
		} else if (diff < -1) { // addition was in node's right subtree
			if (getHeightDifference(nodeN.right) < 0)
				nodeN = rotateLeft(nodeN);
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
	}

	public int heightAfterRebalance() {
		return height(rebalance(root));
	}

	

	public int heightAfterRebalance(TNode<T> node) {
		return height(rebalance(node));

	}

}