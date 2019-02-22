package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

/**
 * The State class is meant to create each individual state, and territory;
 * allowing the client to retrieve and set values to the corresponding
 * parameters, those being the states name, its capital city, abbreviation,
 * population, region, and the number of house seats. Also, it enables the
 * client to compare each state based on its name and print the results.
 *
 * @author <Joseph Curto>
 * @version <01/30/2019>
 */

class State {

	private String name;
	private String capitalCity;
	private String abbreviation;
	private int population;
	private String region;
	private int houseSeats;

	/*
	 * The State constructor allows for the creation of State objects.
	 */

	public State(String name, String capitalCity, String abbreviation, int population, String region, int houseSeats) {
		this.name = name;
		this.capitalCity = capitalCity;
		this.abbreviation = abbreviation;
		this.population = population;
		this.region = region;
		this.houseSeats = houseSeats;

	}// end constructor

	
	/*
	 * The getter and setter methods allow the program to retrieve specific values of a particular field.
	 * As well as set specific values to particular fields.
	 */
	
	public String getStateName() {
		return this.name;
	}// end get for state

	public void setStateName(String name) {
		this.name = name;
	}// end set for

	public String getCapitalCity() {
		return this.capitalCity;
	}// end get for capital

	public void setCapitalCity(String capitalCity) {
		this.capitalCity = capitalCity;
	}// end set for capital city

	public String getAbbr() {
		return this.abbreviation;
	}// end get

	public void setAbbr(String abbreviation) {
		this.abbreviation = abbreviation;
	}// end setAbbr

	public int getPopulation() {
		return this.population;
	}// end getPop

	public void setPopulation(int population) {
		this.population = population;
	}// end setPop

	public String getRegion() {
		return this.region;
	}// end getRegion

	public void setRegion(String reg) {
		region = reg;
	}// end setRegion

	public int getHouseSeats() {
		return this.houseSeats;
	}// end getHouseSeats

	public void setHouseSeats(int houseSeats) {
		this.houseSeats = houseSeats;
	}// end setHouseSeats

	/*
	 * CompareTo method allows each individual state to be compared with one
	 * another based on their names.
	 * 
	 * @param state This takes in a state object as its parameter
	 */

	public int compareTo(State state) {
		return this.getStateName().compareTo(state.getStateName());
	}// end compareTo method

	/*
	 * The toString method allows each state field to be printed/formated in a
	 * specific way, allowing enough room to neatly fit each value.
	 */

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%1$-25s", name));
		sb.append(String.format("%1$-20s", capitalCity));
		sb.append(String.format("%1$-10s", abbreviation));
		sb.append(String.format("%1$-16s", NumberFormat.getInstance().format(population)));
		sb.append(String.format("%1$-20s", region));
		sb.append(String.format("%1$-20d", houseSeats));

		return sb.toString();
	}// end toString

} // end States class

/**
 * COP 3538: Project 1 – Array Searches and Sorts
 * <p>
 * Project1 class begins with retrieving the State.csv file from out main
 * package allowing us to retrieve and set each line of the csv file into
 * individual state objects.
 * <p>
 * <p>
 * A list will then be implemented so we can read the State.csv file line by
 * line, and associate each value in between commas as specific values: such as
 * the states name, capital city, etc
 * <p>
 * <p>
 * Next, a menu will be printed so give the client six different options to
 * choose from, the first one being to simply print the states how they are
 * displayed in the csv file. Option two allows the client to sort the states by
 * name using the bubble sort algorithm. Option 3 sorts the states based on
 * their population size, and prints the results in ascending order. Option four
 * sorts each state based on the region they reside. Lastly, Option 5 which
 * allows the client to type in a particular state and get its information only
 * printed to the console, and Option 6 which exits the program.
 * <p>
 * <p>
 * After someone properly chooses one of the six options, the results will be
 * displayed and formatted neatly into six columns, followed by the six options
 * again. The choices will continue to be given to the client until they choose
 * the sixth option which is to exit the program.
 *
 * 
 *
 * @author <Joseph Curto>
 * @version <01/30/2019>
 */

