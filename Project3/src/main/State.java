package main;

import java.text.NumberFormat;

public class State {

	/*
	 * The states class creates the states and their respective fields as well
	 * as formats the inputs in a specific way.
	 * 
	 * @author <Joseph Curto>
	 * 
	 * @version <03/04/19>
	 */

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
}// end state class
