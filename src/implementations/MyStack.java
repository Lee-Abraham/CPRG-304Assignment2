package implementations;

import utilities.Iterator;
import java.util.NoSuchElementException;
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
	
	//Clears the stack
	public void clear() {
		//Makes each element inside the stack into the null
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}
	
	//Checks if the stack is empty
	public boolean isEmpty() {
		//Check if size is empty.
		return size == 0;
	}
	
	//Checks the size of the stack
	public int size() {
		//Returns the size of stack
		return size;
	}
	
	
	//Searches for location of the element in a stack
	public int search(E target) {
		//Iterates over the stack
		for (int i = size - 1; i >=  0;  i--) {
			//check each elements in the stack and if its equals
			if (elements[i].equals(target)) {
				//return where the element is.
				return size - 1 - i;
			}
		}
		
		//No element inside the stack.
		return -1;
	}
	
	//Converts stack into array
	@SuppressWarnings("unchecked")
	public E[] toArray(E[] targetArray) {
		//Check if array is null
		if (targetArray == null) {
			throw new NullPointerException("Array is empty");
		}
		//Increase the size of array.
		else if (targetArray.length < size) {
			E[] newArray =  (E[]) new Object[size];
			targetArray = newArray;
		}
		//Iterates over the stack and makes it into an array.
		for (int i = 0; i < size; i++) {
			//Place the stack object into the right array position.
			targetArray[i] =  elements[i];
		}
		//Returns the array.
		return targetArray;
	}
	
	//Converts stack into array
	@SuppressWarnings("unchecked")
	public E[] toArray() {
		//Creates a new array
	    E[] result = (E[]) new Object[size];
	    //Iterates and put the stack elements into the array.
	    for (int i = 0; i < size; i++) {
	        result[i] = elements[size - 1 - i]; // LIFO order
	    }
	    //returns the new array.
	    return result;

	}
	
	//Check if elements inside stack
	public boolean contains(E element)  {
		if (element == null) {
			throw new NullPointerException("Canot check for null");
		}
		else  {
			for (int i = size - 1; i >= 0; i--) {
				if (elements[i].equals(element)) {
					return true;
				}
			}
			
			return false;
		}
	}
	
	public boolean stackOverflow() {
		return false;
	}
	
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
			if (hasNext()) {
				throw new StackException("");
			}
			else {
				return elements[current--];
			}
		}
	}
}
