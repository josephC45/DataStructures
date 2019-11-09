package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

/*Description:
 * <p> The main class begins by parsing the specified CSV file. After it is found and successfully parsed, we insert
 * states into the binary search tree. After inserting the states we traverse through the new binary search tree using an inOrder traversal
 * by calling tree.traverse(1).<p>
 * <p> After successfully inOrder traversing the BST, the specified states are deleted from the BST and you are notified which states were deleted.<p>
 * <p> Next, the tree is preOrder traversed using the tree.traverse(2).<p>
 * <p> Continuing, next we call the find method to find specified states, if the states are found then their population as well as the number of nodes
 * it took to find the state are returned. If the state is not found, then the error message will be returned.<p>
 * <p>The specified states will again be deleted from the BST, then the tree will be postOrder traversed.<p>
 * <p> Lastly, the minimum and maximum values will be returned to the console.<p>
 * 
 *@author <Joseph Curto>
 *@version <04/04/19>
 */
public class Project4 {
	private static States[] usa = new States[56];
	private static BinarySearchTree tree = new BinarySearchTree();
	private static final Scanner scan = new Scanner(System.in);
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("COP3538 Project 4");
		System.out.println("Instructor: Xudong Liu");
		System.out.println();
		System.out.println("Binary Search Tree");
		
		
		//Reads the CSV file and inserts the data into the BST
		CSVFile();
		insertState();
		
		
		//InOrder traverses the BST
		System.out.println("Inorder Traversal:");
		printData();
		tree.traverse(1);
		System.out.println();
		
		
		//Deletes the specified states from the BST
		tree.delete("California");
		tree.delete("Florida");
		tree.delete("Kentucky");
		System.out.println();
		
		
		//PreOrder traverses the BST
		System.out.println("Preorder Traversal:");
		printData();
		tree.traverse(2);
		System.out.println();
		
		
		//Finds or attempts to find the specified states from the BST
		findState("Michigan");
		findState("Guam");
		findState("Florida");
		System.out.println();
		
		//Deletes specified states
		tree.delete("Delaware");
		tree.delete("American Samoa");
		tree.delete("West Virginia");
		tree.delete("North Dakota");
		System.out.println();
		
		//PostOrder traverses the BST
		System.out.println("Postorder Traversal:");
		printData();
		tree.traverse(3);
		System.out.println();
		
		//Find the state with the smallest population within the BST
		printData();
		tree.traverse(4);
		System.out.println();
		
		//Find the state with the largest population within the BST
		printData();
		tree.traverse(5);
		System.out.println();
		
		
		System.out.println("Thank you for running my program!");
		
		//Terminates the program.
		quit();
		
	}//end main method
	
	/*
	 * The CSVFile method allows the user to enter in a specific file, that one
	 * being the, States.csv file which will then allow the client access to the
	 * programs menu (Stack or State Priority Queue). If the client enters a invalid file name,
	 * the message, "The file you typed in does not exist, please try again."
	 * will appear. After the client provides a number one through six, the list
	 * interface will parse the CSV file line by line associating each string
	 * between the delimiter(a comma) into its appropriate index. Lastly, after
	 * the CSV file is done being parse, a message will be printed to the
	 * console displaying how many states objects were found.
	 * 
	 */
	
	private static void CSVFile() throws IOException {

		File file;
		System.out.print("Please enter the file name: ");
		String fileLocation = scan.nextLine();

		while (true) {

			file = new File(fileLocation);

			if (file.exists()) {
				System.out.println("You have found the file");
			} // end if

			else {
				System.out.println("The file you are looking for does not exist.");

			} // end else

			break;

		} // end while

		List<String> lines = Files.readAllLines(file.toPath());

		usa = new States[lines.size() - 1];

		for (int i = 1; i < lines.size(); i++) {

			String line = lines.get(i);

			String[] tableElement = line.split(",");
			String stateName = tableElement[0];
			int population = Integer.parseInt(tableElement[3]);

			States st = new States(stateName,population);

			usa[i - 1] = st;
		} // end for
		
	}// end file method
	
	
	/*
	 * The insertState method inserts the 56 states and their population into the binary search tree, and
	 * returns how many states are inserted.
	 */
	public static States insertState() {
		int count = 0;
	
		for(int i = 0; i < usa.length; i++) {
			tree.insert(usa[i].getStateName(), usa[i].getPopulation());
			count++;

		} // end for
		
		System.out.println("There are " + count + " States on the Binary Search Tree");
		System.out.println();
		

		return null;
	}// end PushState method
	
	/*
	 * The findState method traverses through the binary search tree to find the state you specify in the main method.
	 * If the state is found then the population and the number of nodes it took to get to the state will be printed out.
	 * Otherwise, the error message, "You did not find the state" will be returned. 
	 */
	public static void findState(String state){
		int found = tree.find(state);
		
		if(found != -1){
			System.out.println(state + " was found with a population of " + NumberFormat.getNumberInstance().format(found) + ".");
			System.out.println();
		}//end if
		else{
			System.out.println("You did not find " + state + ".");
		}//end else
	}//end stringFind 
	
	
	
	/*
	 * The printData method is used to print the heading of the table/ as well as each states data
	 *  in a neat format. Allowing adequate room for each field.
	 */

	private static void printData() {
		
		System.out.printf("%-24s %-18s", "State/Territory Name", "Population");
		System.out.println();
		System.out.println("--------------------------------------");
	}// end printData method
	
	
	/*
	 * The quit method simply exits the program after the user types in 
	 * Priority Queue Contents as their choice.
	 */
	
	private static void quit(){
		System.exit(0);
		System.out.println("You have ended the program.");
	}//end quit

}//end class
