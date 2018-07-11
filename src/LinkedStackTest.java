import org.junit.Assert;

import static org.junit.Assert.*;

public class LinkedStackTest {
    Position test1 = new Position(0,0);
    Position test2 = new Position(0,1);
    Position test3 = new Position(1,1);
    Position test4 = new Position(4,4);
    Node<Position> testPos1 = new Node<Position>(test1);
    Node<Position> testPos2 = new Node<Position>(test2);
    @org.junit.Test
    public void popChild() {
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.push(test1);
        TestStack.push(test2);
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test1.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test1.gety());
        TestStack.nextChild();
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test2.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test2.gety());
        TestStack.popChild();
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test1.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test1.gety());


    }
    @org.junit.Test
    public void popChildMultiChild(){
        Position pop1 = new Position(1,1);
        Position pop2 = new Position(2,2);
        Position pop3 = new Position(3,3);
        Position pop4 = new Position(4,4);
        Position pop5 = new Position(5,5);
        Position pop6 = new Position(6,6);
        Position pop7 = new Position(7,7);
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.push(pop1);
        //current top is pop1
        TestStack.push(pop2);
        //add 1 child to pop1
        TestStack.nextChild();
        //current top is pop 2
        //2 children
        TestStack.push(pop3);
        TestStack.push(pop4);
        //push pop3 and pop4 under pop2
        TestStack.nextChild(); //current top is pop4 with pop3 as a sibling.
        //3 children under pop4.

        TestStack.push(pop5);
        TestStack.push(pop6);
        TestStack.push(pop7);
        assertEquals(TestStack.getCurrent().childSize(), 3);
        //current top is pop4 that has 3 children (5,6,7).

        //this will jump up from our current (4) to its parent (2), and delete
        //node 4 with its children. Then move to node2s next child and set it as
        //the current element.
        Position deleted = TestStack.popChild();
        //deleted is also pop3.
        assertEquals(deleted.gety(), pop3.gety());
        assertEquals(deleted.getx(), pop3.getx());
        assertEquals(TestStack.getCurrent().childSize(), 0);
        assertEquals(TestStack.getCurrent().getElement().gety(), pop3.gety());
        assertEquals(TestStack.getCurrent().getElement().getx(), pop3.getx());
        //this will jump up from our current (3) to its parent (2), and delete
        //node 3 with its children (none). Then if node 2 has no children, it will
        //move up to 2s parent (1), and delete node2, then it will make pop1 the
        //current. Since pop1.prev is null, it will return pop1, since each
        //recursion will set the correct temp, but returns null when when prev
        // = null is reached, which does nothing.
        deleted = TestStack.popChild();
        assertEquals(TestStack.getCurrent().getElement().gety(), pop1.gety());
        assertEquals(TestStack.getCurrent().getElement().getx(), pop1.getx());

    }
    @org.junit.Test
    public void popChildDeleteNode(){
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.push(test1);
        TestStack.push(test2);
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test1.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test1.gety());
        TestStack.nextChild();
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test2.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test2.gety());
        TestStack.popChild();
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test1.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test1.gety());
        assertEquals(TestStack.popChild(), null);
        assertEquals(TestStack.getCurrent().childSize(), 0);

    }
    @org.junit.Test
    public void popChildNull() {
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.push(test1);
        TestStack.push(test2);
        TestStack.push(test2);
        TestStack.push(test2);
        assertEquals(TestStack.popChild(), null);

    }

    @org.junit.Test
    public void moveParent() {
      LinkedStack<Position> TestStack = new LinkedStack<Position>();
      TestStack.push(test1);
      TestStack.push(test2);
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test1.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test1.gety());
        TestStack.nextChild();
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test2.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test2.gety());
        TestStack.moveParent();
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test1.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test1.gety());

    }
    @org.junit.Test
    public void moveParentNull(){
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.push(test1);
        TestStack.moveParent();
        Assert.assertEquals(TestStack.getCurrent(), null);
    }

    @org.junit.Test
    public void nextChild() {
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.push(test1);
        TestStack.push(test2);
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test1.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test1.gety());
        TestStack.nextChild();
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test2.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test2.gety());
    }
    @org.junit.Test(expected = IllegalStateException.class)
    public void nextChildNull() {
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.push(test1);
        Assert.assertEquals(TestStack.getCurrent().getElement().getx(), test1.getx());
        Assert.assertEquals(TestStack.getCurrent().getElement().gety(), test1.gety());
        TestStack.nextChild();
    }
    @org.junit.Test(expected = IllegalStateException.class)
    public void nextChildException(){
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.push(test1);
        TestStack.push(test2);
        TestStack.nextChild();
        TestStack.nextChild();
    }
    @org.junit.Test(expected = IllegalStateException.class)
    public void nextChildExceptionNothingAdded(){
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.nextChild();
    }

    @org.junit.Test
    public void push() {
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.push(test1);
        assertEquals(TestStack.peek().getx(), test1.getx());
        assertEquals(TestStack.peek().gety(), test1.gety());
        assertEquals(TestStack.getCurrent().getPrev(), null);
        TestStack.push(test2);
        assertEquals(TestStack.peek().getx(), test1.getx());
        assertEquals(TestStack.peek().gety(), test1.gety());
        assertEquals(TestStack.getCurrent().getPrev(), null);
        assertEquals(TestStack.getCurrent().childSize(), 1);
        assertEquals(TestStack.getCurrent().getNext().getElement().getx(), test2.getx());
        assertEquals(TestStack.getCurrent().getNext().getElement().gety(), test2.gety());


    }

    @org.junit.Test
    public void peek() {
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.push(test1);
        assertEquals(TestStack.peek().getx(), test1.getx());
        assertEquals(TestStack.peek().gety(), test1.gety());
        TestStack.push(test2);
        assertEquals(TestStack.peek().getx(), test1.getx());
        assertEquals(TestStack.peek().gety(), test1.gety());
    }

    @org.junit.Test
    public void getCurrent() {
        LinkedStack<Position> TestStack = new LinkedStack<Position>();
        TestStack.push(test1);
        assertEquals(TestStack.getCurrent().getElement().getx(), test1.getx());
        assertEquals(TestStack .getCurrent().getElement().gety(), test1.gety());
    }
}