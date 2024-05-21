package Chess.GameManager;

import puzzle.TwoPhaseMoveState;
//import Chess.GameManager.Positions.*;

import java.util.Set;

public class ChessState implements TwoPhaseMoveState<Position>
{
    private Position targetPosition;

    public ChessState()
    {
        targetPosition = new Position(0,0);
    }

    @Override
    public boolean isLegalToMoveFrom(Position chessPieceMove)
    {

        return false;
    }

    @Override
    public boolean isSolved()
    {
        return false;
    }

    @Override
    public boolean isLegalMove(TwoPhaseMove<Position> chessPieceMoveTwoPhaseMove)
    {
        return false;
    }

    @Override
    public void makeMove(TwoPhaseMove<Position> chessPieceMoveTwoPhaseMove)
    {

    }

    @Override
    public Set<TwoPhaseMove<Position>> getLegalMoves()
    {
        return Set.of();
    }

    @Override
    public TwoPhaseMoveState<Position> clone()
    {
        return null;
    }
}
