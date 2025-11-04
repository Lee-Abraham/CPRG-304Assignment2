package implementations;

import utilities.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.EmptyStackException;
import appDomain.StackADT;
import exceptions.StackException;

/**
 * MyStack.java
 * @date  11/03/2025
 * @author Lee Abraham Valera
 * @version 1.8
 */
public class MyStack<E> implements StackADT<E>  {
	
	//Attributes
	private E[] elements;
	private int size;
	private static final int initial = 10;
	
	
	//Creates empty My Stack Object.
	@SuppressWarnings("unchecked")
	public MyStack() {
		elements = (E[]) new Object[initial];
		size = 0;
	}
	
	//Adds the element into the stack
	@Override
	public void push(E element) {
		//If element is null  throws exception
		if (element == null) {
			throw new NullPointerException("Cannot add a null element");
		}
		//If Stack has reach max capacity and wants to add new element
		else if (size == elements.length) {
			resize();
		}
		//adds new element inside the stack
		elements[size++] = element;

	}
	
	//Remove the first element of the stack than returns.
	@Override
	public E pop() {
		//Check if the stack is empty
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		//If not empty, remove and gets the first element in the stack
		else {
			E head = elements[--size];
			elements[size] = null;
			return head;
		}
	}
	
	//Returns the first element of the stack without removing it from the stack
	@Override
	public E peek() {
		//Check if stack is empty
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		//Returns the first element in the stack
		else {
			return elements[size - 1];
		}
	}
	
	@Override
	public boolean equals( StackADT<E> that) {
		//Check if stack is not empty
		if (that == null) {
			return false;
		}
		//Check if the stack have the same length
		if (this.size() != that.size()) {
			return false;
		}
		
		//Uses Iterator to check each element in stack
		Iterator<E> thisIter = this.iterator();
		Iterator<E> thatIter = that.iterator();
		
		//Iterates over all elements while stack has a next element.
		while (thisIter.hasNext() && thatIter.hasNext()) {
			//Get next elements of stack
			E thisElem = thisIter.next();
			E thatElem = thatIter.next();
			
			//Return null if stack is different
			if (thisElem == null && thatElem != null) {
				return false;
			}
			//Return null if elements are not equal.
			if (!thisElem.equals(thatElem)) {
				return false;
			}
		}
		
		//Return true if stack is the same
		return true;
	}
	
	//Clears the stack
	@Override
	public void clear() {
		//Makes each element inside the stack into the null
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}
	
	//Checks if the stack is empty
	@Override
	public boolean isEmpty() {
		//Check if size is empty.
		return size == 0;
	}
	
	//Checks the size of the stack
	@Override
	public int size() {
		//Returns the size of stack
		return size;
	}
	
	
	//Searches for location of the element in a stack
	@Override
	public int search(E target) {
		
		if (target == null) {
			return -1;
		}
		 Iterator<E> stackIterator = this.iterator();
		 int index = 1;
		 
		 while (stackIterator.hasNext()) {
			 E elemStack = stackIterator.next();
			 
			 if (elemStack == null && target == null) {
				 return index;
			 }
			 if (elemStack.equals(target)) {
				 return index;
			 }
			 
			 index++;
		 }
		
		//No element inside the stack.
		return -1;
	}
	
	//Converts stack into array
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] copy) {
		//Check if array is null
		if (copy == null) {
			throw new NullPointerException("Array is empty");
		}
		//Increase the size of array.
		if (copy.length < size) {
			//Thank you for being able to use Arrays.copyOF T T
			copy = (E[]) Arrays.copyOf(elements, size, copy.getClass());
		}
		//Iterates over the stack and makes it into an array.
		for (int i = size - 1, j = 0; i >= 0; i--, j++) {
			//Place the stack object into the right array position.
			copy[j] =  elements[i];
		}
		//Returns the array.
		return copy;
	}
	
	//Converts stack into array
	@Override
	public Object[] toArray() {
		//Creates a new array
	    Object[] result = new Object[size];
	    //Iterates and put the stack elements into the array.
	    for (int i = 0; i < size; i++) {
	        result[i] = elements[size - 1 - i]; // LIFO order
	    }
	    //returns the new array.
	    return result;

	}
	
	//Check if elements inside stack
	@Override
	public boolean contains(E element)  {
		//Check if the element given is present
		if (element == null) {
			throw new NullPointerException("Canot check for null");
		}
		//If its not null than do this
		else  {
			//Iterates all element in the stack
			for (int i = size - 1; i >= 0; i--) {
				if (elements[i].equals(element)) {
					//Returns true if an element is inside the stack
					return true;
				}
			}
			//Return false if element is not in stack
			return false;
		}
	}
	
	//Check if the stack overflows.
	public boolean stackOverflow() {
		//Returns false
		return size >= elements.length;
	}
	
	//Change the initial size of stack when reaching the limit
	@SuppressWarnings("unchecked")
	public void resize() {
		//Creates a new stack with a bigger capacity
		E[] newElements = (E[]) new Object[elements.length * 2];
		//Copy the elements inside the stack into the new bigger stack
		System.arraycopy(elements, 0, newElements, 0, initial);	
		//returns the increase stack size with its old elements.
		elements = newElements;
	}
	
	@Override
	//Creates object iterator
	public Iterator<E> iterator() {
		return new StackIterator();
	}
	
	//Implements a stack iterator
	private class StackIterator implements Iterator<E> {
		private int current = size - 1;
		
		//Checks if the next element is not empty
		@Override
		public boolean hasNext() {
			return  current >= 0;
		}
		
		//Returns the next elements
		@Override
		public E  next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Has no next element");
			}
			else {
				return elements[current--];
			}
		}
	}
}
