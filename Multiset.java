import java.io.PrintStream;

/**
 * Abstract class for a multiset.  Your implmentation should extend this abstract class and implement all the abstract methods.
 *
 * @param T Type of elements that the multiset can hold.
 * 
 * @author jkcchan
 */
public abstract class  Multiset<T>
{
	/** Delimiter string for print operation. */
	protected static final String printDelim = " | ";

	
	/** 
	 * Add an element into multiset. 
	 * 
	 * @param item Element to add.
	 */
	public abstract void add(T item);

	
	/**
	 * Searches for an element in the multiset.
	 * 
	 * @param item Element to search for.
	 * @return The number of instance of the element in the multiset.  If element is not in the multiset, return 0.
	 */
	public abstract int search(T item);

	
	/**
	 * Remove one instance of element from the multiset.  If element doesn't exist, method just returns.
	 * 
	 * @param item Element to remove. 
	 */
	public abstract void removeOne(T item);
	
	
	/**
	 * Remove all instances of element from the multiset.  If element doesn't exist, method just returns.
	 * 
	 * @param item Element to remove. 
	 */
	public abstract void removeAll(T item);

	
	/**
	 * Prints out all the elements and the number of instances of each to 'out' PrintStream.
	 * 
	 * @param out PrintStream to print out to.
	 */
	public abstract void print(PrintStream out);
	
	
	public class Node
	{
		private T element;
		private Node nextNode;
		
		public Node(T element)
		{
			this.element = element;
			nextNode = null;
		}
		
		public void setElement(T element) 
		{
			this.element = element;
		}
		
		public void setNextNode(Node nextNode) 
		{
			this.nextNode = nextNode;
		}
		
		public T getElement() 
		{
			return element;
		}
		
		public Node getNextNode() 
		{
			return nextNode;
		}
	}
	
	public class BSTNode
	{
		private T item;
		private int instances;
		
		private BSTNode leftChild;
		private BSTNode rightChild;
		
		BSTNode(T item)
		{
			this.item = item;
			instances = 0;
			leftChild = null;
			rightChild = null;
		}
		
		public T getItem() {
			return item;
		}
		
		public void setItem(T item) {
			this.item = item;
		}
		
		public BSTNode getLeftChild() {
			return leftChild;
		}
		
		public BSTNode getRightChild() {
			return rightChild;
		}
		
		public void setLeftChild(BSTNode leftChild) {
			this.leftChild = leftChild;
		}
		
		public void setRightChild(BSTNode rightChild) {
			this.rightChild = rightChild;
		}
		
		public int getInstances() {
			return instances;
		}
		
		public void setInstances(int instances) {
			this.instances = instances;
		}
	}
} // end of abstract class Multiset
