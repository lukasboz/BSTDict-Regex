/**
 * @author Lukas Bozinov 
 * Description of Class: A class that initializes a BSTNode for use in a BST (Binary Search Tree)
 */

public class BSTNode {

	// declare private instance variables for each component of the BSTNode
	private Record theItem;
	private BSTNode parent, left, right;

	/**
	 * Constructor for a BSTNode. Sets the parent, left child, and right child to
	 * null, and initializes a user-specified item to store in the node.
	 * 
	 * @param item
	 */
	public BSTNode(Record item) {
		theItem = item;
		parent = null;
		left = null;
		right = null;
	}

	//get method for the record (item)
	public Record getRecord() {
		return theItem;
	}

	//set method for the record
	public void setRecord(Record d) {
		theItem = d;
	}

	//get method for the left child of the current node
	public BSTNode getLeftChild() {
		return left;
	}

	//get method for the right child of the current node
	public BSTNode getRightChild() {
		return right;
	}

	//get method for the parent of the current node
	public BSTNode getParent() {
		return parent;
	}

	//set method for the left child of the current node
	public void setLeftChild(BSTNode u) {
		left = u;
	}
	//set method for the right child of the current node
	public void setRightChild(BSTNode u) {
		right = u;
	}
	//set method for the parent of the current node
	public void setParent(BSTNode u) {
		parent = u;
	}

	/**
	 * @return true/false depending on whether a node is a leaf
	 */
	public boolean isLeaf() {
		if (left == null && right == null) { //if both children are non-existent
			return true; //return true
		}
		return false; //otherwise return false
	}

}