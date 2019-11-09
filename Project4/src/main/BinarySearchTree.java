package main;

/*Description: 
 * <p> The BST class will have a insert method, find method, delete method, successor method, traversal methods, and minimum and maximum methods. <p>
 * <p> The insert method inserts the states into its appropriate area in the tree via alphabetical order. <p>
 * <p> The find method traverses through the BST to find the state via their state name. <p>
 * <p> The delete method will traverse the tree to find the state, determine whether or not they have children, if so, then the proper precautions will be taken.<p>
 * <p> The successor method traverses through the tree to find the successor of the node specified by going right down the tree.<p>
 * <p> The traversal methods inOrder, preOrder, and postOrder are used to traverse the BST<p>
 * <p> The minimum and maximum methods simply traverse the BST's left side as well as the right side  until the null node is reached, then the stats population is returned.<p>
 * 
 * @author <Joseph Curto>
 * @version <04/04/19>
 */
public class BinarySearchTree {
	private Node root;
	private Node min_Population;
	private Node max_Population;
	
	
	public BinarySearchTree(){
		root = null;			//no nodes in tree yet
	}//end constructor
	
	/*
	 * The insert method inserts the state which consists of their name and population size into the binary search tree
	 * first checking if the root is equal to null, if so, then it is inserted as the root node. Otherwise is inserted in 
	 * its proper location.
	 * 
	 * @param The string and int parameter are the data items that make up each state node.
	 */
	public void insert(String state, int population){
		Node newNode = new Node(state, population);
		newNode.stateName = state;
		newNode.statePopulation = population;
		
		if(root == null){
			root = newNode;
		}//end if null
		else{
			Node cur = root;
			Node parent;
			while(true){
				parent = cur;
				if(state.compareTo(cur.stateName) < 0){
					cur = cur.leftChild;
					if(cur == null){
						parent.leftChild = newNode;
						return;
					}//end if
					
				}//end if go left
				else{
					cur = cur.rightChild;
					if(cur == null){
						parent.rightChild = newNode;
						return;
						}//end if
					}//end else go right
				}//end while
			}//end else not root
		}//end insert
	
	/*
	 * The find method finds the specified state and returns the states population and the number of nodes we have traversed
	 * to get to the specified state.
	 * 
	 * @param The string parameter allows the user to specify which state they wish to find.
	 * @return Negative one is returned if the state you are looking for is not found. 
	 * If the find is successful, then the nodes population is returned
	 */ 
	public int find(String state){
		Node cur = root;
		int counter = 0;
		while(!cur.stateName.equalsIgnoreCase(state)){
			if(state.compareTo(cur.stateName) < 0){
				cur = cur.leftChild;
				counter++;
			}//end if
			else{
				cur = cur.rightChild;
				counter++;
			}//end else
			if(cur == null){
				return -1;
			}//end if
		}//end while
		System.out.println("You have traversed " + counter + " nodes to find " + state + ".");
		return cur.statePopulation;
		
	}//end find
	
	/*
	 * The delete method deletes the state from the BST and restructures the BST depending on the circumstances of the node; such as 
	 * if it has no children, if it has one child on its left or right, and if it has both a left and right child. If so, the following precautions will 
	 * be taken.
	 * 
	 * @param The string parameter allows the user to input the name of a specific state they wish to delete from the binary search tree
	 */
	public void delete(String state){
		Node cur = root;
		Node parent = root;
		boolean leftChild = true;
		
		while(!cur.stateName.equalsIgnoreCase(state)){
			parent = cur;
			if(state.compareTo(cur.stateName) < 0){
				leftChild = true;
				cur = cur.leftChild;
			}//end if
			
			//otherwise go right
			else{
				leftChild = false;
				cur = cur.rightChild;
			}//end else
			
			if(cur == null){
				System.out.println("You did not find the state you meant to delete");
			}//end if
		}//end while
		
		
		
		//if there are no children
		if(cur.leftChild == null && cur.rightChild == null){
			if(cur == root){
				root = null;
			}//end if
			
			else if(leftChild){
				parent.leftChild = null;
			}//end else if
			
			else{
				parent.rightChild = null;
			}//end else
		}//end if
		
		
		
		//if no right child, replace with left subtree
		else if(cur.rightChild == null){
			if(cur == root){
				root = cur.leftChild;
			}//end if
			
			else if(leftChild){
				parent.leftChild = cur.leftChild;
			}//end else if
			
			else{
				parent.rightChild = cur.leftChild;
			}//end else
		}//end else if
		
		
		//if no left child, replace with right subtree
		else if(cur.leftChild == null){
			if(cur == root){
				root = cur.rightChild;
			}//end if
			
			else if(leftChild){
				parent.leftChild = cur.rightChild;
			}//end else if
			
			else{
				parent.rightChild = cur.rightChild;
			}//end else
			
		}//end else if
		
		//two children, so replace with inorder successor
		else{
			Node successor = getSuccessor(cur);
			
			//connect parent of current to successor instead
			
			if(cur == root){
				root = successor;
			}//end if
			
			else if(leftChild){
				parent.leftChild = successor;
			}//end else if
			
			else{
				parent.rightChild = successor;
			}//end else
			
			//connect successor to currents left child
			successor.leftChild = cur.leftChild;
		}//end else
		
		//successor cannot have a left child
		
		System.out.println(state + " has been deleted from the binary search tree.");
		
		
	}//end delete method
	
