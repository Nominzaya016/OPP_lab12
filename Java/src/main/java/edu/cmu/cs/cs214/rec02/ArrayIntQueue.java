package edu.cmu.cs.cs214.rec02;

public class ArrayIntQueue {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int head;
    private int tail;
    private int size;

    /**
     * The initial size for new instances of ArrayQueue
     */
    private static final int INITIAL_SIZE = 10;

    /**
     * Constructs an empty queue with an initial capacity of ten.
     */
    public ArrayIntQueue() {
        elementData = new Object[DEFAULT_CAPACITY];
        head = 0;
        tail = 0;
        size = 0;
    }

    public void enqueue(Integer value) {
        ensureCapacity();
        elementData[tail] = value;
        tail = (tail + 1) % elementData.length;
        size++;
    }

    /** {@inheritDoc} */
    public Integer dequeue() {
        if (isEmpty())
            return null;
        Integer value = (Integer) elementData[head];
        elementData[head] = null;
        head = (head + 1) % elementData.length;
        size--;
        return value;
    }

    public Integer peek() {
        if (isEmpty())
            return null;
        return (Integer) elementData[head];
    }

    public void clear() {
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] = null;
        }
        head = 0;
        tail = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** {@inheritDoc} */
    public int size() {
        return size;
    }

    /**
     * Increases the capacity of this <tt>ArrayIntQueue</tt> instance, if
     * necessary, to ensure that it can hold at least size + 1 elements.
     */
    private void ensureCapacity() {
        if (size == elementData.length) {
            int newCapacity = elementData.length * 2;
            Object[] newArray = new Object[newCapacity];

            for (int i = 0; i < size; i++) {
                newArray[i] = elementData[(head + i) % elementData.length];
            }

            elementData = newArray;
            head = 0;
            tail = size;
        }
    }
}