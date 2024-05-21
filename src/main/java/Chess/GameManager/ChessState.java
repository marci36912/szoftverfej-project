package Chess.GameManager;

import puzzle.TwoPhaseMoveState;
import Chess.GameManager.Positions.*;

import java.util.Set;

public class ChessState implements TwoPhaseMoveState<Position>
{
    private static final short BOARD_SIZE = 8;

    private PiecePosition targetPosition;
    private KingPiecePosition kingsPosition;
    private KnightPiecePosition knightPosition;

    public ChessState()
    {
        this(new Position(0,0), new Position(0,0), new Position(0,0));
    }

    public ChessState(Position target, Position king, Position knight)
    {
        if(isOnBoard(target))
        {
            targetPosition = new PiecePosition(target.row(),target.col());
        }
        if(canPlacePieces(king, knight))
        {
            kingsPosition = new KingPiecePosition(king.row(),king.col());
            knightPosition = new KnightPiecePosition(knight.row(),knight.col());
        }
    }

    @Override
    public boolean isLegalToMoveFrom(Position chessPieceMove)
    {

        return false;
    }

    @Override
    public boolean isSolved()
    {
        return targetPosition.equals(kingsPosition) || targetPosition.equals(knightPosition);
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

    private boolean isOnBoard(Position position)
    {
        if(position.col() < 0 || position.row() < 0
        || position.col() >= BOARD_SIZE || position.row() >= BOARD_SIZE)
        {
            throw new IllegalArgumentException("Piece is out of bounds.");
        }

        return true;
    }

    private boolean canPlacePieces(Position king, Position knight)
    {
        isOnBoard(king);
        isOnBoard(knight);

        if(king.equals(targetPosition.getPosition()) || knight.equals(targetPosition.getPosition()))
        {
            throw new IllegalArgumentException("Can't set the pieces position to the targets position.");
        }

        if(king.equals(knight))
        {
            throw new IllegalArgumentException("The pieces can't have the same position.");
        }

        return true;
    }
}
