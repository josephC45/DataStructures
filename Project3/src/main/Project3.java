package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

/*
 * COP3538 Project 3- Linked Lists
 * Description
 * <p> The main class begins by parsing the csv file, then pushing them on to the 
 * usaLinkedList stack. After doing so, the nodes along with their states data are popped from the
 * stack and inserted into the queue depending upon when they were popped from the stack.
 * If they are popped at an even number, they are inserted in the beginning of the doubly linked list.
 * On the other hand, if they are an odd number they are inserted from the end of the doubly linked list.
 * <p>
 * <p> After insertion of the nodes into the usaDoublyLL queue, they traversed checking for the specific states 
 * that we must remove from the doubly linked list. After doing so, the nodes and their state data are pushed back onto the 
 * usaLinkedList stack in the same fashion as they were removed. However this time they are being inserted at the front and end 
 * of the usaLinkedList alternating the insertion locations.<p>
 * <p> After the program goes through each method, the program exits.<p>
 * @author <Joseph Curto>
 * @version <03/14/19>
 */
public class Project3 {
	private static State[] usa = new State[56];
	private static Stack usaLinkedList = new Stack();
	private static Queue usaDoublyLL = new Queue();
	private static final Scanner scan = new Scanner(System.in);
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("COP3538 Project 3");
		System.out.println("Instructor: Xudong Liu");
		System.out.println();
		System.out.println("Linked Lists");
		
		CSVFile();
		PushState();
		
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
		
	}// end file method
	
	/*
	 * The PushState method is used to go through the usa stack and check each individual state
	 * to see if they are located in the regions: New England, Middle Atlantic or South. If so, then 
	 * they are pushed/added to the usaLinkedList stack and the counter is incremented which counts how
	 * many states fall into this stack.
	 */
	public static State PushState() {
		int count = 0;
		
		for (int i = 0; i < usa.length - 1; i++) {
			if (usa[i].getRegion().equalsIgnoreCase("New England") || usa[i].getRegion().equalsIgnoreCase("Middle Atlantic")
					|| usa[i].getRegion().equalsIgnoreCase("South")) {

				usaLinkedList.push(usa[i]);
				count++;
			}

		} // end for

		System.out.println("There were " + count + " States on the stack");
		System.out.println();

		return null;
	}// end PushState method
	
	/*
	 * The PopState method pops all the states from the usaLinkedList stack and inserts them
	 * either in the front of the queue or in the end of the queue dependent on whether they are
	 * are located at positive or negative positions in the pop order.
	 */
	public static void PopState() {
		System.out.println();
		System.out.println("Stack Contents");
		printData();
		int popCounter = 0;
		while (!usaLinkedList.isEmpty()) {
			State val = usaLinkedList.pop();

			if(popCounter % 2 != 0){
				usaDoublyLL.insertFront(val);
				popCounter++;;
			}	
			else{
				usaDoublyLL.insertEnd(val);
				popCounter++;
			}
			System.out.println(val);
		} // end while
		usaLinkedList.printStack();
	}// end PopState method
	
	
	/*
	 * The RemoveStates method removes all the nodes from the end of the queue 
	 * and pushes those values back onto the usaLinkedList stack.
	 */
	public static void RemoveStates(){
		System.out.println("Queue Contents: ");
		printData();
		while(!usaDoublyLL.isEmpty()){
			State val = usaDoublyLL.removeEnd();
			System.out.println(val);
			usaLinkedList.push(val);
		}
		usaDoublyLL.printQueue();
		
	}//end RemoveStates
	
	/*
	 * The DeleteStates method indicates which states we want to delete.
	 * As the queue is traverses the state names are checked to see whether they match up; if
	 * they do then they are removed from the queue.
	 */
	public static void DeleteStates(){
		System.out.println("");
		System.out.println("Second Queue Contents: ");
		printData();
		while(!usaDoublyLL.isEmpty()){
			State val = usaDoublyLL.removeFront();
			usaDoublyLL.findDelete(val);
			
			if(val.getStateName().equalsIgnoreCase("Massachusettes")  ||
					val.getStateName().equalsIgnoreCase("New Hampshire") ||
					val.getStateName().equalsIgnoreCase("Rhode Island") ||
					val.getStateName().equalsIgnoreCase("Maryland") ||
					val.getStateName().equalsIgnoreCase("New Jersey") ||
					val.getStateName().equalsIgnoreCase("Pennsylvania") ||
					val.getStateName().equalsIgnoreCase("Alabama") ||
					val.getStateName().equalsIgnoreCase("Kentucky") ||
					val.getStateName().equalsIgnoreCase("North Carolina")){
				
				System.out.println(val);
				usaLinkedList.push(val);
			}
			
			usaDoublyLL.printQueue();
		}
		
	}//end DeleteStates
	
	/*
	 * The ReprintStack method reprints the stack.
	 */
	public static void ReprintStack(){
		System.out.println();
		System.out.println("Second Stack:");
		printData();
		int removeCounter = 0;
		while(!usaDoublyLL.isEmpty()){
			State val = usaDoublyLL.removeFront();
			State vals = usaDoublyLL.removeEnd();
			
			if(removeCounter % 2 != 0){
				usaLinkedList.push(val);
				removeCounter++;
			}
			else{
				
				usaLinkedList.push(vals);
				removeCounter++;
			}
			
			System.out.println(val);
			System.out.println(vals);
		}
		usaLinkedList.printStack();
	}//end ReprintStack
	

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

}
