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
	private final String IN_FILE = "states2.txt";	// input file
	
	public StateTree() {
		bTree = new BinaryTree<State>();
	}
	
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
						String inp1;
						do{
							inp1 = Prompt.getString("Enter state name to search (Q to quit)");
							State found = find(bTree.getRoot(), inp1);
							if (found == null){
								System.out.println(inp1 + " not found");
							}
							else{
								System.out.println(found);
							}
						}while(!inp1.equalsIgnoreCase("q"));
						System.out.println();
						break;
					case '4' :
						String inp2;
						do{
							inp2 = Prompt.getString("Enter state name to delete (Q to quit)");
							delete(inp2);
						}while(!inp2.equalsIgnoreCase("q"));
						System.out.println();
						break;
					case '5' :
						System.out.println("Number of nodes = " + size(bTree.getRoot()));
						System.out.println();
						break;
					case '6' :
						clear();
						System.out.println();
						break;
					case '7' :
						int inp3;
						do{
							inp3 = Prompt.getInt("Enter level value to print (-1 to quit)");
							printLevel(inp3);
						}while(inp3 != -1);
						System.out.println();
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
		bTree = new BinaryTree<State>();
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
	public State find(TreeNode<State> curr, String stateName) {
		if (curr == null) 
			return null;
		if (curr.getValue().getName().equalsIgnoreCase(stateName))
			return curr.getValue();
		State leftRes = find(curr.getLeft(), stateName);
		State rightRes = find(curr.getRight(), stateName);
		if (leftRes != null) return leftRes;
		if (rightRes != null) return rightRes;
		return null;
		
		
	}
	
	/** Delete a node */
	public void delete(String stateName) {
		State foundState = find(bTree.getRoot(), stateName);
		if (foundState == null){
			System.out.println(stateName + " not found");
			return;
		}
		bTree.remove(foundState);
		System.out.println(foundState.getName() + " has been deleted!!");
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
		bTree = new BinaryTree<State>();
		System.out.println("Data successfully cleared.");
	}
	
	/**	Print the level requested */
	public void printLevel(int n) {
		if (n < 0){
			System.out.println();
			return;
		}
		ArrayList<TreeNode<State>> currLevel = new ArrayList<TreeNode<State>>();
		currLevel.add(bTree.getRoot());
		int cur = 0;
		while (cur < n){
			ArrayList<TreeNode<State>> newLevel = new ArrayList<TreeNode<State>>();
			for (TreeNode<State> st: currLevel){
				if (st.getLeft() != null)
					newLevel.add(st.getLeft());
				if (st.getRight() != null)
					newLevel.add(st.getRight());
			}
			currLevel = newLevel;
			cur++;
		}
		for (TreeNode<State> curSt: currLevel){
			System.out.print(curSt.getValue().getName() + " ");
		}
		System.out.println();
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
