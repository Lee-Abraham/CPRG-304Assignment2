package implementations;

import utilities.QueueADT;
import exceptions.EmptyQueueException;
import utilities.Iterator;

public class MyQueue<E> implements QueueADT<E> {

    private MyDLL<E> list;

    public MyQueue() {
        list = new MyDLL<>();
    }

    @Override
    public void enqueue(E element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot enqueue null element");
        }
        list.add(element);
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.remove(0);
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.get(0);
    }

    @Override
    public boolean equals(QueueADT<E> that) {
        if (that == null || this.size() != that.size()) {
            return false;
        }

        Iterator<E> it1 = this.iterator();
        Iterator<E> it2 = that.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            E a = it1.next();
            E b = it2.next();
            if (a == null ? b != null : !a.equals(b)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public E[] toArray(E[] copy) throws NullPointerException {
        if (copy == null) {
            throw new NullPointerException("Array cannot be null");
        }
        return list.toArray(copy);
    }

    @Override
    public boolean contains(E obj) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException("Cannot search for null element");
        }
        return list.contains(obj);
    }

    @Override
    public int search(E element) {
        if (element == null) return -1;

        int index = 1;
        Iterator<E> it = list.iterator();
        while (it.hasNext()) {
            if (element.equals(it.next())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    public void clear() {
        list.clear();
    }

    @Override
    public void dequeueAll() {
        clear();
    }
}