package cse143.util;

/** A collection designed for storing elements in LIFO (Last-In First-Out) order.
 * Stacks provide special insertion, extraction, and inspection operations. Stacks 
 * have a single point of insertion AND removal. Elements other than the
 * top of the stack may not be inspected. */
public interface Stack<E> {
    /** Pushes an item onto the top of this stack. 
     *  @param e The element to push onto this stack */ 
    public void push(E e);

    /** Retrieves and removes the top of this stack.
     *  @return the top of this stack 
     *  @throws NoSuchElementException if this stack is empty */
    public E pop(); 

    /** Retrieves, but does not remove, the top of this stack.
     **  @return the top of this stack 
     *   @throws NoSuchElementException if this stack is empty */
    public E peek();

    /** Returns true if this stack contains no elements.
     *  @return true if this stack contains no elements */ 
    public boolean isEmpty();

    /** Returns the number of elements in this stack. 
     *  @return the number of elements in this stack*/
    public int size();
}
