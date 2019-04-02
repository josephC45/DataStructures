package main;

import java.text.NumberFormat;

public class States {
	

		/*
		 * The states class creates the states and their respective fields as well
		 * as formats the inputs in a specific way.
		 * 
		 * @author <Joseph Curto>
		 * 
		 * @version <03/04/19>
		 */

		private String name;
		private int population;
		

		/*
		 * The State constructor allows for the creation of State objects.
		 */

		public States(String name, int population) {
			this.name = name;
			this.population = population;
	
		}// end constructor

		/*
		 * The getter and setter methods allow the program to retrieve specific
		 * values of a particular field. As well as set specific values to
		 * particular fields.
		 */

		public String getStateName() {
			return this.name;
		}// end get for state


		public void setStateName(String name) {
			this.name = name;
		}// end set for


		public int getPopulation() {
			return this.population;
		}// end getPop

		public void setPopulation(int population) {
			this.population = population;
		}// end setPop

		

		/*
		 * CompareTo method allows each individual state to be compared with one
		 * another based on their names.
		 * 
		 * @param state This takes in a state object as its parameter
		 */

		public int compareTo(States state) {
			return this.getStateName().compareTo(state.getStateName());
		}// end compareTo method
		
		/*
		 * The toString method allows each state field to be printed/formated in a
		 * specific way, allowing enough room to neatly fit each value.
		 */

		public String toString() {
			StringBuilder sb = new StringBuilder();

			sb.append(String.format("%1$-25s", name));
			sb.append(String.format("%1$-16s", NumberFormat.getInstance().format(population)));
			
			return sb.toString();
		}// end toString
	}// end state class
