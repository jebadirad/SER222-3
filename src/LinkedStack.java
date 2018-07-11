/**
 * Linked List implementation using Stack.
 *
 * @author JonDavid Ebadirad
 * @version 1.0
 */
public class LinkedStack<T> implements  StackADT<T> {

    private int count;
    private Node<T> current;

    /**
     * Constructor
     */
    public LinkedStack(){
        count = 0;
        current = null;
    }
    


    /**
     * Method used to pop the child out of the parent element. We want to use
     * this method when there are no more moves around the current element, so
     * we replace the current element with its previous element and remove
     * the child that we were just on. This makes the original "current" element
     * removed from the potential solution.
     * @return the current node value in case you need it.
     */
    public T popChild(){
        if(current.getPrev() != null){

            Node<T> temp = current.getPrev();
            temp.removeNextNode(current);
            if(temp.childSize() > 0){
                current = temp.getNext();
            }else{
                current = temp;
                popChild();
            }
            return current.getElement();
        }else{
            return null;
        }

    }

    /**
     * Replaces the current node, with the previous node.
     * Current node may be null.
     */
    public void moveParent(){
        current = current.getPrev();
    }

    /**
     * Replaces the current node with the first element from the current nodes
     * next stack. getNext will throw an exception. 
     * @throws IllegalStateException if the current state is null.
     */
    public void nextChild(){
        if(current == null){
            throw new IllegalStateException("no children");
        }
        else{
            current = current.getNext();
        }
    }

    /**
     * Creates a new Node element, then sets the current node to the new node if
     * the current node is empty. Otherwise if the current node is not null, the
     * new node will have prev set to the current node, then the new node will be added
     *  to the current node's next stack.
     * @param element  element that needs to be added to the solution list.
     */
    @Override
    public void push(T element){

        Node<T> temp = new Node<T>(element);
        if(null == current){
            temp.setPrev(current);
            current = temp;
        }
        else{
            temp.setPrev(current);
            current.addNextNode(temp);
        }

    }

    /**
     * Checks the current element value
     * @return the value of the current node.
     */
    @Override
    public T peek() {
        return current.getElement();
    }

    /**
     * Gets the current node.
     * @return the current node.
     */
    public Node<T> getCurrent(){
        return current;
    }


}
