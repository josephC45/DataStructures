package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

/*
 * Description: 
 * <p> The main class first parses through the CSV file to read the data needed.<p>
 * <p> Next, the state objects which consist of the states name and their population are inserted into the hash table.<p>
 * <p> What follows are method calls to the HashTable class which allow for insertion, deletion, find, and display of the values found inside
 * the hash table.<p>
 * 
 * @author Joseph Curto
 * @version 04/18/2019
 */
public class Project5 {

	private static States[] usa = new States[56];
	private static HashTable hash = new HashTable(113);
	private static final Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("COP3538 Project 5: Hash Tables");
		System.out.println("Instructor: Xudong Liu");
		System.out.println();
		
		
		//Reads the CSV file and inserts the data into the Hash Table
		CSVFile();
		
		//inserts initial states and displays the hash table.
		insertState();
		printData();
		hash.displayTable();
		
		//deletes specified states.
		System.out.println();
		hash.deleteSingle("Vermont");
		hash.delete("Kansas");
		hash.delete("South Carolina");
		System.out.println();
		
		//finds specified states
		hash.find("Florida");
		hash.find("Rhode Island");
		hash.find("Kansas");
		System.out.println();
		
		//deletes specified states
		hash.delete("Kentucky");
		hash.delete("Minnesota");
		hash.delete("Puerto Rico");
		hash.deleteSingle("Ohio");
		System.out.println();
		
		//Reprints table
		printData();
		hash.displayTable();
		
		//prints out the number of available spaces and the total number of collisions.
		System.out.println();
		hash.printFreeAndCollisions();
		
		
		//terminates the program
		quit();
		
	}//end main
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
	 * The insertState method inserts the 56 states and their population into the hash table, and
	 * returns how many states have been inserted.
	 */
	public static States insertState() {
		int count = 0;
	
		for(int i = 0; i < usa.length; i++) {
			hash.insert(usa[i].getStateName(), usa[i].getPopulation()); //insert function from hash class
			count++;
		} // end for
		
		System.out.println("There are " + count + " States read into the Hash Table");
		System.out.println();
	
		return null;
	}// end PushState method
	
	
	/*
	 * The printData method is used to print the heading of the table/ as well as each states data
	 *  in a neat format. Allowing adequate room for each field.
	 */
	private static void printData() {
		
		System.out.printf("%-24s", "Hash Table");
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

}
