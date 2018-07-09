import java.text.NumberFormat;
import java.util.*;
import java.io.*;

/**
 * MazeTester uses recursion to determine if a maze can be traversed.
 *
 * @author Lewis and Chase
 * @version 4.0
 */
public class MazeTester
{
    /**
     * Creates a new maze, prints its original form, attempts to
     * solve it, and prints out its final form.
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the name of the file containing the maze: ");
        String filename = scan.nextLine();


        System.out.print("Would you like to define a starting position and an ending position?(y/n)");
        String defaultSettings = scan.nextLine().toLowerCase();
        Maze labyrinth = new Maze("E:\\School\\SER222\\Homework\\SER222-3\\src\\" + filename);
        MazeSolver solver = new MazeSolver(labyrinth);
        System.out.println(labyrinth);
        if(defaultSettings.equals("y")){
            int maxRows = labyrinth.getRows()- 1;
            int maxCols = labyrinth.getColumns() -1;
            System.out.println("Enter the starting position (two integers separated by a comma).");
            System.out.print("x must be between 0 and " + maxRows + " and y must be between 0 and " + maxCols + ": ");
            String startingx = scan.nextLine();
            String[] startExploded = startingx.split(",");

            System.out.println("Enter the ending position (two integers separated by a comma).");
            System.out.print("x must be between 0 and " + maxRows + " and y must be between 0 and " + maxCols + ": ");
            String endingx = scan.nextLine();
            String[] endExploded = endingx.split(",");
            int startxParsed;
            int startyParsed;
            int endyParsed;
            int endxParsed;
            try{
                 startxParsed = Integer.parseInt(startExploded[0]);
                 startyParsed = Integer.parseInt(startExploded[1]);
                 endyParsed   = Integer.parseInt(endExploded[0]);
                 endxParsed   = Integer.parseInt(endExploded[1]);

            }
            catch(NumberFormatException exception){
                 startxParsed = 0;
                 startyParsed = 0;
                 endyParsed = labyrinth.getColumns() -1;
                 endxParsed = labyrinth.getRows() -1;
            }
            if(     startxParsed >= 0
                    && startxParsed <= maxRows
                    && startyParsed >= 0
                    && startyParsed <= maxCols
                    && endxParsed >= 0
                    && endxParsed <= maxRows
                    && endyParsed >= 0
                    && endyParsed <= maxCols
                    ){
                if(solver.SmartTraverse(startxParsed,startyParsed,endxParsed,endyParsed)) {
                    System.out.println("The maze was successfully traversed!");
                    System.out.println(labyrinth);


                    String solution = solver.Solution(startxParsed,startyParsed,endxParsed,endyParsed);
                    System.out.println(labyrinth);
                    System.out.println(solution);
                }
                else{
                    System.out.println("There is no possible path.");
                    System.out.println(labyrinth);
                }

            }else{

                throw new IllegalStateException("Bad inputs");
            }

        }else{
            if (solver.traverse()) {
                System.out.println("The maze was successfully traversed!");
                System.out.println(labyrinth);


                String solution = solver.Solution(0,0, labyrinth.getRows() -1,labyrinth.getColumns() -1);
                System.out.println(labyrinth);
                System.out.println(solution);
            }
            else {
                System.out.println("There is no possible path.");
                System.out.println(labyrinth);
            }
        }








    }
}