	/*
	 * The getSuccessor method acquire the node following the node value you are looking for by traversing the right side
	 * of the tree.
	 * 
	 * @param The Node parameter allows us to acquire the successor of the specified node.
	 * @return The successor node is returned for use in the delete method.
	 */
	private Node getSuccessor(Node delNode){
		Node successorParent = delNode;
		Node successor = delNode;
		Node cur = delNode.rightChild;
		while(cur != null){
			successorParent = successor;
			successor = cur;
			cur = cur.leftChild;
		}//end while
		
		if(successor != delNode.rightChild){
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
			
		}//end if
		return successor;
		
	}//end getSuccessor
	
	
	/*
	 * The printMinimum prints the node with the smallest population size by calling the min method
	 * until a null node is returned.
	 * 
	 */
	public void printMinimum(){
		if(root == null){
			return;
		}//end if
		
		if(min_Population == null){
			min_Population = root;
		}//end if
		
		min(root);
		min_Population.printNode();
	}//end printMinimum
	

	/*
	 * The min method find the state with the smallest population by recursively traversing through the binary search tree. 
	 * 
	 * @param The Node parameter allows us to start at the root of the binary search tree, thus allowing us to traverse
	 * through the left side of the tree until we reach a null node. 
	 */
	private void min(Node node){
		if(node == null){
			return;
		}//end if
		
		if(node.statePopulation < min_Population.statePopulation){
			min_Population = node;
		}//end if
		
		min(node.leftChild);
		min(node.rightChild);
	}//end min
	
	
	/*
	 * The printMaximum prints the node with the largest population size by calling the max method until a null node is reached. 
	 * 
	 */
	public void printMaximum(){
		if(root == null){
			return;
		}//end if
		
		if(max_Population == null){
			max_Population = root;
		}//end if
		
		max(root);
		max_Population.printNode();
		
	}//end printMaximum
	
	/*
	 * The max method finds the state with the maximum sized population by recursively traversing through the binary search tree. 
	 * 
	 * @param The Node parameter allows us to start at the root of the binary search tree, thus allowing us to traverse
	 *  traverse through the right side of the tree until we reach a null node
	 */
	private void max(Node node){
		if(node == null){
			return;
		}//end if
		
		if(node.statePopulation > max_Population.statePopulation){
			max_Population = node;
		}//end if
		
		max(node.leftChild);
		max(node.rightChild);
	}//end max
	
	
	/*
	 * The printInOrder method traverses the binary search tree in order (LNR) 
	 * which results in an ascending ordered binary search tree.
	 * 
	 * @param The localRoot parameter is used to recursively call the root node
	 */
	public void printInOrder(Node localRoot){
		if(localRoot == null)
			return;
		printInOrder(localRoot.leftChild);
		localRoot.printNode();
		printInOrder(localRoot.rightChild);
		
	}//end printInOrder
	
	/*
	 * The printPreOrder method traverses the binary search tree in the pre-order (NLR)
	 * which returns the nodes value, then the nodes left child value, then the nodes right child value.
	 * 
	 * @param The localRoot parameter is used to recursively call the root node
	 */
	public void printPreOrder(Node localRoot){
		if(localRoot == null)
			return;
		localRoot.printNode();
		printPreOrder(localRoot.leftChild);
		printPreOrder(localRoot.rightChild);
		
	}//end printPreOrder
	
	/*
	 * The printPostOrder method traverses the binary search tree in the post-order(LRN)
	 * which returns the left child, right child, then the children's node. 
	 * 
	 * @param The localRoot parameter is used to recursively call the root node
	 */
	public void printPostOrder(Node localRoot){
		if(localRoot == null)
			return;
		printPostOrder(localRoot.leftChild);
		printPostOrder(localRoot.rightChild);
		localRoot.printNode();
		
	}//end printPostOrder
	
	/*
	 * The traverse methodAllows for the traversal over the binary search tree using recursion.
	 * 
	 * @param The int trav parameter allows the user to input numbers 1-5 depending on what method you wish to perform.
	 */
	public void traverse(int trav){
		switch(trav){
		case 1: printInOrder(root);
				break;
		case 2: printPreOrder(root);
				break;
		case 3: printPostOrder(root);
				break;
		case 4: printMinimum();
				break;
		case 5: printMaximum();
				break;
		}//end switch
		
	}//end traverse
	

/*
* Description: 
* <p>The Node class creates the state node which consists of the states name, and its population. Also, creates 
* the left and right children nodes.<p>
* 
* @author <Joseph Curto>
* @version <04/04/19>
*/
private class Node{
		String stateName;
		int statePopulation;
		Node leftChild;
		Node rightChild;
		
		
		public Node(String state, int population){
			stateName = state;
			statePopulation = population;
		}//end constructor
		
		public void printNode(){
			System.out.printf("%-25s%,10d\n",stateName, statePopulation);
		}//end printNode
	}//end Node class

}//end BST class




