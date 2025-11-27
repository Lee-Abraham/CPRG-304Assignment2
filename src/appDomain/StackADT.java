package appDomain;
import exceptions.StackException;
import utilities.Iterator;

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
	 * Pre-condition: Stack Object initialized
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
	 * Pre-condition: Stack Object initialized
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
	 * Pre-condition: Stack Object initialized
	 * 
	 * Post-condition: Element is seen from stack
	 * 
	 * @throws StackException is thrown if no element is inside stack.
	 * 
	 * @return <E> element
	 * 
	 */
	public E peek() throws StackException;
	
	/**
	 * Compares stacks
	 * 
	 * Pre-condition: Stack Object initialized and stack is not empty
	 * 
	 * Post-condition: Get true stack are equal to the other, false otherwise
	 * 
	 * @param stackADT<E>
	 * 
	 * @throws StackException is thrown if stack is null.
	 * 
	 * @return true if stack is equal, false if not
	 */
	public boolean equals( StackADT<E> that) throws StackException;
	
	/**
	 * Go through stack one element at a time
	 * 
	 * Pre-condition: iterator object initialized, stack object initialized
	 * 
	 * Post-condition: iterator checks stack from top to bottom
	 * 
	 * @throws StackException is thrown if stack is null or uninitialized.
	 * 
	 * @return Iterator<E>
	 */
	public Iterator<E> iterator() throws StackException;
	
	/**
	 * Returns array containing elements in stack.
	 * 
	 * Pre-condition:stack object initialized
	 * 
	 * Post-condition: Stack element inside Object Array
	 * 
	 * @throws StackException is thrown if stack is null or uninitialized.
	 * 
	 * @return Object[] containing elements in the stack.
	 */
	public Object[] toArray() throws StackException;
	
	/**
	 * Returns array containing elements in stack.
	 * 
	 * Pre-condition:stack object initialized
	 * 
	 * Post-condition: Stack element inside Object Array
	 * 
	 * @param E[]
	 * 
	 * @throws StackException is thrown if stack is null or uninitialized.
	 * 
	 * @return E[] containing elements in the stack.
	 */
	public E[] toArray(E[] copy) throws StackException;
	
	/**
	 * Returns the index of the element
	 * 
	 * Pre-condition:stack object initialized
	 * 
	 * Post-condition: Index of elements returned.
	 * 
	 * @param object
	 * 
	 * @throws StackException is thrown if object is null.
	 * 
	 * @return index of element as int or -1 if not found.
	 */
	public int search(E obj) throws StackException;
	
	/**
	 * Check if element is inside stack
	 * 
	 * Pre-condition:stack object initialized
	 * 
	 * Post-condition: Return true if element in stack, false otherwise.
	 * 
	 * @param object
	 * 
	 * @throws StackException is thrown if object is null.
	 * 
	 * @return true if object in in Stack, false if not.
	 */
	public boolean contains(E obj) throws StackException;
	
	/**
	 * Return the number of element in stack.
	 * 
	 * Pre-condition: stack object initialized
	 * 
	 * Post-condition: Size of stack is returned
	 * 
	 * @throws StackException is thrown if stack is null or uninitialized.
	 * 
	 * @return size of stack as int.
	 */
	public int size() throws StackException;
	
	/**
	 * Check if stack is null or not null
	 * 
	 * Pre-condition: stack object initialized
	 * 
	 * Post-condition: Stack is empty or not empty
	 * 
	 * @throws StackException is thrown if stack is uninitialized.
	 * 
	 * @return true if stack is empty, false otherwise.
	 */
	public boolean isEmpty() throws StackException;
	
	/**
	 * Removes all elements in stack.
	 * 
	 * Pre-condition: stack object initialized
	 * 
	 * Post-condition: No element inside stack
	 * 
	 * @throws StackException is thrown if stack is uninitialized.
	 * 
	 */
	public void clear() throws StackException;
	
}