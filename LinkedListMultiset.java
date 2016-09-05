import java.io.PrintStream;

public class LinkedListMultiset<T> extends Multiset<T>
{
	private Node head;
	private int length;
	
	/* Constructor */
	public LinkedListMultiset() 
	{
		head = null;
		length = 0;
	}
	
	
	public void add(T item) 
	{
		Node currentNode;
		/* If the list is empty, add new node to the head of the list */
		if(head == null)
		{
			head = new Node(item);
		}
		else
		{
			currentNode = head;
			
			/* Loop until there is no next node */
			while(currentNode.getNextNode() != null)
			{
				currentNode = currentNode.getNextNode();
			}
			
			currentNode.setNextNode(new Node(item));
		}
		
		length++;
	}
	
	
	public int search(T item) 
	{
		Node currentNode = head;
		int count = 0;
		
		/* Loop until end of list */
		while(currentNode != null)
		{
			/* If the current node value equals to the searched item */
			if(currentNode.getElement().equals(item))
			{
				count++;
			}
			/* Current node is set to the next node */
			currentNode = currentNode.getNextNode();
		}
		
		return count;
	}	
	
	
	public void removeOne(T item) 
	{
		Node currentNode = head;
    	Node preNode = null;
    	
    	/* Loop until end of the list */
        while (currentNode != null) 
        {
        	/* If the current node value equals to the value of the item to be removed */
        	if (currentNode.getElement().equals(item)) 
        	{
        		/* If the item to be removed is in the head of the list */
        		if(currentNode.getElement().equals(head.getElement()))
        		{
        			head = head.getNextNode();
        		}
        		/*else if(currentNode.getNextNode() == null)
        		{
        			preNode.setNextNode(null);
        		}*/
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
    	
    	/* Loop until the end of the list */
        while (currentNode != null) 
        {
        	/* If the current node value equals to the value of the item to be removed */
        	if (currentNode.getElement().equals(item)) 
        	{
        		/* If the item to be removed is in the head of the list */
        		if(currentNode.getElement().equals(head.getElement()))
        		{
        			head = head.getNextNode();
        		}
        		/*else if(currentNode.getNextNode() == null)
        		{
        			preNode.setNextNode(null);
        		}*/
        		else
        		{
        			preNode.setNextNode(currentNode.getNextNode());
        		}
        		
        		currentNode = currentNode.getNextNode();
        		
        		length--;
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
		/* Declaration and assignment of values to variables */
		Node currentNode = head;
		Node preNode = currentNode;
		int count;
		
		/* Loop until the end of the list */
		for(int i = 0; i < length; i++)
		{
			if(checkRepitition(preNode) != false)
			{
				T temp = preNode.getElement();
				currentNode = head;
				count = 0;
				/* Loop until the end of the list */
				while(currentNode != null)
				{
					/* Increment counter if the value of current node equals to the temp value */
					if(currentNode.getElement().equals(temp))
					{
						count++;
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
	
}