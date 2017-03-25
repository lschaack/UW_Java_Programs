package cse143.util;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/** Resizable-array implementation of the <code>Stack</code> interface. Implements all <code>Stack</code> operations.
 *  This class does NOT provide an iterator or access to the middle of the <code>Stack</code>. */
public class ArrayStack<E> implements Stack<E> {
    private ArrayList<E> stack;

    /** Constructs a new empty ArrayStack */
    public ArrayStack() {
        this.stack = new ArrayList<E>();
    }

    /** Pushes an item onto the top of this stack. 
     *  @param e The element to push onto this stack */ 
    public void push(E e) {
        stack.add(e);
    }

    /** Retrieves and removes the top of this stack.
     *  @return the top of this stack 
     *  @throws NoSuchElementException if this stack is empty */
    public E pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.remove(this.size() - 1);
    }

    /** Retrieves, but does not remove, the top of this stack.
     **  @return the top of this stack 
     *   @throws NoSuchElementException if this stack is empty */
    public E peek() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.get(this.size() - 1);
    }

    /** Returns the number of elements in this stack. 
     *  @return the number of elements in this stack*/
    public int size() {
        return this.stack.size();
    }

    /** Returns true if this stack contains no elements.
     *  @return true if this stack contains no elements */ 
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
}
