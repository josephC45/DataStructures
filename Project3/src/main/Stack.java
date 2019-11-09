package main;

/*Description:
 * <p>The Stack class is used to hold the singly linked list.
 * Which will then be used to push and pop nodes from the stack and insert 
 * them into the doubly linked list queue.
 * 
 * 
 * @author <Joseph Curto>
 * @version <03/14/19>
 */
public class Stack {
	private Node first;
	
	/*
	 * Constructor which creates the stack
	 */
	public Stack(){
		first = null;
	}//end constructor
	
	/*
	 * isEmpty method checks whether or not the stack contains
	 * anything, if not then true is returned; otherwise false is returned.
	 * @return null is returned if the first node is empty, which signifies that the 
	 * linked list is in fact empty.
	 */
	public boolean isEmpty(){
		return (first == null);
	}//end isEmpty method
	
	/*
	 * The isFull method check to see if the linked list is full, which becuase it is a linked list
	 * it doesn't have a capacity which means it is never full.
	 */
	public boolean isFull(){
		return false;
	}
	
	/*
	 * Push method is used to push/add the states object onto the node. 
	 * @param State's data is used as the parameter since it is what the new node
	 * will be carryig so do speak.
	 */
	public void push(State st){
		Node newNode = new Node(st);
		newNode.next = first;
		first = newNode;
	}//end insetFirst method
	
	/*
	 * Pop method is used to pop/remove a node from the beginning of the stack.
	 * @return The nodes data is returned in order to print it onto the table.
	 */
	public State pop(){
		Node temp = first;
		first = first.next;
		return temp.usa;
		
	}//end deleteFirst method
	
	/*
	 * The display method is used to simply traverse the linked list and print each
	 * node.
	 */
	public void printStack(){
		Node cur = first;
		while(cur != null){
			cur.displayNode();
			cur = cur.next;
		}
		System.out.print(" ");
	}//end display method
	
}//end stack class



