public class BinarySearchTree {
    private BSTNode root;

    // Constructor that creates a leaf node as the root of the tree
    public BinarySearchTree() {
        root = new BSTNode(null);
    }

    // Returns the root node of this binary search tree
    public BSTNode getRoot() {
        return root;
    }

    public BSTNode get(BSTNode r, Key k) {
        BSTNode node = get_helper(r, k);
        if (node.isLeaf()) {
            return null;
        }
        else {
            return node;
        }
    }
    private BSTNode get_helper(BSTNode r, Key k) {
        //return the node with the key k in the subtree rooted at r, return null if no such node exists
        if (r.isLeaf()) {
            return r;
        }
        else {
            if (r.getRecord().getKey().compareTo(k) == 0) { // if keys are equal
                return r;
            }
            else if (r.getRecord().getKey().compareTo(k) < 0) { // if key is greater than k
                return get_helper(r.getRightChild(), k);
            }
            else {
                return get_helper(r.getLeftChild(), k); // if key is less than k
            }
        }
    }

    public void insert (BSTNode r, Record d) throws DictionaryException {
        // Method to insert a record into the subtree rooted at r
        // Throws a DictionaryException if a record with the same key already exists
        // Get the node with the same key as the record being inserted
        BSTNode node = get_helper(r, d.getKey());

        // If a node with the same key already exists, throw an exception
        if (get(r, d.getKey()) == null) {
            // Set the record of the node to the new record
            node.setRecord(d);

            // Create new left and right children for the node
            BSTNode leftchild = new BSTNode(null);
            BSTNode rightchild = new BSTNode(null);

            // Set the left and right children of the node
            node.setLeftChild(leftchild);
            node.setRightChild(rightchild);

            // Set the parent of the left and right children to the node
            leftchild.setParent(node);
            rightchild.setParent(node);
        }
        else {
            throw new DictionaryException("Record already exists");
        }
    }

    //Deletes the node with the given key from the tree with root r.
    //Throws a DictionaryException if the tree does not store a
    // record with the given key
    public void remove(BSTNode r, Key k) throws DictionaryException {
        // Get the node with the given key
        BSTNode node = get_helper(r, k);

        // If the node doesn't exist, throw an exception
        if (node == null) {
            throw new DictionaryException("Node doesn't exist");
        }

        // Get the parent of the node
        BSTNode parent = node.getParent();

        if (node.getLeftChild().isLeaf() && node.getRightChild().isLeaf()) {
            if (parent != null) {
                // If the node is the left child of its parent, set the left child of the parent to a new leaf node
                if (parent.getLeftChild() == node) {
                    parent.setLeftChild(new BSTNode(null));
                }
                // If the node is the right child of its parent, set the right child of the parent to a new leaf node
                else if (parent.getRightChild() == node) {
                    parent.setRightChild(new BSTNode(null));
                }
            } else {
                // If the node is the root, set the record, left child, and right child of the root to null
                root.setRecord(null);
                root.setLeftChild(null);
                root.setRightChild(null);
            }
        }
        // If the node has two children
        else if (!node.getLeftChild().isLeaf() && !node.getRightChild().isLeaf()) {
            // Find the smallest node in the right subtree of the node
            BSTNode smallest = smallest(node.getRightChild());

            // Remove the smallest node from the tree
            remove(root, smallest.getRecord().getKey());

            // Set the record of the node to the record of the smallest node
            node.setRecord(smallest.getRecord());
        }
        // If the node has only a left child
        else if (!node.getLeftChild().isLeaf()) {
            if (parent != null) {
                // If the node is the left child of its parent, set the left child of the parent to the left child of the node
                if (parent.getLeftChild() == node) {
                    parent.setLeftChild(node.getLeftChild());
                }
                // If the node is the right child of its parent, set the right child of the parent to the left child of the node
                else if (parent.getRightChild() == node) {
                    parent.setRightChild(node.getLeftChild());
                }

                // Set the parent of the left child to the parent of the node
                node.getLeftChild().setParent(parent);
            } else {
                // Set the record of the node to the record of its left child
                node.setRecord(node.getLeftChild().getRecord());

                // Remove the left child of the node recursively
                remove(node.getLeftChild(), node.getLeftChild().getRecord().getKey());
            }
        }
        // If the node has only a right child
        else if (!node.getRightChild().isLeaf()) {
            if (parent != null) {
                // If the node is the left child of its parent, set the left child of the parent to the right child of the node
                if (parent.getLeftChild() == node) {
                    parent.setLeftChild(node.getRightChild());
                }
                // If the node is the right child of its parent, set the right child of the parent to the right child of the node
                else if (parent.getRightChild() == node) {
                    parent.setRightChild(node.getRightChild());
                }

                // Set the parent of the right child to the parent of the node
                node.getRightChild().setParent(parent);
            } else {
                // Set the record of the node to the record of its right child
                node.setRecord(node.getRightChild().getRecord());

                // Remove the right child of the node recursively
                remove(node.getRightChild(), node.getRightChild().getRecord().getKey());
            }
        }
    }






    //Returns the node storing the successor of the
    //given key in the tree with root r; returns null
    //if the successor does not exist.

    public BSTNode successor (BSTNode r, Key k){
        // Get the node with the given key
        BSTNode node = get_helper(r, k);

        // If the node doesn't exist, find the node with the smallest key greater than k
        if (get(r, k) == null) {
            while (node != null && node.getRecord().getKey().compareTo(k) < 0) {
                node = node.getParent();
            }
            return node;
        }

        // If the node has a right child, find the smallest node in its right subtree
        if (!node.getRightChild().isLeaf()) {
            return smallest(node.getRightChild());
        }

        // Traverse up the tree to find the successor node
        while (node != null && node.getParent() != null && node.getParent().getRightChild() == node) {
            node = node.getParent();
        }

        // Return the parent of the successor node
        return node.getParent();
    }


    //Returns the node storing the predecessor of the
    //given key in the tree with root r; returns null
    // if the predecessor does not exist.
    public BSTNode predecessor(BSTNode r, Key k) {
        // Get the node with the given key
        BSTNode node = get_helper(r, k);

        // If the node doesn't exist, find the node with the largest key smaller than k
        if (get(r, k) == null) {
            while (node != null && node.getRecord().getKey().compareTo(k) > 0) {
                node = node.getParent();
            }
            return node;
        }

        // If the node has a left child, find the largest node in its left subtree
        if (!node.getLeftChild().isLeaf()) {
            return largest(node.getLeftChild());
        }

        // Traverse up the tree to find the predecessor node
        while (node != null && node.getParent() != null && node.getParent().getLeftChild() == node) {
            node = node.getParent();
        }

        // Return the parent of the predecessor node
        return node.getParent();
    }


    //    Returns the node with the smallest key in tree with root r
    BSTNode smallest(BSTNode r) {
        if (r == null) {
            return null;
        }
        if (r.getLeftChild().isLeaf()) {
            r.getRecord();
        }
        do {
            r.getLeftChild().getRecord();
        } while(!r.getLeftChild().isLeaf());
        return r;
    }


    //Returns the node with the largest key in tree with root r.
    BSTNode largest(BSTNode r) {
        if (r == null) {
            return null;
        }
        if (r.isLeaf()) {
            r.getRecord();
        }
        do {
            r.getRightChild().getRecord();
        } while(!r.getRightChild().isLeaf());
        return r;
    }
}