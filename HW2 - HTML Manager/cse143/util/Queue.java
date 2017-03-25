package cse143.util;

/** A collection designed for holding elements prior to processing. 
 * Queues provide special insertion, extraction, and inspection operations. Queues
 * have a single insertion point and a single removal point. Elements other than the
 * front of the queue may not be inspected. */
public interface Queue<E> {

    /** Inserts the specified element into this queue.
     *  @param e The element to add */ 
    public void enqueue(E e);

    /** Retrieves and removes the head of this queue.
     *  @return the head of this queue
     *  @throws NoSuchElementException if this queue is empty */
    public E dequeue(); 

    /** Retrieves, but does not remove, the head of this queue.
     **  @return the head of this queue
     *   @throws NoSuchElementException if this queue is empty */
    public E peek();

    /** Returns true if this queue contains no elements.
     *  @return true if this queue contains no elements */ 
    public boolean isEmpty();

    /** Returns the number of elements in this queue. 
     *  @return the number of elements in this queue */
    public int size();
}
