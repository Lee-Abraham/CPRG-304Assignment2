package implementations;

import utilities.Iterator;
import utilities.ListADT;
import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {

    private static class MyDLLNode<T> {
        T data;
        MyDLLNode<T> next;
        MyDLLNode<T> prev;

        MyDLLNode(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private MyDLLNode<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index < size / 2) {
            MyDLLNode<E> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        } else {
            MyDLLNode<E> temp = tail;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
            return temp;
        }
    }

    @Override
    public boolean add(E toAdd) {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null object");
        }

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
        return true;
    }

    @Override
    public boolean add(int index, E toAdd) {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null object");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == size) {
            return add(toAdd);
        }

        MyDLLNode<E> current = getNode(index);
        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        newNode.next = current;
        newNode.prev = current.prev;

        if (current.prev != null) {
            current.prev.next = newNode;
        } else {
            head = newNode;
        }

        current.prev = newNode;
        size++;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null list");
        }

        Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());
        }

        return true;
    }

    @Override
    public E get(int index) {
        return getNode(index).data;
    }

    @Override
    public E set(int index, E toChange) {
        if (toChange == null) {
            throw new NullPointerException("Cannot set null");
        }

        MyDLLNode<E> node = getNode(index);
        E old = node.data;
        node.data = toChange;
        return old;
    }

    @Override
    public E remove(int index) {
        MyDLLNode<E> node = getNode(index);
        E removed = node.data;

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        size--;
        return removed;
    }

    @Override
    public E remove(E toRemove) {
        if (toRemove == null) {
            throw new NullPointerException("Cannot remove null object");
        }

        MyDLLNode<E> current = head;
        int index = 0;

        while (current != null) {
            if (current.data.equals(toRemove)) {
                return remove(index);
            }
            current = current.next;
            index++;
        }

        return null;
    }

    @Override
    public boolean contains(E toFind) {
        if (toFind == null) {
            throw new NullPointerException("Cannot search for null");
        }

        MyDLLNode<E> temp = head;
        while (temp != null) {
            if (temp.data.equals(toFind)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public E[] toArray(E[] toHold) {
        if (toHold == null) {
            throw new NullPointerException("Array cannot be null");
        }

        if (toHold.length < size) {
            @SuppressWarnings("unchecked")
            E[] newArr = (E[]) java.lang.reflect.Array
                    .newInstance(toHold.getClass().getComponentType(), size);
            toHold = newArr;
        }

        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
            toHold[i++] = current.data;
            current = current.next;
        }

        return toHold;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        MyDLLNode<E> current = head;

        while (current != null) {
            arr[i++] = current.data;
            current = current.next;
        }
        return arr;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private MyDLLNode<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                E val = current.data;
                current = current.next;
                return val;
            }
        };
    }
}
