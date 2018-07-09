
import java.util.*;

/**
 * MazeSolver attempts to recursively traverse a Maze. The goal is to get from the
 * given starting position to the bottom right, following a path of 1's. Arbitrary
 * constants are used to represent locations in the maze that have been TRIED
 * and that are part of the solution PATH.
 *
 * @author Lewis and Chase
 * @version 4.0
 */
public class MazeSolver
{
    private Maze maze;

    /**
     * Constructor for the MazeSolver class.
     */
    public MazeSolver(Maze maze)
    {
        this.maze = maze;
    }

    /**
     * Attempts to recursively traverse the maze. Inserts special
     * characters indicating locations that have been TRIED and that
     * eventually become part of the solution PATH.
     *
     * @return true if the maze has been solved
     */
    public boolean traverse()
    {
        boolean done = false;
        int row, column;
        Position pos = new Position();
        Deque<Position> stack = new LinkedList<Position>();
        stack.push(pos);
        while (!(done) && !stack.isEmpty())
        {
            pos = stack.pop();
            maze.tryPosition(pos.getx(),pos.gety());  // this cell has been tried
            if (pos.getx() == maze.getRows()-1 && pos.gety() == maze.getColumns()-1)
                done = true;  // the maze is solved
            else
            {
                push_new_pos(pos.getx() - 1,pos.gety(), stack);
                push_new_pos(pos.getx() + 1,pos.gety(), stack);
                push_new_pos(pos.getx(),pos.gety() - 1, stack);
                push_new_pos(pos.getx(),pos.gety() + 1, stack);
            }
        }

        return done;
    }
    /**
     * Attempts to recursively traverse the maze. Inserts special
     * characters indicating locations that have been TRIED and that
     * eventually become part of the solution PATH. Has a user defined start and end
     *
     * @param startx starting x coordinate
     * @param starty starting y coordinate
     * @param endy ending y coordinate
     * @param endx ending x coordinate
     * @return true if the maze has been solved
     */
    public boolean SmartTraverse(int startx, int starty, int endx, int endy){
        boolean done = false;
        int row, column;
        Position pos = new Position(startx, starty);
        Deque<Position> stack = new LinkedList<Position>();
        stack.push(pos);
        while (!(done) && !stack.isEmpty())
        {
            pos = stack.pop();
            maze.tryPosition(pos.getx(),pos.gety());  // this cell has been tried
            if (pos.getx() == endx && pos.gety() == endy )
                done = true;  // the maze is solved
            else
            {
                push_new_pos(pos.getx() - 1,pos.gety(), stack);
                push_new_pos(pos.getx() + 1,pos.gety(), stack);
                push_new_pos(pos.getx(),pos.gety() - 1, stack);
                push_new_pos(pos.getx(),pos.gety() + 1, stack);
            }
        }

        return done;
    }
    /**
     * Navigates the maze and determines a path from finish to start. Inserts a
     * 3 along the path when a solution has been determined.The maze MUST
     * have a solution otherwise this method will run forever.
     *
     * @return string that contains the solution by coordinates from start to
     * finish
     * */
    public String Solution(int startx, int starty, int endx, int endy){
        String solution = "";
        boolean done = false;
        int row, column;
        Position pos = new Position(endx , endy);
        LinkedStack<Position> stack = new LinkedStack<Position>();
        stack.push(pos);
        while (!(done))
        {
            pos = stack.peek();
            if (pos.getx() == startx && pos.gety() == starty)
                done = true;  // the maze is solved
            else
            {
                push_solution_pos(pos.getx() - 1,pos.gety(), stack);
                push_solution_pos(pos.getx() + 1,pos.gety(), stack);
                push_solution_pos(pos.getx(),pos.gety() - 1, stack);
                push_solution_pos(pos.getx(),pos.gety() + 1, stack);

                if(stack.getCurrent().size() > 0){

                    stack.nextChild();
                }else{
                    stack.popChild();
                }
            }
        }
        while(stack.getCurrent() != null){
            Position pos1 = stack.peek();
            maze.markPath(pos1.getx(),pos1.gety());
            solution += "(" + pos1.getx() + ", " + pos1.gety() + ") \n";
            stack.moveParent();
        }

        return solution;
    }
    /**
     * Push a new attempted solution onto the stack. The solution must be a valid
     * grid object and must not be a move that we have used in a another solution
     * Once a move is used, it modifies the original maze back to a 1 in order
     * to mark that move as no longer an acceptable solution for future solutions.
     * @param x represents x coordinate
     * @param y represents y coordinate
     * @param stack the working stack of moves within the grid
     */
    private void push_solution_pos(int x, int y, LinkedStack<Position> stack){
        Position pos = new Position(x,y);
        if(maze.validSolutionPath(x,y)){
            if(stack.getCurrent().getPrev() != null && stack.getCurrent().getPrev().getElement().getx() == x &&
                    stack.getCurrent().getPrev().getElement().gety() == y){
                
            }else{
                stack.push(pos);
                maze.solutionAttempted(x,y);

            }
        }
    }


    /**
     * Push a new attempted move onto the stack
     * @param x represents x coordinate
     * @param y represents y coordinate
     * @param stack the working stack of moves within the grid
     * @return stack of moves within the grid
     */
    private void push_new_pos(int x, int y,
                              Deque<Position> stack)
    {
        Position npos = new Position();
        npos.setx(x);
        npos.sety(y);
        if (maze.validPosition(x,y))
            stack.push(npos);
    }

}