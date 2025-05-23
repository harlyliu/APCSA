/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *
 *	@author Harly Liu
 *	@since 5/17/2025
 */

import java.util.ArrayList;

public class BinaryTree<E extends Comparable<E>> {

    private TreeNode<E> root;		// the root of the tree

    private final int PRINT_SPACES = 3;	// print spaces between tree levels
    // used by printTree()

    /**	constructor for BinaryTree */
    public BinaryTree() {
        root = null; 
    }

    /** copy constructor*/
    public BinaryTree(TreeNode<E> rootIn) {
        root = rootIn;
    }
    
    public TreeNode<E> getRoot(){
		return root;
	}

    /**	Field accessors and modifiers */

    /**	Add a node to the tree
     *	@param value		the value to put into the tree
     */
    public void add(E value) {
        TreeNode<E> newNode = new TreeNode<E>(value);
        if (root == null) {
            root = newNode;
            return;
        }
        TreeNode<E> currNode = root;
        while (true) {
            if (value.compareTo(currNode.getValue()) < 0) {
                if (currNode.getLeft() == null) {
                    currNode.setLeft(newNode);
                    break;
                } else {
                    currNode = currNode.getLeft();
                }
            } else { 
                if (currNode.getRight() == null) {
                    currNode.setRight(newNode);
                    break;
                } else {
                    currNode = currNode.getRight();
                }
            }
        }
    }
	
	/**Recursive version of add. 
	 * @param value the value to put into the tree
	 */
    public void addRecursion(E value) {
        if (root == null) {
            root = new TreeNode<E>(value);
        } else {
            addRecursionHelper(value, root);
        }
    }

	/**Helper method for recursive version. Determines if value
	 * is on left or right of subtree and recur accordingly
	 * @param value
	 * @param subroot, the current part of the tree we are adding value to
	 */
    public void addRecursionHelper(E value, TreeNode<E> subRoot) {
        if (subRoot == null) {
            return;
        }
        if (value.compareTo(subRoot.getValue()) < 0) {
            if (subRoot.getLeft() == null) {
                subRoot.setLeft(new TreeNode<E>(value));
            } else {
                addRecursionHelper(value, subRoot.getLeft());
            }
        } else { 
            if (subRoot.getRight() == null) {
                subRoot.setRight(new TreeNode<E>(value));
            } else {
                addRecursionHelper(value, subRoot.getRight());
            }
        }

    }

    /**
     *	Print Binary Tree Inorder
     */
    public void printInorder() {
        ArrayList<TreeNode<E>> nodes = new ArrayList<>();
        printInorderHelper(root, nodes);
        for (TreeNode<E> node : nodes) {
            System.out.print(node.getValue() + " ");
        }
        System.out.println();
    }

	/**Inorder helper adds all values to an arraylist in order
	 */
    private void printInorderHelper(TreeNode<E> curr, ArrayList<TreeNode<E>> order) { 
        if (curr == null) return;
        printInorderHelper(curr.getLeft(), order);
        order.add(curr); 
        printInorderHelper(curr.getRight(), order);
    }

    /**
     *	Print Binary Tree Preorder
     */
    public void printPreorder() {
        printPreOrderHelper(root);
        System.out.println();
    }

	/**Uses recursion to print each subtree in preorder
	 */
    private void printPreOrderHelper(TreeNode<E> curr) { 
        if (curr == null) return;
        System.out.print(curr.getValue() + " ");
        printPreOrderHelper(curr.getLeft());
        printPreOrderHelper(curr.getRight());
    }

    /**
     *	Print Binary Tree Postorder
     */
    public void printPostorder() {
        printPostOrderHelper(root);
        System.out.println();
    }
	
	/**Uses recursion to print each subtree in postorder
	 */
    private void printPostOrderHelper(TreeNode<E> curr) {
        if (curr == null) return;
        printPostOrderHelper(curr.getLeft());
        printPostOrderHelper(curr.getRight());
        System.out.print(curr.getValue() + " ");
    }