public class Project1 {
	private static int count = 0;
	private static State[] usa = new State[56];
	private static final Scanner scan = new Scanner(System.in);

	/*
	 * The main method will work as follows, First, count will keep track of
	 * whether or not we have sorted the states yet. If it hasn't been sorted,
	 * it will remain at the value zero. Next, if the CSVFile method reads a
	 * file that exists, then it will continue down to the switch statement. The
	 * switch statement will print the six choices, and allow the client to
	 * choose the option they would like to run. Depending on the option they
	 * choose to run, a specific sorting algorithm or searching algorithm will
	 * be run to find a specific value. If the client enters a invalid value,
	 * (anything other than a number one through six) the message,
	 * "You must enter a number 1 through 6." will be printed and the program
	 * will wait until a valid value is chosen.
	 */

	public static void main(String[] args) throws IOException {
		
		System.out.println("COP3538 Project 1");
		System.out.println("Instructor: Xudong Liu");
		System.out.println();
		System.out.println("Array Searches and Sorts");
		
		
		
		CSVFile();
		while (true) {
			System.out.println();

			try {
				switch (printChoices()) {
				
				default:
					throw new Exception();
					
				case 1:
					printData();
					printData();
					break;

				case 2:
					count++;
					BubbleSort();
					printData();
					break;

				case 3:
					SelectionSort();
					printData();
					break;

				case 4:
					insertionSort();
					printData();
					break;

				case 5:
					BinarySearch();
					printData();
					break;

				case 6:
					quit();
					break;

				}// end switch
			} // end try

			catch (Exception e) {
				System.out.println("You must enter a number 1 through 6, try again.");

			} // end catch
		} // end while

	}// end main method

	/*
	 * The CSVFile method allows the user to enter in a specific file, that one
	 * being the, States.csv file which will then allow the client access to the
	 * programs menu (six choices). If the client enters a invalid file name,
	 * the message, "The file you typed in does not exist, please try again."
	 * will appear. After the client provides a number one through six, the list
	 * interface will parse the csv file line by line associating each string
	 * between the delimiter(a comma) into its appropriate index. Lastly, after
	 * the csv file is done being parse, a message will be printed to the
	 * console displaying how many states objects were found.
	 * 
	 */

	private static void CSVFile() throws IOException{

		File file;
		System.out.print("Please enter the file name: ");
		String fileLocation = scan.nextLine();
		
		while (true) {
			

			file = new File(fileLocation);

			if (file.exists()) {
				System.out.println("You have found the file");
			}//end if
			
			else{
				System.out.println("The file you are looking for does not exist.");
	
			}//end else
			
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

			State state = new State(stateName, capitalCity, abbreviation, population, region, houseSeats);

			usa[i - 1] = state;
		} // end for

		System.out.println();
		System.out.println("There were " + usa.length + " state records read.");
	}// end file method

	/*
	 * The printChoices method will be used to print the six different choices
	 * after the client types in the file, then continuously until the client
	 * chooses the sixth option which will close the program.
	 */

	private static int printChoices() {

		System.out.println("1.) Print a state report");
		System.out.println("2.) Sort by State name");
		System.out.println("3.) Sort By Population");
		System.out.println("4.) Sort by Region");
		System.out.println("5.) Find and print a given state");
		System.out.println("6.) Quit Program");
		
		System.out.print("Enter your choice: ");
		int num = scan.nextInt();
		return num;

	}// end printChoices method
	
	/*
	 * The printData method is used to print the heading of the table/ as well as each states data
	 *  in a neat format. Allowing adequate room for each field.
	 */
	private static void printData() {

		System.out.println();
		System.out.printf("%-24s %-18s %-10s %-15s %-15s %-12s", "State/Territory Name", "Captial City", "Abbr", "Population", "Region", "US House Seats");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------");

		for (State state : usa) {
			System.out.println(state);

		} // end for
	}// end printData method


