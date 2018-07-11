import java.util.Stack;
/**
 * Node implementation to be used with LinkedStack when using mazesolver.
 * These nodes have a stack for next elements in order to keep track of the
 * different potential outcomes.
 *
 * @author JonDavid Ebadirad
 * @version 1.0
 */
public class Node<T> {
    private Stack<Node<T>> next;
    private T element;
    private Node<T> prev;
    private int childSize;


    /**
     *
     * constructor with a first element.
     * @param elem element to be added with the node.
     */
    public Node(T elem){
        next = new Stack<Node<T>>();
        element = elem;
        childSize = 0;
    }

    /**
     * childSize of the "child" elements, same as the childSize of the next stack.
     * @return childSize of the "next" stack.
     */
    public int childSize(){
        return childSize;
    }


    /**
     * gets the first Node in the next stack. Does not pop it out of the stack.
     * @return the node on top of the stack.
     * @throws IllegalStateException if the next node is empty.
     */
    public Node<T> getNext(){
        if(!next.isEmpty()){
            return next.peek();
        }
        else{
            throw new IllegalStateException("no more nodes");
        }
    }

    /**
     * adds a node to the next stack.
     * @param node Node to add as a child of this node.
     */
    public void addNextNode(Node<T> node){
        if(null != node){
            next.push(node);
            childSize++;
        }
    }


    /**
     * remove a node from the stack by comparing it to another node.
     * @param node node that is to be removed from the stack.
     */
    public void removeNextNode(Node<T> node){
        next.remove(node);
        childSize--;
    }


    /**
     * gets the value of the element for this node.
     * @return the value of the element for this node.
     */
    public T getElement(){
        return element;
    }

    /**
     * gets the previous Node that is associated with this node.
     */
    public Node<T> getPrev(){
        return prev;
    }

    /**
     * sets the previous node for the current node.
     * @param node the parent of this node.
     */
    public void setPrev(Node<T> node){
        prev = node;
    }


}
