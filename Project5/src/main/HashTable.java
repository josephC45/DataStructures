package main;

import java.text.NumberFormat;

/*
 * Description: 
 * <p> The Hash Table class creates the hash table and implements seperate chaining to take care of any instances of collision that may occur
 * due to hash values being the same.<p>
 * <p> In addition, insert, delete, find, and display methods are included in the hash table.<p>
 * 
 * @author Joseph Curto
 * @version 04/18/2019
 */
public class HashTable {
	private LinkedList [] hashArray;
	private int arraySize = 113;
	private int ar = 0;
	private int collisions = 0;
	
	
	public HashTable(int size){
		arraySize = size;
		hashArray = new LinkedList[arraySize];
		for(int i = 0; i < arraySize; i++){
			hashArray[i] = new LinkedList(); //fills array with linked lists
		}//end for
		
	}//empty constructor

	/*
	 * The hashFunc method generates a hash code value of the states name by adding each character value together 
	 * and using the modulus operator against the array size of 113.
	 * 
	 * @param The string state is being passed as a parameter since we are basing the hash code on the states name.
	 * @return The hash value is returned which corresponds to the name of the state.
	 */
	public int hashFunc(String state){
		int val = 0;
		
		for(int j = 0; j < state.length(); j++){
			char st = state.charAt(j);
			 val = val + (int)st;
		}
		return val % arraySize;
	}//end hashFunc
	
	/*
	 * The insert method inserts the node into the hash table at its appropriate key which is determined by 
	 * the hash function.
	 * 
	 * @param The string state and the population are passed as parameters because they are what make up the state object which is 
	 * being inserted into the hash table.
	 */
	public void insert(String state, int population){
		Node newNode = new Node(state, population);
		String key = newNode.stateName;
		int hashVal = hashFunc(key);
		hashArray[hashVal].insertLast(newNode);
		
	}//end insert
	
	/*
	 * The delete method uses the hash function previously defined to search for the specified state and 
	 * remove it from the hash table.
	 * 
	 * @param The string state is being passed since we are going to delete the state based on its name.
	 */
	public void delete(String state){
		int hashVal = hashFunc(state);
		hashArray[hashVal].deleteLink(state);
	
	}//end delete
	
	/*
	 * The delete method uses the hash function previously defined to search for the specified state and 
	 * remove it from the hash table it the state has no collisions.
	 * 
	 * @param The string state is being passed since we are going to delete the state based on its name.
	 */
	public void deleteSingle(String state){
		int hashVal = hashFunc(state);
		hashArray[hashVal].deleteSingleLink(state);
	}
	
	/*
	 * The find method finds the specified state based on its hash value.
	 * 
	 * @param The string state is being passed since we are searching for the state based on its name.
	 */
	public int find(String state){
		int hashVal = hashFunc(state);
		return hashArray[hashVal].find(state);
		
	}//end find
	
	/*
	 * The printFreeAndCollision method counts the number of empty nodes and the number of collisions and prints
	 * their values.
	 */
	public void printFreeAndCollisions(){
		
		for(int i = 0; i < arraySize; i++){
			
			if(hashArray[i].isEmpty()){
				ar++;
				continue;
			}//end if
			
		}//end for
		System.out.println("There are " + ar + " empty spaces and " + collisions + " collisions.");
	}//end printFreeAndCollisions
	
	/*
	 * The displayTable method displays the elements of the hash table one after the other.
	 * 
	 * JOSEPH AND PROFESSOR LIU, I WANT TO APPOLOGIZE FOR THE HORRENDOUS FORMATTING.
	 * 
	 */
	public void displayTable(){
		for(int i = 0; i < arraySize; i++){
			System.out.print(i + ". ");
			
			if(hashArray[i].isEmpty()){
				System.out.println("Empty");
		
			}//end if
			else{
				hashArray[i].print();
			}
		
		}//end for
	}//end displayTable
	
/*
 * Description: 
 * <p> The LinkedList class creates the linked list for each key in the hash table.<p>
 * @author Joseph Curto
 * @version 04/18/2019
 */
class LinkedList{
	 private Node first;
	 private Node last;
	
	public LinkedList(){
		first = null;
		last = null;
		
	}//end constructor
	
	/*
	 * The isEmpty method checks to see whether or not the linked list in each key is empty.
	 */
	public boolean isEmpty(){
		return (first == null && last == null);
	}//end isEmpty
	
	/*
	 * The insertLast method inserts a state into the linked list from the end.
	 * 
	 * @param The string state and population are passed because they are what make up the state object.
	 */
	public void insertLast(Node newNode){  
		Node cur = last;
		
		while(cur != null){
			first = cur;
			cur = cur.nextNode;
		}//end while
		if(first == null){
			last = newNode;
		}//end if
		else{
			first.nextNode = newNode;
			collisions++;
		}//end else
		newNode.nextNode = cur;
		
	}//end insertLast
	
	/*
	 * The deleteLink deletes the specified link based on the state name.
	 * 
	 * @param The string state is the parameter since we are going to delete the linked made up of the state object based upon its name.
	 */
	public void deleteLink(String state){
		Node ln = first;
		Node cur = last;
		
		while(cur != null && state != cur.stateName){
			first = cur;
			cur = cur.nextNode;
			
		}//end while
		
		//These if else deletes if there are collisions
		if(first == null){
			last = last.nextNode;
		}
		else{
			ln.nextNode = first.nextNode;
		}
				
		
		System.out.println("You have deleted " + state);
	}//end deleteLink
	
	/*
	 * The deleteSingleLink method deletes the state if there are no collisions
	 * 
	 * @param The string state parameter allows us to deleted the specified state.
	 */
	public void deleteSingleLink(String state){
		Node ln = null;
		Node cur = last;
		
		while(cur != null && state != cur.stateName){
			ln = cur;
			cur = cur.nextNode;
		}//end while
		
		if(cur == null){
			last = last.nextNode;
		}//end if
		else{
			ln.nextNode = cur.nextNode;
		}//end else
		
		System.out.println("You have deleted " + state);
	}
	
	/*
	 * The find method locates the state and returns the states population.
	 * 
	 * @param The string state is the parameter since we are searching for a state based upon its name.
	 * @return If the state is found, then its population needs to be returned; otherwise -1 will be returned.
	 */
	public int find(String state){
		Node cur = last;
		
		while(cur != null){
			if(cur.stateName.equalsIgnoreCase(state)){
				System.out.println(state + " was found with the population of " + NumberFormat.getInstance().format(cur.statePopulation) + ".");
				return cur.statePopulation;
			}//end if
			cur = cur.nextNode;
		}//end while
		
		System.out.println(state + " was not found.");
		return -1;
	}//end find
	
	/*
	 * The print method prints the linked list.
	 */
	public void print(){
		Node cur = last;
		while(cur != null){
			cur.printNode();
			cur = cur.nextNode;
		}//end while
	}//end print method
	
}//end LinkedList
	
/*
 * Description:
 * <p> The Node class creates the node with the specified fields. <p>
 * 
 * @author Joseph Curto
 * @version 04/11/2019
 */
	private class Node{
		String stateName;
		int statePopulation;
		Node nextNode;
		
		public Node(String state, int population){
			stateName = state;
			statePopulation = population;
		}//end constructor
		
		/*
		 * The printNode method formats how the contents of the node will be printed.
		 */
		public void printNode(){
			System.out.printf("%-25s%,10d\n", stateName, statePopulation);
		}//end printNode
		
	}//end private Node class
}//end HashTable class
