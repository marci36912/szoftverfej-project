package Chess.GameManager;

import puzzle.TwoPhaseMoveState;
import Chess.GameManager.Positions.*;

import java.util.Set;

public class ChessState implements TwoPhaseMoveState<ChessPieceMove>
{
    private Position targetPosition;
    private KnightPosition knightPosition;
    private KingPosition kingPosition;
    private boolean kingsTurn;

    public ChessState()
    {
        targetPosition = new Position(0,0);
        knightPosition = new KnightPosition(0,0);
        kingPosition = new KingPosition(0,0);

        kingsTurn = false;
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
        if(kingPosition.equals(chessPieceMoveTwoPhaseMove.from()))
        {
            return switch(chessPieceMoveTwoPhaseMove.from())
            {
                case KingDown -> true;
                case KingDownLeft -> true;
                case KingDownRight -> true;
                case KingUp -> true;
                case KingUpLeft -> true;
                case KingUpRight -> true;
                case KingLeft -> true;
                case KingRight -> true;
                default -> false;
            };
        }
        else
        {
            return switch(chessPieceMoveTwoPhaseMove.from())
            {
                case KnightUpUpperLeft -> true;
                case KnightUpLowerLeft -> true;
                case KnightUpUpperRight -> true;
                case KnightUpLowerRight -> true;
                case KnightDownUpperLeft -> true;
                case KnightDownLowerLeft -> true;
                case KnightDownUpperRight -> true;
                case KnightDownLowerRight -> true;
                default -> false;
            };
        }
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