    /**	Return a balanced version of this binary tree
     *	@return		the balanced tree
     */
    public BinaryTree<E> makeBalancedTree() {
        BinaryTree<E> balancedTree = new BinaryTree<E>();
        ArrayList<TreeNode<E>> nodesInOrder = new ArrayList<>();
        printInorderHelper(root, nodesInOrder);
        TreeNode<E> newRoot = balancedTreeHelper(nodesInOrder, 0, nodesInOrder.size() - 1);
        balancedTree.root = newRoot; 
        return balancedTree;
    }

	/**Returns treenode that is root of a balanced tree given notes in order
	 * @param nodes. all nodes in order
	 * @param left. the left bound of the nodes that needs to be turned into bt
	 * @param right. the right bound of the nodes that needs to be turned into bt
	 */
    public TreeNode<E> balancedTreeHelper(ArrayList<TreeNode<E>> nodes, int left, int right) { 
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode<E> newNode = new TreeNode<E>(nodes.get(mid).getValue()); 
        TreeNode<E> leftNode = balancedTreeHelper(nodes, left, mid - 1);
        TreeNode<E> rightNode = balancedTreeHelper(nodes, mid + 1, right);
        newNode.setLeft(leftNode);
        newNode.setRight(rightNode);
        return newNode;
    }

    /**
     *	Remove value from Binary Tree
     *	@param value		the value to remove from the tree
     *	Precondition: value exists in the tree
     */
    public void remove(E value) {
        root = remove(root, value);
    }
    
    /**
     *	Remove value from Binary Tree
     *	@param node			the root of the subtree
     *	@param value		the value to remove from the subtree
     *	@return				TreeNode that connects to parent
     */
    public TreeNode<E> remove(TreeNode<E> node, E value) {
        if (node == null) return null;
        if (value.compareTo(node.getValue()) < 0) {
            node.setLeft(remove(node.getLeft(), value));
        } 
        else if (value.compareTo(node.getValue()) > 0) { 
            node.setRight(remove(node.getRight(), value));
        } 
        else { 
            if (node.getLeft() == null) {
                return node.getRight();
            } 
            else if (node.getRight() == null) {
                return node.getLeft();
            } 
            else {
                TreeNode<E> succ = getSuccessor(node);
                node.setRight(remove(node.getRight(), succ.getValue())); 
                succ.setLeft(node.getLeft());
                succ.setRight(node.getRight());
                return succ;
            }
        }
        return node;
    }
	
	/**Given root, find the smallest element in the right subtree
	 */
    public TreeNode<E> getSuccessor(TreeNode<E> node) { 
        node = node.getRight();
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /*******************************************************************************/
    /********************************* Utilities ***********************************/
    /*******************************************************************************/
    /**
     *	Print binary tree
     *	@param root		root node of binary tree
     *
     *	Prints in vertical order, top of output is right-side of tree,
     *			bottom is left side of tree,
     *			left side of output is root, right side is deepest leaf
     *	Example Integer tree:
     *			  11
     *			/	 \
     *		  /		   \
     *		5			20
     *				  /	  \
     *				14	   32
     *
     *	would be output as:
     *
     *				 32
     *			20
     *				 14
     *		11
     *			5
     ***********************************************************************/
    public void printTree() {
        printLevel(root, 0);
    }

    /**
     *	Recursive node printing method
     *	Prints reverse order: right subtree, node, left subtree
     *	Prints the node spaced to the right by level number
     *	@param node		root of subtree
     *	@param level	level down from root (root level = 0)
     */
    private void printLevel(TreeNode<E> node, int level) { // Made it private
        if (node == null) return;
        // print right subtree
        printLevel(node.getRight(), level + 1);
        // print node: print spaces for level, then print value in node
        for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
        System.out.println(node.getValue());
        // print left subtree
        printLevel(node.getLeft(), level + 1);
    }
}
