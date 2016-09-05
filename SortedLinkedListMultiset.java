import java.io.PrintStream;

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	private Node head;
	private int length;
	
	/* Constructor */
	public SortedLinkedListMultiset() 
	{
		head = null;
		length = 0;
	} 
	
	
	public void add(T item) 
	{
		Node newNode = new Node(item);
		Node currentNode;
		Node prevNode = null;
		
		currentNode = head;
		
		/* A string variable that converts the value of the item to String */
		String temp = item.toString();
		
		/* Loop until the end of the list */
		while(currentNode != null)
		{
			String temp2 = currentNode.getElement().toString();
			if(temp2.compareTo(temp) > 0)
			{
				break;
			}
			
			prevNode = currentNode;
			currentNode = currentNode.getNextNode();
		}
		
		/* If the list is empty, add new node to the head of the list */
		if(head == null)
		{
			head = newNode;
		}
	
		else if(currentNode == head)
		{
			newNode.setNextNode(head);
			head = newNode;
		}
		else
		{
			prevNode.setNextNode(newNode);
			newNode.setNextNode(currentNode);
		}
		
		length++;
	} 
	
	
	public int search(T item) 
	{
		Node currentNode = head;
		int count = 0;
		
		/* Loop until end of the list */
		while(currentNode != null)
		{
			/* If the searched value exists then increment counter */
			if(currentNode.getElement().equals(item))
			{
				count++;
			}
			else if(count != 0)
			{
				return count;
			}
			/* Set current node to the next node */
			currentNode = currentNode.getNextNode();
		}
		
		return count;

	}
	
	public void removeOne(T item) 
	{
		Node currentNode = head;
    	Node preNode = null;
    	
    	/* Loop until end of list */
        while (currentNode != null) 
        {
        	/* If current node equals to the searched item */
        	if (currentNode.getElement().equals(item)) 
        	{
        		/* If the value of current node matches the head node value */
        		if(currentNode.getElement().equals(head.getElement()))
        		{
        			head = head.getNextNode();
        		}
        		else
        		{
        			preNode.setNextNode(currentNode.getNextNode());
        		}
        		
        		length--;
    			break;
        	}
        	
        	preNode = currentNode;
            currentNode = currentNode.getNextNode();
        }
	}
	
	
	public void removeAll(T item) 
	{
		Node currentNode = head;
    	Node preNode = null;
    	int count = 0;
    	
    	/* Loop until end of list */
        while (currentNode != null) 
        {
        	/* If the current node value matches the item to be removed */
        	if (currentNode.getElement().equals(item)) 
        	{
        		count++;
        		
        		/* If the current node value equals to the head value */
        		if(currentNode.getElement().equals(head.getElement()))
        		{
        			head = head.getNextNode();
        		}
        		else
        		{
        			preNode.setNextNode(currentNode.getNextNode());
        		}
        		
        		currentNode = currentNode.getNextNode();
        		
        		length--;
        	}
        	else if(count != 0)
    		{
    			break;
    		}
        	else
        	{
        		preNode = currentNode;
                currentNode = currentNode.getNextNode();
        	}
        }
	} 	
	
	public void print(PrintStream out) 
	{
		Node currentNode = head;
		Node preNode = currentNode;
		int count;
		
		/* Loop until end of list */
		for(int i = 0; i < length; i++)
		{
			if(checkRepitition(preNode) != false)
			{
				T temp = preNode.getElement();
				currentNode = head;
				count = 0;
				
				/* Loop until end of list */
				while(currentNode != null)
				{
					/* If the current node equals to the prenode value */
					if(currentNode.getElement().equals(temp))
					{
						count++;
					}
					else if(count != 0)
					{
						break;
					}
					
					currentNode = currentNode.getNextNode();
				}
				out.println(preNode.getElement() + " | " + count);
				preNode = preNode.getNextNode();
			}
			else
			{
				preNode = preNode.getNextNode();
			}
		}
	}
	
	/* Method that checks if duplicate copies exist in the list*/
	private boolean checkRepitition(Node checkNode)
	{
		Node currentNode = head;
		
		/* Loop until the end of the list */
		while(checkNode != currentNode)
		{
			if(checkNode.getElement().equals(currentNode.getElement()))
			{
				return false;
			}
			/* Current node is set to the next node */
			currentNode = currentNode.getNextNode();
		}
		
		return true;
	}
	
} // end of class SortedLinkedListMultiset