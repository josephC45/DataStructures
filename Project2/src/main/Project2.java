package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;


/*
 * COP 3538: Project 2 - Stacks and Priority Queues
 * 
 * <p>Project2 class first reads the file, checks whether or not the file exists, if not, an error message will be displayed
 * If the file is detected, then the program continues to run asking the user to type in Stack initially, then Priority Queue Contents
 * Stack contents initially shows just the states from the regions: South, West, and MidWest. <p>
 * <p>By using the Priority Queue Contents choice, 
 * After popping those states from the stack they are then inserted into their respective priority queues where they are then sorted
 * with the highest priority being the largest population size. 
 * After they are sorting in the priority queue, they are then removed and printed to the console in the order: South, West, and MidWest.
 * Lastly, they are all pushed back onto the original stack and printed out in a LIFO manner, resulting in states from the MidWest being 
 * printed out first, Western States printed second, and the Southern States being printed out last. 
 * After it is all complete, the program will stop.<p>
 * 
 * @author <Joseph Curto>
 * @version <02/19/19>
 */
public class Project2 {

	private static State[] usa = new State[56];
	private static Stack st = new Stack(56);

	private static Priority south = new Priority(33);
	private static Priority west = new Priority(33);
	private static Priority midWest = new Priority(33);

	private static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		System.out.println("COP3538 Project 2");
		System.out.println("Instructor: Xudong Liu");
		System.out.println();
		System.out.println("Stacks and Priority Queues");

		
		CSVFile();
		while (true) {
			System.out.println();
			System.out.print("Stack Contents OR Priority Queue Contents: ");

			String choice = scan.nextLine();

			switch (choice) {
			case "Stack Contents":
				PushState();
				PopState();
				break;
				
			case "Priority Queue Contents":
				RemoveSouthState();
				RemoveWestState();
				RemoveMidWestState();
				PopState();
				quit();
				break;

			
			}// end switch

		} // end while

	}// end main

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

		usa = new State[lines.size() - 1];

		for (int i = 1; i < lines.size(); i++) {

			String line = lines.get(i);

			String[] tableElement = line.split(",");
			String stateName = tableElement[0];
			String capitalCity = tableElement[1];
			String abbreviation = tableElement[2];
			int population = Integer.parseInt(tableElement[3]);
			String region = tableElement[4];
			int houseSeats = Integer.parseInt(tableElement[5]);

			State st = new State(stateName, capitalCity, abbreviation, population, region, houseSeats);

			usa[i - 1] = st;
		} // end for

		System.out.println("There were " + usa.length + " state records read.");
		
	}// end file method

	/*
	 * The PushState method pushes/adds each state depending on the region they are located in, onto
	 * the stack and prints how many states are contained in the stack.
	 */
	
	public static State PushState() {
		int count = 0;

		for (int i = 0; i < usa.length - 1; i++) {
			if (usa[i].getRegion().equalsIgnoreCase("South") || usa[i].getRegion().equalsIgnoreCase("West")
					|| usa[i].getRegion().equalsIgnoreCase("MidWest")) {

				st.push(usa[i]);
				count++;

			} // end if

		} // end for

		System.out.println("There were " + count + " States on the stack");
		System.out.println();

		return null;
	}// end PushState method
	
	/*
	 * The PopState method goes through the stack of States from the Southern, Western and MidWestern regions
	 * one by one checking which region they belong to, and pushes each state onto its appropriate priority queue.
	 */

	public static void PopState() {
		System.out.println();
		System.out.println("Stack Contents");
		printData();
		while (!st.isEmpty()) {
			State val = st.pop();
			if (val.getRegion().equalsIgnoreCase("South")) {
				south.insert(val);
			} else if (val.getRegion().equalsIgnoreCase("West")) {
				west.insert(val);
			} else if (val.getRegion().equalsIgnoreCase("MidWest")) {
				midWest.insert(val);
			}
			
			System.out.println(val);
			
		} // end while
		
		st.printStack();
		
	}// end PopState method

	
	/*
	 * The following three methods tells the user which state stack priority queue they are dealing with,
	 * extracts each state depending on their region (South, West, MidWest), and pushes them onto their corresponding stacks.
	 * Lastly, they are printed to the console with the state with the highest population (top priority) printing first.
	 */

	public static void RemoveSouthState() {
		System.out.println();
		System.out.println("South Priority Queue Contents: ");
		printData();
		while (!south.isEmpty()) {
			State val = south.remove();
			System.out.println(val);
			st.push(val);

		} // end while
		south.printPriority();
	}//end remove south

	public static void RemoveWestState() {
		System.out.println();
		System.out.println("West Priority Queue Contents: ");
		printData();
		while (!west.isEmpty()) {
			State val = west.remove();
			System.out.println(val);
			st.push(val);

		} // end while
		west.printPriority();
	}//end remove west

	public static void RemoveMidWestState() {
		System.out.println();
		System.out.println("MidWest Priority Queue Contents: ");
		printData();
		while (!midWest.isEmpty()) {
			State val = midWest.remove();
			System.out.println(val);
			st.push(val);

		} // end while
		midWest.printPriority();
	}//end remove midwest
	
	/*
	 * The printData method is used to print the heading of the table/ as well as each states data
	 *  in a neat format. Allowing adequate room for each field.
	 */

	private static void printData() {
		
		System.out.printf("%-24s %-18s %-10s %-15s %-15s %-12s", "State/Territory Name", "Captial City", "Abbr", "Population", "Region", "US House Seats");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------");

		
	}// end printData method
	
	/*
	 * The quit method simply exits the program after the user types in 
	 * Priority Queue Contents as their choice.
	 */
	
	private static void quit(){
		System.exit(0);
		System.out.println("You have ended the program.");
	}//end quit

}//end Project2 class
