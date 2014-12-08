/*
* Jennifer Parrish
* Interview Practice
* November 30, 2014
*/

import java.util.ArrayList;
import java.util.Arrays; 

/*
* Binary Search Tree class
*/

public class BST {
	private class Node {
		Node left;
		Node right;
		int data;
		
		Node (int data) {
			left = null;
			right = null;
			this.data = data;
		
		}
		
		Node (Node left, Node right, int data) {
			this.left = left;
			this.right = right;
			this.data = data;
		}
		
		public String toString() {
			return "" + data;
		}
	}
	
	private Node root;
	
	public BST() {
		root = null;
	}
	
	public BST(Node root) {
		this.root = root;
	}
	
	/*
	* Lookup a value to determine if present in BST
	*/
	
	public boolean lookup(int data) {
		return lookupHelper(this.root, data);
	}
	
	private boolean lookupHelper(Node n, int data) {
		if (n == null)
			return false;
		if (n.data == data)
			return true;
		else if (n.data > data)
			return lookupHelper(n.left, data);
		else
			return lookupHelper(n.right, data);
	}
	
	/*
	* Insert new data in tree
	*/
	
	public void insert(int data) { 
		root = insert(root, data);	
	}
	
	private Node insert(Node n, int data) {
		if (n==null)
			n = new Node(data);
		else if (n.data > data) 
			n.left = insert(n.left, data);
		else
			n.right = insert(n.right, data);
		return n;
	}
	
	/*
	* Count number of nodes in tree
	*/
	
	public int size() {
		return size(root);
	}
	
	private int size(Node n) {
		if (n == null) 
			return 0;
		else
			return 1 + size(n.left) + size(n.right);
	}
	
	/*
	* Calculate the depth of the longest branch
	*/
	
	public int maxDepth() {
		return maxDepth(root);
	}
	
	private int maxDepth(Node n) {
		if (n == null)
			return 0;
		return 1 + Math.max(maxDepth(n.left), maxDepth(n.right));
	}
	
	/*
	* Find the minimum value present in the tree
	*/
	
	public int minValue() {
		return minValue(root);
	}
	
	private int minValue(Node n) {
		while (n.left != null) {
			n = n.left;
		}
		return n.data;
	}
	
	/*
	* Create an array containing the order of a preorder walk through the tree
	*/

	public ArrayList<Integer> preOrderPrint () {
		ArrayList<Integer> preOrder = new ArrayList<Integer>();
		preOrderPrint(root, preOrder);
		return preOrder;
	}
	
	private void preOrderPrint(Node n, ArrayList<Integer> preOrder) {
		if (n == null)
			return;
		preOrder.add(n.data);
		preOrderPrint(n.left, preOrder);
		preOrderPrint(n.right, preOrder);
	}
	
	/*
	* Print the nodes in the order of a postorder walk through the tree
	*/
	
	public void postOrderPrint() {
		postOrderPrint(root);
		System.out.println();
	}
	
	private void postOrderPrint(Node n) {
		if (n == null)
			return;
		
		postOrderPrint(n.left);
		postOrderPrint(n.right);
		System.out.print(n.data + " ");
	}
	
	/*
	* Create an array containing the order of a inorder walk through the tree
	*/
	
	public ArrayList<Integer> inOrderPrint() {
		ArrayList<Integer> inOrder = new ArrayList<Integer>();
		inOrderPrint(root, inOrder);
		return inOrder;
	}
	
	private void inOrderPrint(Node n, ArrayList<Integer> inOrder) {
		if (n == null) {
			return;
		}
		inOrderPrint(n.left, inOrder);
		inOrder.add(n.data);
		inOrderPrint(n.right, inOrder);
	}
	
	/*
	* Modification of BFS that prints out the nodes in level order
	*/
	
	public void levelOrderPrint() {
		levelOrderPrint(root);
	}
	
	private void levelOrderPrint(Node n) {
		if (n == null)
			return;
		ArrayList<Node> queue = new ArrayList<Node>();
		queue.add(n);
		int nodesCurrent = 1;
		int nodesNext = 0;
		while(queue.size() > 0) {
			Node curr_node = queue.get(0);
			System.out.print(curr_node.data + " ");
			if (curr_node.left != null) {
				queue.add(curr_node.left);
				nodesNext++;
			}
			if (curr_node.right != null) {
				queue.add(curr_node.right);
				nodesNext++;
			}
			queue.remove(0);
			nodesCurrent--;
			if (nodesCurrent == 0) {
				System.out.println();
				nodesCurrent = nodesNext;
				nodesNext = 0;
			}
		}
		
	}
	
