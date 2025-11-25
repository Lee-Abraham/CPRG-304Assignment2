package implementations;

import utilities.StackADT;
import utilities.Iterator;
import java.util.NoSuchElementException;
import java.util.EmptyStackException;


public class MyStack<E> implements StackADT<E> {
    
    // Use MyArrayList as underlying structure
    private MyArrayList<E> list;
    
    // Creates empty MyStack Object
    public MyStack() {
        list = new MyArrayList<>();
    }
    
    // Adds the element to the top of the stack
    @Override
    public void push(E element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot add a null element");
        }
        list.add(element);
    }
    
    // Remove and return the top element of the stack
    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(list.size() - 1);
    }
    
    // Returns the top element without removing it
    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }
    
    @Override
    public boolean equals(StackADT<E> that) {
        if (that == null) {
            return false;
        }
        if (this.size() != that.size()) {
            return false;
        }
        
        Iterator<E> thisIter = this.iterator();
        Iterator<E> thatIter = that.iterator();
        
        while (thisIter.hasNext() && thatIter.hasNext()) {
            E thisElem = thisIter.next();
            E thatElem = thatIter.next();
            
            if (thisElem == null && thatElem != null) {
                return false;
            }
            if (thisElem != null && !thisElem.equals(thatElem)) {
                return false;
            }
        }
        
        return true;
    }
    
    // Clears the stack
    @Override
    public void clear() {
        list.clear();
    }
    
    // Checks if the stack is empty
    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }
    
    // Returns the size of the stack
    @Override
    public int size() {
        return list.size();
    }
    
    // Searches for location of the element (1-based from top)
    @Override
    public int search(E target) {
        if (target == null) {
            return -1;
        }
        
        int position = 1;
        for (int i = list.size() - 1; i >= 0; i--) {
            E elem = list.get(i);
            if (elem != null && elem.equals(target)) {
                return position;
            }
            position++;
        }
        
        return -1;
    }
    
    // Converts stack into array (LIFO order)
    @Override
    public E[] toArray(E[] copy) throws NullPointerException {
        if (copy == null) {
            throw new NullPointerException("Array cannot be null");
        }
        
        int size = list.size();
        if (copy.length < size) {
            copy = (E[]) java.lang.reflect.Array.newInstance(
                copy.getClass().getComponentType(), size);
        }
        
        // Fill array in LIFO order (top to bottom)
        for (int i = 0; i < size; i++) {
            copy[i] = list.get(size - 1 - i);
        }
        
        return copy;
    }
    
    // Converts stack into Object array (LIFO order)
    @Override
    public Object[] toArray() {
        int size = list.size();
        Object[] result = new Object[size];
        
        // Fill array in LIFO order (top to bottom)
        for (int i = 0; i < size; i++) {
            result[i] = list.get(size - 1 - i);
        }
        
        return result;
    }
    
    // Check if element is in stack
    @Override
    public boolean contains(E element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot check for null");
        }
        return list.contains(element);
    }
    
    // Check if the stack overflows (always false for dynamic list)
    @Override
    public boolean stackOverflow() {
        return false;
    }
    
    // Creates iterator
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }
    
    // Implements a stack iterator (LIFO order)
    private class StackIterator implements Iterator<E> {
        private int current = list.size() - 1;
        
        @Override
        public boolean hasNext() {
            return current >= 0;
        }
        
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            return list.get(current--);
        }
    }
}