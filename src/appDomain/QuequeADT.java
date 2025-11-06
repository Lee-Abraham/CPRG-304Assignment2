package appDomain;

import exceptions.QuequeException;
import utilities.Iterator;

/**
 * QueueADT.java
 * @date 11/06/2025
 * @version 1.0
 * @author Sean Bauzon
 * 
 * <p>
 * The <code>QueueADT</code> interface defines the contract for a Queue
 * data structure following the FIFO (First-In, First-Out) principle.
 * </p>
 *
 * @param <E> The type of elements this queue holds.
 */
public interface QuequeADT<E> {

    /**
     * Adds an element to the rear (tail) of the queue.
     * 
     * Pre-condition: Queue object initialized.
     * 
     * Post-condition: Element is added to the rear of the queue.
     * 
     * @param element The element to add.
     * 
     * @throws QueueException if the element is null.
     */
    public void enqueue(E element) throws QuequeException;

    /**
     * Removes an element from the front (head) of the queue.
     * 
     * Pre-condition: Queue object initialized and not empty.
     * 
     * Post-condition: Element is removed from the front of the queue.
     * 
     * @throws QueueException if the queue is empty.
     * 
     * @return The element removed from the front of the queue.
     */
    public E dequeue() throws QuequeException;

    /**
     * Returns (without removing) the element at the front of the queue.
     * 
     * Pre-condition: Queue object initialized and not empty.
     * 
     * Post-condition: The element at the front of the queue is returned.
     * 
     * @throws QueueException if the queue is empty.
     * 
     * @return The element at the front of the queue.
     */
    public E peek() throws QuequeException;

    /**
     * Compares two queues for equality.
     * 
     * Pre-condition: Both Queue objects initialized and not null.
     * 
     * Post-condition: Returns true if both queues contain the same elements
     * in the same order; false otherwise.
     * 
     * @param that Another QueueADT to compare.
     * 
     * @throws QueueException if the provided queue is null.
     * 
     * @return true if queues are equal, false otherwise.
     */
    public boolean equals(QuequeADT<E> that) throws QuequeException;

    /**
     * Returns an iterator that traverses the queue from front to rear.
     * 
     * Pre-condition: Queue object initialized.
     * 
     * Post-condition: Iterator is returned to traverse the queue.
     * 
     * @throws QueueException if the queue is uninitialized.
     * 
     * @return Iterator<E> for the queue.
     */
    public Iterator<E> iterator() throws QuequeException;

    /**
     * Returns an array containing all elements in this queue.
     * The head of the queue corresponds to the first element of the array.
     * 
     * Pre-condition: Queue object initialized.
     * 
     * Post-condition: Returns an Object array containing queue elements.
     * 
     * @throws QueueException if the queue is uninitialized.
     * 
     * @return Object[] array of queue elements.
     */
    public Object[] toArray() throws QuequeException;

    /**
     * Returns an array containing all elements in this queue.
     * The head of the queue corresponds to the first element of the array.
     * 
     * Pre-condition: Queue object initialized.
     * 
     * Post-condition: Returns a typed array containing queue elements.
     * 
     * @param copy The array into which the elements will be stored.
     * 
     * @throws QueueException if the queue is uninitialized.
     * 
     * @return E[] containing elements in the queue.
     */
    public E[] toArray(E[] copy) throws QuequeException;

    /**
     * Checks if the specified element exists in the queue.
     * 
     * Pre-condition: Queue object initialized.
     * 
     * Post-condition: Returns true if the element exists, false otherwise.
     * 
     * @param obj The element to check for.
     * 
     * @throws QueueException if the object is null.
     * 
     * @return true if element is found, false otherwise.
     */
    public boolean contains(E obj) throws QuequeException;

    /**
     * Returns the number of elements currently in the queue.
     * 
     * Pre-condition: Queue object initialized.
     * 
     * Post-condition: Size of the queue is returned.
     * 
     * @throws QueueException if the queue is uninitialized.
     * 
     * @return The size of the queue as an int.
     */
    public int size() throws QuequeException;

    /**
     * Checks whether the queue is empty.
     * 
     * Pre-condition: Queue object initialized.
     * 
     * Post-condition: Returns true if the queue has no elements.
     * 
     * @throws QueueException if the queue is uninitialized.
     * 
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() throws QuequeException;

    /**
     * Checks whether the queue is full (optional for fixed-size implementations).
     * 
     * Pre-condition: Queue object initialized.
     * 
     * Post-condition: Returns true if queue is full, false otherwise.
     * 
     * @return true if queue is full, false otherwise.
     */
    public boolean isFull();

    /**
     * Removes all elements from the queue (same as dequeueAll()).
     * 
     * Pre-condition: Queue object initialized.
     * 
     * Post-condition: Queue is empty after the operation.
     * 
     * @throws QueueException if the queue is uninitialized.
     */
    public void clear() throws QuequeException;

    /**
     * Removes all elements from the queue (alias for clear()).
     * 
     * Pre-condition: Queue object initialized.
     * 
     * Post-condition: Queue is empty after the operation.
     * 
     * @throws QueueException if the queue is uninitialized.
     */
    public void dequeueAll() throws QuequeException;
}
