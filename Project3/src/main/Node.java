package main;

/* Description:
 * <p> This class is meant to create the node object, the next and previous pointers, as well as
 * the data that will lie within the nodes, which is the states data.<p>
 * 
 * @author <Joseph Curto>
 * @version <03/14/19>
 */
public class Node {
		public State usa;
		public Node next;
		public Node prev;
		
		/*
		 * The Node constructor creates the node and what data will lie 
		 * within.
		 */
		public Node(State st){
			usa = st;
		}//end constructor 
		
		/*
		 * The displayNode method simply prints the Node
		 */
		public void displayNode(){
			System.out.println(usa.toString());
		}//end displayNode
	}//end Node class

