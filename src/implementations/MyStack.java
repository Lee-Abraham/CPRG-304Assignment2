package implementations;

import appDomain.StackADT;
import exceptions.StackException;

/**
 * MyStack.java
 * @date  11/03/2025
 * @author Lee Abraham Valera
 * @version 1.8
 */
public class MyStack<E> implements StackADT<E> {
	
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
		if (size == elements.length) {
			resize();
		}
		else {
			elements[size++] = element;
		}
	}
	
	//Remove the first element of the stack than returns.
	@Override
	public E pop() {
		try {
			E head = elements[--size];
			elements[size] = null;
			return head;
		}
		catch(Exception e) {
			throw new StackException("Stack Is Empty" + e);
		}
	}
	
	//Returns the first element of the stack without removing it from the stack
	@Override
	public E peek() {
		try {
			return elements[size - 1];
		}
		catch(Exception e) {
			throw new StackException("Stack is Empty" + e);
		}
	}
	
	//Change the initial size of stack when reaching the limit
	@SuppressWarnings("unchecked")
	public void resize() {
		E[] newElements = (E[]) new Object[elements.length * 2];
		
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}
}
