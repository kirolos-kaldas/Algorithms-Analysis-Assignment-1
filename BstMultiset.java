import java.io.PrintStream;

public class BstMultiset<T> extends Multiset<T>
{
	BSTNode root;
	
	// Initialize the BST
	public BstMultiset()
	{
		root = null;
	}
	
	// This method adds new nodes in the BST by taking the item as a parameter
	public void add(T item) 
	{
		// Create the node
		BSTNode newNode = new BSTNode(item);
		boolean test = false;
		
		// Add the node to the root if it doesn't have a node
		if(root == null)
		{
			root = newNode;
			root.setInstances(root.getInstances() + 1);
		}
		else
		{
			test = true;
		}
		
		BSTNode current = root;
		BSTNode parent = null;
		
		// Add the node to the children if the root is not empty
		while(test == true)
		{
			parent = current;
			
			// Checks if the item to be added is less then the current which adds the item to a left child
			if(item.toString().compareTo(current.getItem().toString()) < 0)
			{
				// Gets the left child of the parent
				current = current.getLeftChild();
				
				// If the left child is empty, assign the node to it
				if (current == null)
				{
					parent.setLeftChild(newNode);
					parent.getLeftChild().setInstances(parent.getLeftChild().getInstances() + 1);
					test = false;
				}
			}
			// If the item already exists in the BST, then just add an instance to the existing node
			else if(item.toString().compareTo(current.getItem().toString()) == 0)
			{
				current.setInstances(current.getInstances() + 1);
				test = false;
			}
			else
			{
				// Gets the right child of the parent
				current = current.getRightChild();
				
				// If the right child is empty, assign the node to it
				if (current == null)
				{
					parent.setRightChild(newNode);
					parent.getRightChild().setInstances(parent.getRightChild().getInstances() + 1);
					test = false;
				}
			}
		}
	}

	
	// This method searches for a desired item in the BST by taking the item looked for as a parameter
	public int search(T item) 
	{
		// Point to the root
		BSTNode currentNode = root;
		int count = 0;
		
		// Loop until there are no more nodes to look through
		while(currentNode != null)
		{
			// If the item desired is found, get its instances and break fro the loop
			if(currentNode.getItem().equals(item))
			{
				count += currentNode.getInstances();
				break;
			}
			
			// If the item desired is less than the parent then get its left child
			if(item.toString().compareTo(currentNode.getItem().toString()) < 0)
			{
				currentNode = currentNode.getLeftChild();
			}
			// Otherwise, get its right child
			else
			{
				currentNode = currentNode.getRightChild();
			}
		}
		
		// Return the number of instances
		return count;
	}

	
	// This method removes a node with the desired item once and takes the desired item as a parameter
	public void removeOne(T item) 
	{
		boolean test = true;
		
		BSTNode current = root;
		BSTNode parent = null;
		
		if(current == null)
		{
			test = false;
		}
		
		// This loop gets the desired node
		while(test == true)
		{
			parent = current;
			
			// If the item to be removed is less then the current then point the left child of the parent
			if(item.toString().compareTo(current.getItem().toString()) < 0)
			{
				// Gets the left child of the parent
				current = current.getLeftChild();
				
				// If the item is not found then break from the loop
				if (current == null)
				{
					test = false;
					break;
				}
				
				// Breaks out of the loop when the desired node is found
				if(item.toString().compareTo(current.getItem().toString()) == 0)
				{
					break;
				}
			}
			// Breaks out of the loop when the desired node is found
			else if(item.toString().compareTo(current.getItem().toString()) == 0)
			{
				break;
			}
			else
			{
				// Gets the Right child of the parent
				current = current.getRightChild();
				
				// If the item is not found then break from the loop
				if (current == null)
				{
					test = false;
					break;
				}
				
				// Breaks out of the loop when the desired node is found
				if(item.toString().compareTo(current.getItem().toString()) == 0)
				{
					break;
				}
			}
		}
		
		// This if statement deletes the node desired
		if(test == true)
		{
			if(current.getInstances() > 1)
			{
				test = false;
				current.setInstances(current.getInstances() - 1);
			}
			else
			{
				// Delete if the node has no children
				if(current.getLeftChild() == null && current.getRightChild() == null)
				{
					// If the node is at the root
					if(parent == current)
					{
						root = null;
					}
					else if(parent.getRightChild() == current)
					{
						parent.setRightChild(null);
					}
					else if(parent.getLeftChild() == current)
					{
						parent.setLeftChild(null);
					}
				}
				// If node has one child at the right
				else if(current.getLeftChild() == null)
				{
					if(parent == current)
					{
						root = parent.getRightChild();
					}
					else if(parent.getRightChild() == current)
					{
						parent.setRightChild(current.getRightChild());
					}
					else
					{
						parent.setLeftChild(current.getRightChild());
					}
				}
				// If node has one child at the left
				else if(current.getRightChild() == null)
				{
					if(parent == current)
					{
						root = parent.getLeftChild();
					}
					else if(parent.getLeftChild() == current)
					{
						parent.setLeftChild(current.getLeftChild());
					}
					else
					{
						parent.setRightChild(current.getLeftChild());
					}
				}
				// If the node has two children
				else
				{
					// Get the node that will replace the node that will be deleted
					BSTNode minimumRightNode = minimumRightNode(current);
					
					if(parent == current)
					{
						minimumRightNode.setLeftChild(root.getLeftChild());
						minimumRightNode.setRightChild(root.getRightChild());
						root = minimumRightNode;
					}
					else if(parent.getLeftChild() == current)
					{
						minimumRightNode.setLeftChild(current.getLeftChild());
						minimumRightNode.setRightChild(current.getRightChild());
						parent.setLeftChild(minimumRightNode);
					}
					else
					{
						minimumRightNode.setLeftChild(current.getLeftChild());
						minimumRightNode.setRightChild(current.getRightChild());
						parent.setRightChild(minimumRightNode);
					}
				}
			}
		}
	} // end of removeOne()
	
