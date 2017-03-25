package cse143.util;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/** First-In First-Out implementation of the <code>Queue</code> interface.  Implements all <code>Queue</code> operations.
 *  This class does NOT provide an iterator or access to the middle of the <code>Queue</code>. */
public class FIFOQueue<E> implements Queue<E> {
    private LinkedList<E> queue;

    /** Constructs a new empty FIFOQueue */
    public FIFOQueue() {
        this.queue = new LinkedList<E>();
    }

    /** Inserts the specified element into this queue.
     *  @param e The element to add */ 
    public void enqueue(E e) {
        queue.add(e);
    }

    /** Retrieves and removes the head of this queue.
     *  @return the head of this queue
     *  @throws NoSuchElementException if this queue is empty */
    public E dequeue() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue.remove();
    }

    /** Retrieves, but does not remove, the head of this queue.
     **  @return the head of this queue
     *   @throws NoSuchElementException if this queue is empty */
    public E peek() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue.peek();
    }

    /** Returns the number of elements in this queue. 
     *  @return the number of elements in this queue */
    public int size() {
        return this.queue.size();
    }

    /** Returns true if this queue contains no elements.
     *  @return true if this queue contains no elements */ 
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
