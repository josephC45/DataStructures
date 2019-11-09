package main;

/*Descripton
 * <p> The Queue class implements a queue to store a doubly linked list
 * of states objects. Allowing the program to insert nodes containing the states
 * data from the front and end of the doubly linked list, as well as remove nodes from either
 * end; and delete nodes from arbitrary locations within the doubly linked list.
 * @author <Joseph Curto>
 * @version <03/14/19>
 */
public class Queue {
	private Node first;
	private Node last;

	public Queue(){
		first = null;
		last = null;
	}//end constructor
	
	/*
	 * The isEmpty method checks to see whether or not the doubly linked list
	 * is empty. 
	 * @param It is is then true is returned, otherwise, false is returned.
	 */
	public boolean isEmpty(){
		return (first == null);
	}//end isEmpty
	
	/*
	 * The isFull method checks to see whether or not the doubly linked list is full or not.
	 * @param If it is, then true is returned, otherwise false is returned.
	 */
	public boolean isFull(){
		return false;
	}//end isFull
	
	/*
	 * The insertFront method inserts the nodes into the front of the doubly linked list.
	 * First the program checks to see if the doubly linked list is empty; if it is, then the last postiion
	 * is where the new node is located, otherwise it is inserted in the front and the next and previous pointers
	 * are set.
	 * @param State s is taken as a parameter because we must insert the state data to the front of the doubly linked list.
	 */
	public void insertFront(State s){
		Node newNode = new Node(s);
		if(isEmpty()){
			last = newNode;
		}
		else{
			first.prev = newNode;
			newNode.next = first;
		}
		first = newNode;
		
	}//end insertFront
	
	/*
	 * The removeFirst method removes the first node from the doubly linked list by setting the first pointer
	 * to the following node which is first.next.
	 * @return The new node along with its states data is needed for printing.
	 */
	public State removeFront(){
		State temp = first.usa;
		if(first.next == null){
			last = null;
		}
		else{
			first.next.prev = null;
		}
		first = first.next;
		return temp;
	}//end removeFirst
	
	/*
	 * The insertEnd method inserts the nodes into the end of the doubly linked list.
	 * First the program checks to see if the doubly linked list is empty; if it is, then the first postiion
	 * is where the new node is located, otherwise it is inserted in the end and the next and previous pointers
	 * are set.
	 * 
	 * @param State data is passed since it is needed to create the new node
	 * which is then inserted into the end of the doubly linked list.
	 */
	public void insertEnd(State s){
		Node newNode = new Node(s);
		if(isEmpty()){
			first = newNode;
		}
		else{
			
			last.next = newNode;
			newNode.prev = last;
		}
		last = newNode;
		
	}//end insertEnd
	
	
	/*
	 * The removeEnd method removes the last node from the doubly linked list by setting the last node to 
	 * the node which precedes it, that being last.prev.
	 * @return The node's data is returned onto the programs table.
	 */
	public State removeEnd(){
		Node temp = last;
		if(first.next == null){
			first = null;
		}
		else{
			last.prev.next = null;
		}
		last = last.prev;
		return temp.usa;
	}//end removeEnd
	
	
	/*
	 * The findDelete method checks to see whether or not the states which it is currently visiting
	 * has the state name we are looking for. If so, they are removed from the doubly linked list, 
	 * and their next and previous pointers are reassigned.
	 * 
	 * @param String val is needed to compare each states names to one another
	 * in order to check if it needs to be deleted or not.
	 * 
	 * @return The current node, which traverses through the entire doubly linked list is returned
	 * so we see all the states that weren't deleted.
	 */
	public Node findDelete(State val){
		
		Node cur = first;
		while(cur.usa.compareTo(val) != 0){
			cur = cur.next;
			if(cur == null){
				return null;
			}
		}
		if(cur == first){
			first = cur.next;
		}
		else{
			cur.prev.next = cur.next;
		}
		if(cur == last){
			last = cur.prev;
		}
		else{
			cur.next.prev = cur.prev;
		}
		
		return cur;
		
	}//end findDelete
	
	/*
	 * The printQueue method traverses through the entire queue until it reaches the last node
	 * printing each one as it arrives to it.
	 */
	public void printQueue(){
		Node cur = first;
		while(cur != null){
			cur.displayNode();
			cur = cur.next;
		}
		System.out.println(" ");
	}//end printQueue
	
}//end class