	/*
	* Determines whether the tree is a valid BST
	*/
	
	public boolean isBST() {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isBST(Node n, int max, int min) {
	   if (n==null)
	     return true;
	   if (n.data < max && n.data > min) {
	     return isBST(n.left, n.data, min) && isBST(n.right, max, n.data);
	   }
	   return false;
	
	}
	
	/*
	* Given the pre and in order traversals, make a copy of the tree
	* works for any binary tree
	*/
	
	public BST buildTreeFromPreIn(int[] preOrder, int[] inOrder, int size) {
		Node n = buildTreeFromPreIn(new Node(0), preOrder, inOrder, 0, size-1, 0, size-1);
		return new BST(n);
	
	}
	
	private Node buildTreeFromPreIn(Node n, int[] preOrder, int[] inOrder, int pre_start, int pre_end, int in_start, int in_end) {
		
		if (pre_start > pre_end || in_start > in_end)
			return null;
			
		int rootNode = preOrder[pre_start];
		n = new Node(rootNode);
		int offset = 0;
		for (int i = 0; i < inOrder.length; i++) {
			offset++;
			if (inOrder[i] == rootNode) {
				break;
			}
		}
		
		int length = offset-in_start;
	
		n.left = buildTreeFromPreIn(n.left, preOrder, inOrder, pre_start+1, pre_start+length, in_start, offset-1);
		n.right = buildTreeFromPreIn(n.right, preOrder, inOrder, pre_start+length+1, pre_end, offset+1, in_end);
		
		return n;
	
	}
	
	/*private Node buildTreeFromPreIn(Node n, int[] preOrder, int[] inOrder, int size) {
		if (size == 0)
			return null;
		
		int rootNode = preOrder[0];
		n = new Node(rootNode);
		int offset = 0;
		for (int i = 0; i < size; i++) {
			offset++;
			if (inOrder[i] == rootNode) {
				System.out.println("Offset: " + offset);
				break;
			}
		}
		
		int[] preOrderLeft = Arrays.copyOfRange(preOrder, 1, offset);
		int[] preOrderRight = Arrays.copyOfRange(preOrder, offset, size);
		int[] inOrderLeft = Arrays.copyOfRange(inOrder, 0, offset-1);	
		int[] inOrderRight = Arrays.copyOfRange(inOrder, offset, size);
	
		
		n.left = buildTreeFromPreIn(n.left, preOrderLeft, inOrderLeft, preOrderLeft.length);
		n.right = buildTreeFromPreIn(n.right, preOrderRight, inOrderRight, preOrderRight.length);
		
		return n;
	
	}*/
	
	public static void main(String[] args) {
		BST bst = new BST();
		bst.insert(4);
		bst.insert(7);
		bst.insert(1);
		bst.insert(12);
		bst.insert(5);
		bst.insert(3);
		bst.postOrderPrint();
		System.out.println("Max depth: " + bst.maxDepth());
		System.out.println("Size: " + bst.size());
		bst.levelOrderPrint();
		System.out.println("Min value: " + bst.minValue());
		
		ArrayList<Integer> inorder = bst.inOrderPrint();
		ArrayList<Integer> preorder = bst.preOrderPrint();
		
		System.out.print("Preorder: ");
		for (int i : preorder) {
			System.out.print(i + " ");
		}
		
		System.out.print("\nInorder: ");
		
		for (int i : inorder) {
			System.out.print(i + " ");
		}
		
		System.out.println();
		
		int[] preOrder = new int[preorder.size()];
		for (int i = 0; i < preorder.size(); i++) {
			preOrder[i] = preorder.get(i);
		}
		
		int[] inOrder = new int[inorder.size()];
		for (int i = 0; i < inorder.size(); i++) {
			inOrder[i] = inorder.get(i);
		}
	
		
		BST bstCopy = bst.buildTreeFromPreIn(preOrder, inOrder, preorder.size());
		System.out.println("Copy level order print:");
		bstCopy.levelOrderPrint();
		
		
	}
	



}