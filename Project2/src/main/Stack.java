package main;


/*
 * The Stack class creates a stack of states from the regions specified; those being the South, West, and MidWest.
 * If the states are in a region other than the three specified they are not pushed onto the stack, they are simply ignored.
 * 
 * @author <Joseph Curto>
 * @version <02/19/19>
 */
public class Stack {

	private int top;
	private int maxSize;
	private State[] usa;

	public Stack(int max) {
		maxSize = max;
		usa = new State[maxSize];
		top = -1;

	}// end constructor

	/*
	 * The peek method allows the user to only see or peek at the first element in the stack.
	 * @return The return statement returns just the top element of the stack if needed.
	 */
	public State peek() {
		if (top == -1) {
			System.out.println("Stack is empty");
			return null;
		}//end if
		return usa[top];
	}// end peek

	/*
	 * Push method allows the user to push a state onto the stack.
	 * @params By using State as a parameter it allows the method to push each individual states on to the stack vs individual components.
	 * @return The method push returns a incremented usa array with the new state inserted.
	 */
	public State push(State st) {
		if (isFull()) {
			System.out.println("The stack is full, you cannot push.");
		}//end if

		return usa[++top] = st;
		
	}// end push

	/*
	 * Pop method allows the user to remove states one at a time in LIFO pattern.
	 * @return The return statement returns the last element in the stack, and decrements the stack by 1.
	 */
	public State pop() {
		if (isEmpty()) {
			System.out.println("You cannot pop any state since there are none to be popped.");
		}//end if

		return usa[top--];
	}// end pop

	/*
	 *isFull method allows user to check if the stack is full.
	 *@return The boolean values of true or false are returned if the stack meets the specified requirements.
	 */
	private boolean isFull() {
		return (top == maxSize - 1);
	}// end isFull

	/*
	 * isEmpty method checks whether or not the stack is empty or not. 
	 * @return The boolean values of true or false are returned if the stack meets the specified requirements.
	 */
	public boolean isEmpty() {
		return (top == -1);
	}// end isEmpty

	/*
	 * The printStack method simply allows the user to print out the states within the stack. 
	 */
	public void printStack() {
		for (int i = top; i >= 0; i--) {
			System.out.println(usa[i]);
		}//end for
	}// end printStack

}// end stack class
