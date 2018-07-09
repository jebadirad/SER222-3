/**
 * Simple stack interface used for LinkedStack. Ended up gutting this
 * interface after I implemented more specific methods that related to my solution
 *  that did not really fit how a stack should behave. 
 *
 * @author JonDavid Ebadirad
 * @version 1.0
 */
public interface StackADT<T> {
     T peek();
     void push(T element);
}
