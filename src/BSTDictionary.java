public class BSTDictionary {
	/* Ordered Dictionary ADT */

	private BinarySearchTree tree = new BinarySearchTree();

	public BSTDictionary() {
		// empty constructor
	}

	/*
	 * Returns the Record object with the Key as k, or it returns null if such a
	 * Record is not in the dictionary.
	 */
	public Record get(Key k) {

		// checks for a non-null case to make sure there is something to get from the
		// tree
		if (tree.get(tree.getRoot(), k) != null) {
			return tree.get(tree.getRoot(), k).getRecord();
		}

		return null; // otherwise return null
	}

	/*
	 * Inserts the Record d into the ordered dictionary. It throws a
	 * DictionaryException if a Record with the same Key attribute as d is already
	 * in the dictionary.
	 */
	public void put(Record d) throws DictionaryException {
		tree.insert(tree.getRoot(), d); // calls BST.java insert function

	}

	/*
	 * Removes the Record with the same Key attribute as k from the dictionary. It
	 * throws a DictionaryException if such a Record is not in the dictionary.
	 */
	public void remove(Key k) throws DictionaryException {
		tree.remove(tree.getRoot(), k); // calls BST.java remove function
	}

	/*
	 * Returns the successor of k (the Record from the ordered dictionary with
	 * smallest key larger than k); it returns null if the given key has no
	 * successor. Note that the given key k DOES NOT need to be in the dictionary.
	 */
	public Record successor(Key k) {

		// run a null check just before your successor method
		BSTNode successor = tree.successor(tree.getRoot(), k);
		if (successor != null) {
			return successor.getRecord();
		}
		return null;

	}

	/*
	 * Returns the predecessor of k (the Record from the ordered dictionary with
	 * largest key smaller than k; it returns null if the given key has no
	 * predecessor. Note that the given key k DOES NOT need to be in the dictionary.
	 */
	public Record predecessor(Key k) {
		return tree.predecessor(tree.getRoot(), k).getRecord();

	}

	/*
	 * Returns the Record with smallest key in the ordered dictionary. Returns null
	 * if the dictionary is empty.
	 */
	public Record smallest() {
		return tree.smallest(tree.getRoot()).getRecord();

	}

	/*
	 * Returns the Record with largest key in the ordered dictionary. Returns null
	 * if the dictionary is empty.
	 */
	public Record largest() {
		return tree.largest(tree.getRoot()).getRecord();

	}
}