	/*
	 * BubbleSort method will be used to search the State object by the name of
	 * the state/territory.
	 */

	public static void BubbleSort() {
		int out, in;

		for (out = usa.length - 1; out > 1; out--) {
			for (in = 0; in < out; in++) {
				if (usa[in].compareTo(usa[in + 1]) > 0) {

					State temp = usa[in];
					usa[in] = usa[in + 1];
					usa[in + 1] = temp;
				} // end if

			} // end nested for
		} // end out for loop

	}// end BubbleSort method

	/*
	 * Selection sort will be used to sort the varying populations across the US
	 * in ascending order.
	 */

	public static void SelectionSort() {
		int in, out;
		for (out = 0; out < usa.length - 1; out++) {
			for (in = out + 1; in < usa.length; in++) {
				if (usa[in].getPopulation() < usa[out].getPopulation()) {

					State temp = usa[out];
					usa[out] = usa[in];
					usa[in] = temp;

				} // end if
			} // end nested for
		} // end for

	}// end SelectionSort

	/*
	 * Insertion Sort method will be used to sort through the regions of the
	 * country.
	 */

	public static void insertionSort() {
		int in, out;
		for (out = 1; out < usa.length; out++) {
			State temp = usa[out];
			in = out;

			while (in > 0 && usa[in - 1].getRegion().compareTo(temp.getRegion()) > 0) {
				usa[in] = usa[in - 1];
				--in;

			} // end while

			usa[in] = temp;

		} // end for
	}// end insertionSort method

	/*
	 * Binary search will search the specific state name typed in and
	 * continuously divide the 56 states in half until it finds a match, if and
	 * only if the client has previously chose to sort the csv file by State name.
	 * Otherwise, if a sorting method hasn't been chosen yet, a linear search will be used to
	 * locate the state along with its corresponding data.
	 */

	public static void BinarySearch() {

		System.out.println();
		System.out.print("Enter the state name: ");
		String name = scan.next();
		State state = null;

		if(count == 1){
			System.out.println("Binary Search");
			int i = 0;
			int j = usa.length - 1;
			int mid = (i + j) / 2;
			boolean found = false;
			
			while (!found) {
				int midVal = usa[mid].getStateName().compareToIgnoreCase(name);

				if (midVal == 0) {
					System.out.println(mid);
					found = true;
				} // end if

				else if (i == j)
					break;

				else if (midVal < 0)
					i = mid + 1;

				else
					j = mid - 1;

			} // end while
		}
		
		if(count == 0) {
			System.out.println("Linear Search");

			for (State search : usa) {
				if (search.getStateName().equalsIgnoreCase(name))
					state = search;
					break;

		} // end if

		System.out.println(String.format("%1$-19s", "Name: ") + state.getStateName());
		System.out.println(String.format("%1$-19s", "Capital City: ") + state.getCapitalCity());
		System.out.println(String.format("%1$-19s", "Abbr: ") + state.getAbbr());
		System.out.println(String.format("%1$-19s", "Population: ") + NumberFormat.getInstance().format(state.getPopulation()));
		System.out.println(String.format("%1$-19s", "Region: ") + state.getRegion());
		System.out.println(String.format("%1$-19s", "US House Seats: ") + state.getHouseSeats());
		
		}
		
	}
	

	/*
	 * The quit method is used to exit the program whenever the client chooses
	 * option six. After the client chooses the sixth option and presses enter,
	 * the message, "Thank you for running my program, have a nice day!" will
	 * appear, signaling that the program has stopped running.
	 */

	private static void quit() {
		System.out.println("Thank you for running my program, have a nice day!");
		System.exit(0);

	}// end quit method
	
}// end of Project1 class
