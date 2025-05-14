package edu.cmu.cs.cs214.rec02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ArrayIntQueueTest {

    private ArrayIntQueue queue;

    @Before
    public void setUp() {
        queue = new ArrayIntQueue(); // Constructor test
    }

    @Test
    public void testConstructorAndIsEmpty() {
        assertNotNull(queue); // ArrayIntQueue() constructor
        assertTrue(queue.isEmpty()); // isEmpty()
        assertEquals(0, queue.size()); // size()
    }

    @Test
    public void testEnqueueAndSize() {
        queue.enqueue(10); // enqueue
        queue.enqueue(20);
        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());
    }

    @Test
    public void testDequeue() {
        queue.enqueue(100);
        queue.enqueue(200);
        assertEquals(Integer.valueOf(100), queue.dequeue()); // dequeue
        assertEquals(1, queue.size());
        assertEquals(Integer.valueOf(200), queue.peek()); // peek
    }

    @Test
    public void testPeek() {
        queue.enqueue(1);
        assertEquals(Integer.valueOf(1), queue.peek()); // peek
        queue.enqueue(2);
        assertEquals(Integer.valueOf(1), queue.peek()); // still 1
    }

    @Test
    public void testClear() {
        queue.enqueue(5);
        queue.enqueue(10);
        queue.clear(); // clear
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.dequeue()); // dequeue from empty
    }

    @Test
    public void testEnqueueDequeueMultiple() {
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testEnsureCapacityGrowth() {
        // Fill up to initial capacity (10)
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        // Trigger ensureCapacity() by adding 11th item
        queue.enqueue(10);

        assertEquals(11, queue.size());

        // Check that all elements are still intact
        for (int i = 0; i < 11; i++) {
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }
    }

    @Test
    public void testWrapAroundWithEnsureCapacity() {
        // Fill queue
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        // Dequeue 3 to shift head
        for (int i = 0; i < 3; i++) {
            queue.dequeue();
        }
        // Add more to wrap around
        for (int i = 10; i < 13; i++) {
            queue.enqueue(i);
        }
        // Add one more to trigger growth
        queue.enqueue(13);

        int expected = 3;
        while (!queue.isEmpty()) {
            assertEquals(Integer.valueOf(expected++), queue.dequeue());
        }
    }
}