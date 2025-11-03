package appDomain;
import exceptions.StackException;

/**
 * StackADT.java
 * @date  10/29/2025
 * @author Lee Abraham Valera
 * @version 1.8
 * 
 * <p>
 * The <code>StackADT</code> interface is designed to be used as a basis
 *  for the Stack data structure. 
 * </p>
 * 
 * @param <E> The object this list hold.
 *
 */
public interface StackADT<E> {
	/**
	 * Adds elements to the stack
	 * 
	 * Pre-condition: Stack Object initialize
	 * 
	 * Post-condition: Element is added on-top of stack
	 * 
	 * @Param <E>
	 * 
	 * @throws StackException is thrown if element is null.
	 * 
	 */
	public void push(E element) throws StackException;
	
	/**
	 * Remove elements in the stack
	 * 
	 * Pre-condition: Stack Object initialize
	 * 
	 * Post-condition: Element is remove  from top of stack
	 * 
	 * @throws StackException is thrown if no element is inside stack.
	 * 
	 * @return <E> element
	 */
	public E pop() throws StackException;
	
	/**
	 * Check the first element in the stack
	 * 
	 * Pre-condition: Stack Object initialize
	 * 
	 * Post-condition: Element is seen from stack
	 * 
	 * @throws StackException is thrown if no element is inside stack.
	 * 
	 * @return <E> element
	 * 
	 */
	public E peek() throws StackException;
	
}
