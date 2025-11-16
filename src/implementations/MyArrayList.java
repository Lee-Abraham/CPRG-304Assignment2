package implementations;
/**
 * Created on November 15 2025
 *
 * @author LeeValera
 * @version 1.8
 */

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E> {
	
	private E[] data;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	
	@SuppressWarnings("unchecked")
	//Constructor
	public MyArrayList() {
		data = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}
	
	//Returns the size of MyArrayList
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	//Clear the current MyArrayList
	@Override
	public void clear() {
		for (int i = 0; i< size; i++) {
			data[i] = null;
		}
		size = 0;
		
	}

	//Add object to list
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		//Check for exception
		if (toAdd == null) {
			throw new NullPointerException("Cannont add null item");
		}
		if (index < 0 || index > size ) {
			throw new IndexOutOfBoundsException("Index out of bound");
		}
		
		//Increase size of list
		if (size == data.length) {
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) new Object[data.length * 2];
			
			for (int i = 0; i < data.length; i++ ) {
				newData[i] = data[i];
			}
			
			data = newData;
		}
		
		//Shift elements to the right
		for ( int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		
		data[index] = toAdd;
		size++;
		return true;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException {
		//Check exception
		if (toAdd == null) {
			throw new NullPointerException("Cannot add null object");
		}
		
		//Increase size of list
		if (size == data.length) {
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) new Object[data.length * 2];
			
			for (int i = 0; i < data.length; i++ ) {
				newData[i] = data[i];
			}
			
			data = newData;
		}
		
		//add item to the next index of the list.
		//Increase the index of current object where the last item is and add new item to last null index.
		data[size++] = toAdd;
		return true;
	}
	
	//Method to add items to a list
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if (toAdd == null) {
			throw new NullPointerException("Cannot add null list");
		}
		
		Iterator<? extends E> it = toAdd.iterator();
		
		while(it.hasNext() ) {
			add(it.next());
		}
		
		return true;
	}
	
	//Method to get object from index
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		//Exception
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bound");
		}
		
		//the data at that index
		return data[index];
	}
	
	//Method to remove object at index than returns the removed object.
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		//Exception
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bound");
		}
		//Store the removed item.
		E removedItem = data[index];
		
		//Loop to move other elements to the left, shifts the elements to be remove to last.
		for (int i = index; i < size; i++ ) {
			data[i] = data[i + 1];
		}
		
		//Makes the last element to null;
		data[size - 1] = null;
		size--;
		
		return removedItem;
	}
	
	//Method to remove given object
	@Override
	public E remove(E toRemove) throws NullPointerException {
		//Exception
		if (toRemove == null) {
			throw new NullPointerException("Cannot remove null object");
		}
		
		//Loop to get index of the object and use remove(index) method to remove object.
		for (int i = 0; i < size; i++) {
			if (data[i].equals(toRemove)) {
				return remove(i);
			}
		}
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bound");
		}
		if (toChange == null) {
			throw new NullPointerException("object given can't be null");
		}
		
		E old = data[index];
		data[index] = toChange;
		
		return old;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	
	//Method to check if item in list
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Element to find is null");
		}
		
		Iterator<E> it = this.iterator();
		
		while (it.hasNext()) {
			if (it.next().equals(toFind)) {
				return true;
			}
		}
		return false;
	}
	
	//method to return an array with same runtime type.
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if (toHold == null) {
			throw new NullPointerException("Array cannot be null");
		}
		
		//Set length of given list to the size of our list.
		if(toHold.length < size) {
			@SuppressWarnings("unchecked")
			E[] newArray = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
			toHold = newArray;
		}
		
		Iterator<E> it = this.iterator();
		int i = 0;
		
		//Loop to parse object to array.
		while(it.hasNext()) {
			toHold[i++] = it.next();
		}
		
		//Makes element null if size of list to big
		if(toHold.length > size) {
			toHold[size] = null;
		}
		
		return toHold;
	}
	
	//Method to return array
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		
		Iterator<E> it = this.iterator();
		
		int i = 0;
		
		//Parse object to list
		while (it.hasNext()) {
			result[i++] = it.next();
		}
		
		return result;
	}


	@Override
	    public Iterator<E> iterator() {
	        return new Iterator<E>() {
	            private int cursor = 0;
	
	            @Override
	            public boolean hasNext() {
	                return cursor < size;
	            }
	
	            @Override
	            public E next() {
	            	if (!hasNext()) {
	            		throw new NoSuchElementException("No more elements in the list");
	            	}
	                return data[cursor++];
	            }
	        };
	}

}
