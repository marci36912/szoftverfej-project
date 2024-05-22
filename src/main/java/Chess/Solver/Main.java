package Chess.Solver;

import Chess.GameManager.ChessState;
import Chess.GameManager.Position;
import puzzle.TwoPhaseMoveState;
import puzzle.solver.BreadthFirstSearch;

public class Main
{
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
