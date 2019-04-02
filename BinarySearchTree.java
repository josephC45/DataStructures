package main;

public class BinarySearchTree {
	private Node root;
	
	
	public BinarySearchTree(){
		root = null;			//no nodes in tree yet
	}//end constructor
	
	
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
	
	//finds the state and returns the states population
	public int find(String state){
		Node cur = root;
		int counter = 0;
		while(!cur.stateName.equalsIgnoreCase(state)){
			if(state.compareTo(cur.stateName) < 0){
				cur = cur.leftChild;
				counter++;
			}
			else{
				cur = cur.rightChild;
				counter++;
			}
			if(cur == null){
				return -1;
			}
		}
		System.out.println("You have traversed " + counter + " nodes to find " + state + ".");
		return cur.statePopulation;
		
	}//end find
	
	/*
	 * The delete method deletes the state from the BST and restructures the BST depending on the circumstances of the node; such as 
	 * if it has no children, if it has one child on its left or right, and if it has both a left and right child. If so, the following precautions will 
	 * be taken.
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
			}
			else if(leftChild){
				parent.leftChild = null;
			}
			else{
				parent.rightChild = null;
			}//end else
		}//end if
		
		
		
		//if no right child, replace with left subtree
		else if(cur.rightChild == null){
			if(cur == root){
				root = cur.leftChild;
			}
			else if(leftChild){
				parent.leftChild = cur.leftChild;
			}
			else{
				parent.rightChild = cur.leftChild;
			}//end else
		}//end else if
		
		
		//if no left child, replace with right subtree
		else if(cur.leftChild == null){
			if(cur == root){
				root = cur.rightChild;
			}
			else if(leftChild){
				parent.leftChild = cur.rightChild;
			}
			else{
				parent.rightChild = cur.rightChild;
			}
			
		}
		
		
		
		//two children, so replace with inorder successor
		else{
			Node successor = getSuccessor(cur);
			
			//connect parent of current to successor instead
			
			if(cur == root){
				root = successor;
			}
			else if(leftChild){
				parent.leftChild = successor;
			}
			else{
				parent.rightChild = successor;
			}
			
			//connect successor to currents left child
			successor.leftChild = cur.leftChild;
		}
		
		//successor cannot have a left child
		
		System.out.println(state + " has been deleted from the binary search tree.");
		
		
		
		
		
	}//end delete method
	
	/*
	 * The getSuccessor method aquires the node following the node value you are looking for. 
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
	
	public String printMinimum(Node node){
		Node cur = node;
		
		if(cur == null){
			return null;
		}//end if
		
		while(cur.leftChild != null){
			cur = cur.leftChild;
		}
		return cur.stateName;
	}//end printMinimum
	
	
	//prints inorder traversal
	public void printInOrder(Node localRoot){
		if(localRoot == null)
			return;
		printInOrder(localRoot.leftChild);
		localRoot.printNode();
		printInOrder(localRoot.rightChild);
		
	}//end printInOrder
	
	//prints PreOrder traversal
	public void printPreOrder(Node localRoot){
		if(localRoot == null)
			return;
		localRoot.printNode();
		printPreOrder(localRoot.leftChild);
		printPreOrder(localRoot.rightChild);
		
	}//end printPreOrder
	
	//prints PostOrder traversal
	public void printPostOrder(Node localRoot){
		if(localRoot == null)
			return;
		printPostOrder(localRoot.leftChild);
		printPostOrder(localRoot.rightChild);
		localRoot.printNode();
		
	}//end printPostOrder
	
	//Allows for the traversal over the binary search tree
	public void Traverse(int trav){
		switch(trav){
		case 1: printInOrder(root);
				break;
		case 2: printPreOrder(root);
				break;
		case 3: printPostOrder(root);
				break;
		case 4: System.out.println("The smallest population is from the state of " + printMinimum(root));
				break; 
		}//end switch
		
	}//end traverse
	
	
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




