import org.junit.Test;


import static org.junit.Assert.*;

public class NodeTest {
    Position test1 = new Position(0,0);
    Position test2 = new Position(0,1);
    Position test3 = new Position(1,1);
    Position test4 = new Position(4,4);
    @Test
    public void childSize() {
        Node<Position>  node = new Node<Position>(test1);
        assertEquals(0, node.childSize());
        Node<Position> temp = new Node<Position>(test2);
        node.addNextNode(temp);
        assertEquals(1, node.childSize());
        
    }

    @Test
    public void getNext() {
        Node<Position>  node = new Node<Position>(test1);
        Node<Position> temp = new Node<Position>(test2);
        Node<Position> temp2 = new Node<Position>(test3);
        node.addNextNode(temp);
        node.addNextNode(temp2);
        assertEquals(test3.getx(), node.getNext().getElement().getx());
        assertEquals(test3.gety(), node.getNext().getElement().gety());

    }
    @Test(expected = IllegalStateException.class)
    public void getNextEmptyStack() {
        Node<Position>  node = new Node<Position>(test1);
        node.getNext();
    }

    @Test
    public void addNextNode() {
        Node<Position>  node = new Node<Position>(test1);
        Node<Position> temp = new Node<Position>(test2);
        Node<Position> temp2 = new Node<Position>(test3);
        assertEquals(0, node.childSize());
        node.addNextNode(temp);
        node.addNextNode(temp2);
        assertEquals(2, node.childSize());
        node.addNextNode(temp);
        node.addNextNode(temp2);
        node.addNextNode(temp);
        node.addNextNode(temp2);
        assertEquals(6, node.childSize());
        
    }

    @Test
    public void removeNextNode() {
        Node<Position>  node = new Node<Position>(test1);
        Node<Position> temp = new Node<Position>(test2);
        Node<Position> temp2 = new Node<Position>(test3);
        node.addNextNode(temp);
        node.addNextNode(temp2);
        assertEquals(test3.getx(), node.getNext().getElement().getx());
        assertEquals(test3.gety(), node.getNext().getElement().gety());
        node.removeNextNode(temp2);
        assertEquals(test2.getx(), node.getNext().getElement().getx());
        assertEquals(test2.gety(), node.getNext().getElement().gety());

    }

    @Test
    public void getElement() {
        Node<Position>  node = new Node<Position>(test1);
        assertEquals(test1.getx(), node.getElement().getx());
        assertEquals(test1.gety(), node.getElement().gety());
    }

    @Test
    public void getPrev() {
        Node<Position>  node = new Node<Position>(test1);
        Node<Position> prev1 = new Node<Position>(test2);
        Node<Position> prev2 = new Node<Position>(test3);
        assertEquals(null, node.getPrev());
        node.addNextNode(prev1);
        assertEquals(null, prev1.getPrev());
        prev1.addNextNode(prev2);
        assertEquals(null, prev2.getPrev());
        prev1.setPrev(node);
        prev2.setPrev(prev1);
        assertEquals(node, prev1.getPrev());
        assertEquals(prev1, prev2.getPrev());
    }

    @Test
    public void setPrev() {
        Node<Position>  node = new Node<Position>(test1);
        Node<Position> prev1 = new Node<Position>(test2);
        Node<Position> prev2 = new Node<Position>(test3);
        assertEquals(null, node.getPrev());
        node.addNextNode(prev1);
        assertEquals(null, prev1.getPrev());
        prev1.addNextNode(prev2);
        assertEquals(null, prev2.getPrev());
        prev1.setPrev(node);
        prev2.setPrev(prev1);
        assertEquals(node, prev1.getPrev());
        assertEquals(prev1, prev2.getPrev());
        prev2.setPrev(node);
        assertEquals(node, prev2.getPrev());
        prev2.setPrev(null);
        assertEquals(null, prev2.getPrev());

    }
}