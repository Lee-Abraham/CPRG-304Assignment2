package implementations;

import appDomain.QueueADT;
import exceptions.QueueException;
import utilities.Iterator;

public class MyQueue<E> implements QueueADT<E> {

    private MyDLL<E> list;

    public MyQueue() {
        list = new MyDLL<>();
    }

    @Override
    public void enqueue(E element) throws QueueException {
        if (element == null) {
            throw new QueueException("Cannot enqueue null element");
        }
        list.add(element);
    }

    @Override
    public E dequeue() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Queue is empty");
        }
        return list.remove(0); 
    }

    @Override
    public E peek() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("Queue is empty");
        }
        return list.get(0); 
    }

    @Override
    public boolean equals(QueueADT<E> that) throws QueueException {
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
    public Iterator<E> iterator() throws QueueException {
        return list.iterator();
    }

    @Override
    public Object[] toArray() throws QueueException {
        return list.toArray();
    }

    @Override
    public E[] toArray(E[] copy) throws QueueException {
        return list.toArray(copy);
    }

    @Override
    public boolean contains(E obj) throws QueueException {
        if (obj == null) {
            throw new QueueException("Cannot search for null element");
        }
        return list.contains(obj);
    }

    @Override
    public int size() throws QueueException {
        return list.size();
    }

    @Override
    public boolean isEmpty() throws QueueException {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void clear() throws QueueException {
        list.clear();
    }

    @Override
    public void dequeueAll() throws QueueException {
        clear();
    }
}
