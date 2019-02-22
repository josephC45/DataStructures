package main;


/*
 * Priority queue class utilizes the structure of a queue to store elements popped from the
 * states stack, storing them based on their population. Resulting in the states from a specific region 
 * printing out with the state with the largest population (highest priority) first. 
 * @author <Joseph Curto>
 * @version <02/19/19>
 */
public class Priority {
	private int maxSize;
	private State[] usa;
	private int nItems;

	public Priority(int max) {
		maxSize = max;
		usa = new State[maxSize];
		nItems = 0;

	}// end constructor
	
	/*
	 * The insert method allows a state to be inserted in to the priority queue based on their population 
	 * the state with the largest population will be displayed first and continuing until the states from that specific
	 * region are all inserted. 
	 * @params The insert method uses a State as a parameter so it can insert a state into the priority queue.
	 */

	public void insert(State st) {
		if (isFull()) {
			System.out.println("The queue is full, you cannot insert anymore states.");
		}//end if
		else{

			int i;

			if (nItems == 0) {
				usa[nItems++] = st;
			}//end if
			else{
				for (i = nItems - 1; i >= 0; i--) {
					if (st.getPopulation() < usa[i].getPopulation()) {
						usa[i + 1] = usa[i];
					} //end if
					
					else{
						break;
					}//end else
				}//end for
				
				usa[i + 1] = st;
				nItems++;
			}//end nested else
		}//end else

	}// end insert

	/*
	 * The State remove function allows you to remove each individual state from the priority queue.
	 * @return The return statement decrements the size of the priority queue by one by removing the value.
	 */
	public State remove() {
		if (isEmpty()) {
			System.out.println("You cannot remove anything from the queue since it is empty.");
		}//end if

		return usa[--nItems];

	}// end remove

	
	/*
	 * The isEmpty method returns a boolean value whether or not the priority queue is empty or not. 
	 * @return The return statement returns a boolean value if the priority queue meets the specific requirements.
	 */
	public boolean isEmpty() {
		return (nItems == 0);

	}// end isEmpty

	/*
	 * The isFull method returns a boolean value whether or not the priority queue is full or not. 
	 * @return The return statement returns a boolean value based on whether or not the priority queue is full or not.
	 * if it is then true returns, otherwise false is returned. 
	 */
	public boolean isFull() {
		return (nItems == maxSize);
		
	}// end isFull
	
	/*
	 * PrintPriority method simply prints the elements of the priority queue onto the console. 
	 */
	public void printPriority(){
		for(int i = usa.length - 1; i >= 0; i--){
			while(!isEmpty()){
				System.out.println(usa[i]);
			}
		}//end for
		
	}//end printPriority
	
}//end Priority class
