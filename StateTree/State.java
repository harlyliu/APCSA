import java.util.Scanner;
import java.util.ArrayList;

/**
 *	The driver program for creating and manipulating
 *	a binary tree of state information.
 *
 *	@author	Harly Liu
 *	@since	5/22/2025
 */
public class StateTree
{
	// Fields
	private BinaryTree<State> bTree;
	private final String IN_FIlE = "states2.txt";	// input file
	
	public StateTree() { }
	
	public static void main ( String [] args )
	{
		StateTree treeOrder = new StateTree();
		treeOrder.mainMenu();
	}
 
	public void mainMenu ()
	{
		String choice;

		do
		{
			System.out.println("Binary Tree algorithm menu\n");
			System.out.println("(1) Read Data from a file");
			System.out.println("(2) Print the list");
			System.out.println("(3) Search the list");
			System.out.println("(4) Delete node");
			System.out.println("(5) Count nodes");
			System.out.println("(6) Clear the list");
			System.out.println("(7) Print the level");
			System.out.println("(8) Print depth of tree");
			System.out.println("(Q) Quit\n");
			choice = Prompt.getString("Choice");
			System.out.println();

			if ('1' <= choice.charAt(0) && choice.charAt(0) <= '8')
			{
				switch (choice.charAt(0))
				{
					case '1' :	
						loadData();		
						break;
					case '2' :
						System.out.println();
						System.out.println("The tree printed inorder\n");
						bTree.printInorder();
						System.out.println();
						break;
					case '3' :
						find();
						break;
					case '4' :
						delete();
						break;
					case '5' :
						System.out.println("Number of nodes = " + size(bTree.getRoot()));
						System.out.println();
						break;
					case '6' :
						clear();
						break;
					case '7' :
						printLevel();
						break;
					case '8' :
						if (depth(bTree.getRoot(), -1) > -1)
							System.out.println("Depth of tree = " + depth(bTree.getRoot(), -1));
						else
							System.out.println("Tree empty");
						System.out.println();
						break;
				}
			}
		}
		while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
	}
	
	/**	Load the data into the binary tree */
	public void loadData() {
		Scanner fileIn = FileUtils.openToRead(IN_FILE);
		String name;			// state name
		String abbreviation;	// state abbreviation
		int population;			// state population
		int area;				// state area in sq miles
		int reps;				// number of House Reps
		String capital;			// state capital city
		int month;				// month joined Union
		int day;				// day joined Union
		int year;				// year joined Union
		for (int i = 0; i < 50; i++){
			name = fileIn.next();
			abbreviation = fileIn.next();
			population = fileIn.nextInt();
			area = fileIn.nextInt();
			reps = fileIn.nextInt();
			capital = fileIn.next();
			month = fileIn.nextInt();
			day = fileIn.nextInt();
			year = fileIn.nextInt();
			bTree.add(new State(name, abbreviation, population, area, 
				reps, capital, month, day, year));
		}
		System.out.println("Database " + IN_FILE + " is loaded!!");
		
	}
	
	/**	Find the node in the tree */
	public State find(TreeNode<State> cur, String val) {
		if (cur == null) return null;
		if (cur.getValue().getName().equalsIgnoreCase(val))
			return cur.getValue();
		State leftAns = find(curr.getLeft, val);
		State rightAns = find(curr.getRight, val);
		if (leftAns != null) return leftAns;
		if (rightAns != null) return rightAns;
		return null;
	}
	
	/** Delete a node */
	public void delete(String val) {
		State found = find(bTree.getRoot(), val);
		bTree.remove(found);
	}
	
	/**	Returns the number of nodes in the subtree - recursive
	 *	@param node		the root of the subtree
	 *	@return			the number of nodes in the subtree
	 */
	public int size(TreeNode<State> node) {
		if (node == null) return 0;
		return 1 + size(node.getLeft()) + size(node.getRight()); 
	}
	
	/**	Clear out the binary tree */
	public void clear() { 
		bTree = null;
	}
	
	/**	Print the level requested */
	public void printLevel(int n) {
		ArrayList<State> currLevel = new ArrayList<State>();
		currLevel.add(bTree);
		int cur = 1;
		while (cur < n){
			ArrayList<State> newLevel = new ArrayList<State>();
			for (State st: currLevel){
				if (st.getLeft() != null)
					newLevel.add(st.getLeft());
				if (st.getRight() != null)
					newLevel.add(st.getRight());
			}
			currLevel = newLevel;
			cur++;
		}
		System.out.println(currLevel);
	}
	
	/**	Returns the depth of the subtree - recursive
	 *	@param node		the root of the subtree
	 *	@return			the depth of the subtree
	 */
	public int depth(TreeNode<State> node, int depth) {
		if (node == null) return depth;
		return Math.max(depth(node.getLeft(), depth+1), 
			depth(node.getRight(), depth+1)); 
	}
	
}
