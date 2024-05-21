package Chess.GameManager;

import puzzle.TwoPhaseMoveState;
import Chess.GameManager.Positions.*;

import java.util.Set;

public class ChessState implements TwoPhaseMoveState<ChessPieceMove>
{
    private Position targetPosition;
    private KnightPosition knightPosition;
    private KingPosition kingPosition;

    public ChessState()
    {
        targetPosition = new Position(0,0);
        knightPosition = new KnightPosition(0,0);
        kingPosition = new KingPosition(0,0);
    }

    @Override
    public boolean isLegalToMoveFrom(ChessPieceMove chessPieceMove)
    {

        return false;
    }

    @Override
    public boolean isSolved()
    {
        return targetPosition.equals(knightPosition) || targetPosition.equals(kingPosition);
    }

    @Override
    public boolean isLegalMove(TwoPhaseMove<ChessPieceMove> chessPieceMoveTwoPhaseMove)
    {
        return false;
    }

    @Override
    public void makeMove(TwoPhaseMove<ChessPieceMove> chessPieceMoveTwoPhaseMove)
    {

    }

    @Override
    public Set<TwoPhaseMove<ChessPieceMove>> getLegalMoves()
    {
        return Set.of();
    }

    @Override
    public TwoPhaseMoveState<ChessPieceMove> clone()
    {
        return null;
    }
}