	// This method deletes all instances of a desired node, takes the item as a parameter
	// It is also implemented the same way as the removeOnce() method
	public void removeAll(T item) 
	{
		boolean test = true;
		
		BSTNode current = root;
		BSTNode parent = null;
		
		while(test == true)
		{
			parent = current;
			
			if(item.toString().compareTo(current.getItem().toString()) < 0)
			{
				current = current.getLeftChild();
				
				if (current == null)
				{
					test = false;
					break;
				}
				
				if(item.toString().compareTo(current.getItem().toString()) == 0)
				{
					break;
				}
			}
			else if(item.toString().compareTo(current.getItem().toString()) == 0)
			{
				break;
			}
			else
			{
				current = current.getRightChild();
				
				if (current == null)
				{
					test = false;
					break;
				}
				
				if(item.toString().compareTo(current.getItem().toString()) == 0)
				{
					break;
				}
			}
		}
		
		if(test == true)
		{
			if(current.getLeftChild() == null && current.getRightChild() == null)
			{
				if(parent == current)
				{
					root = null;
				}
				else if(parent.getRightChild() == current)
				{
					parent.setRightChild(null);
				}
				else if(parent.getLeftChild() == current)
				{
					parent.setLeftChild(null);
				}
			}
			else if(current.getLeftChild() == null)
			{
				if(parent == current)
				{
					root = parent.getRightChild();
				}
				else if(parent.getRightChild() == current)
				{
					parent.setRightChild(current.getRightChild());
				}
				else
				{
					parent.setLeftChild(current.getRightChild());
				}
			}
			else if(current.getRightChild() == null)
			{
				if(parent == current)
				{
					root = parent.getLeftChild();
				}
				else if(parent.getLeftChild() == current)
				{
					parent.setLeftChild(current.getLeftChild());
				}
				else
				{
					parent.setRightChild(current.getLeftChild());
				}
			}
			else
			{
				BSTNode minimumRightNode = minimumRightNode(current);
				
				if(parent == current)
				{
					minimumRightNode.setLeftChild(root.getLeftChild());
					minimumRightNode.setRightChild(root.getRightChild());
					root = minimumRightNode;
				}
				else if(parent.getLeftChild() == current)
				{
					minimumRightNode.setLeftChild(current.getLeftChild());
					minimumRightNode.setRightChild(current.getRightChild());
					parent.setLeftChild(minimumRightNode);
				}
				else
				{
					minimumRightNode.setLeftChild(current.getLeftChild());
					minimumRightNode.setRightChild(current.getRightChild());
					parent.setRightChild(minimumRightNode);
				}
			}
		}
	} // end of removeAll()

	
	// This method prints out the BST
	public void print(PrintStream out) 
	{
		BSTNode currentNode = root;
		
		inorderPrint(currentNode, out);
	}
	
	
	// This a recursive method that calls itself to print the BST in an in order way
	public void inorderPrint(BSTNode node, PrintStream out)
	{
		int count = 0;
		
		while(node != null)
		{
			if(count != 0)
			{
				break;
			}
			
			// prints out the left child first
			inorderPrint(node.getLeftChild(), out);
			
			// Number of occurrence of the current node
			count += node.getInstances();
			
			out.println(node.getItem() + " | " + count);
			
			// prints out he right child
			inorderPrint(node.getRightChild(), out);
		}
	}
	
	
	// This method gets the least child of the right child node of the node that is to be deleted
	public BSTNode minimumRightNode(BSTNode current)
	{
		BSTNode parent = current;
		current = current.getRightChild();
		
		// This loop keeps on getting the left child until it reaches the least left child
		while(current != null)
		{
			if(current.getLeftChild() != null)
			{
				parent = current;
				current = current.getLeftChild();
			}
			else
			{
				if(parent == root)
				{
					parent.setRightChild(null);
					break;
				}
				parent.setLeftChild(current.getLeftChild());
				break;
			}
		}
		
		return current;
	}
} // end of class BstMultiset
