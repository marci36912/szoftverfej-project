package Chess.Solver;

import Chess.GameManager.ChessState;
import Chess.GameManager.Position;
import puzzle.TwoPhaseMoveState;
import puzzle.solver.BreadthFirstSearch;

/***
 * A class for the solver
 */
public class Main
{
    /***
     * entry point of the bfs solver
     * @param args cmd line args
     */
    public static void main(String[] args)
    {
        try
        {
            var bfs = new BreadthFirstSearch<TwoPhaseMoveState.TwoPhaseMove<Position>>();
            bfs.solveAndPrintSolution(new ChessState());
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }
}
