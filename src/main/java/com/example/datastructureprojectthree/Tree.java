package com.example.datastructureprojectthree;
public class Tree<T extends Comparable<T>> {

	private TNode<T> root;

	// @author @m7md.png AKA Mohammad Faraj

	public Tree() {
		this.root = null;
	}

	public void traverseInOrder() {
		traverseInOrder(root);
	}

	private void traverseInOrder(TNode<T> node) {
		if (node != null) {
			if (node.left != null)
				traverseInOrder(node.left);
			System.out.print(node + " ");
			if (node.right != null)
				traverseInOrder(node.right);
		}
	}

}
