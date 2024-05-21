package Chess.GameManager;

import javafx.geometry.Pos;
import puzzle.TwoPhaseMoveState;
import Chess.GameManager.Positions.*;

import java.util.HashSet;
import java.util.Set;

public class ChessState implements TwoPhaseMoveState<Position>
{
    private static final short BOARD_SIZE = 8;

    private PiecePosition targetPosition;
    private KingPiecePosition kingPosition;
    private KnightPiecePosition knightPosition;

    public ChessState()
    {
        this(new Position(6,7), new Position(1,5), new Position(2,5));
    }

    public ChessState(Position target, Position king, Position knight)
    {
        if(isOnBoard(target))
        {
            targetPosition = new PiecePosition(target.row(),target.col());
        }
        else
        {
            throw new IllegalArgumentException();
        }

        if(canPlacePieces(king, knight) && !isSolved())
        {
            kingPosition = new KingPiecePosition(king.row(),king.col());
            knightPosition = new KnightPiecePosition(knight.row(),knight.col());
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isLegalToMoveFrom(Position chessPieceMove)
    {
        var moves = getLegalMoves();
        boolean hasMove = false;

        for(var move : moves)
        {
            if(move.from() == chessPieceMove)
            {
                hasMove = true;
                break;
            }
        }

        return (isOnBoard(chessPieceMove) && hasMove);
    }

    @Override
    public boolean isSolved()
    {
        return targetPosition.equals(kingPosition) || targetPosition.equals(knightPosition);
    }

    @Override
    public boolean isLegalMove(TwoPhaseMove<Position> chessPieceMoveTwoPhaseMove)
    {
        if(chessPieceMoveTwoPhaseMove.from().equals(kingPosition.getPosition()))
        {
            return canPlacePieces(chessPieceMoveTwoPhaseMove.to(), knightPosition.getPosition());
        }
        else
        {
            return canPlacePieces(chessPieceMoveTwoPhaseMove.to(), kingPosition.getPosition());
        }
    }

    @Override
    public void makeMove(TwoPhaseMove<Position> chessPieceMoveTwoPhaseMove)
    {
        if(!isLegalToMoveFrom(chessPieceMoveTwoPhaseMove.from()) && !isLegalMove(chessPieceMoveTwoPhaseMove))
        {
            throw new IllegalArgumentException();
        }

        if(chessPieceMoveTwoPhaseMove.from().equals(kingPosition.getPosition()))
        {
            kingPosition.setPosition(chessPieceMoveTwoPhaseMove.to());
        }
        else
        {
            knightPosition.setPosition(chessPieceMoveTwoPhaseMove.to());
        }
    }

    @Override
    public Set<TwoPhaseMove<Position>> getLegalMoves()
    {
        Set<TwoPhaseMove<Position>> moves = new HashSet<TwoPhaseMove<Position>>();
        var knightsMoves = knightPosition.GetAllMoves();
        var kingsMoves = kingPosition.GetAllMoves();

        knightsMoves.retainAll(kingsMoves);
        kingsMoves.retainAll(knightsMoves);

        for(var move : knightsMoves)
        {
            moves.add(new TwoPhaseMove<Position>(knightPosition.getPosition(), move));
        }

        for(var move : kingsMoves)
        {
            moves.add(new TwoPhaseMove<Position>(kingPosition.getPosition(), move));
        }

        for(var move : moves)
        {
            if(!isLegalMove(move))
            {
                moves.remove(move);
            }
        }

        return moves;
    }

    @Override
    public TwoPhaseMoveState<Position> clone()
    {
        TwoPhaseMoveState<Position> copy;
        try
        {
            copy = (TwoPhaseMoveState<Position>)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new AssertionError();
        }

        return copy;
    }

    private boolean isOnBoard(Position position)
    {
        return !(position.col() < 0 || position.row() < 0
        || position.col() >= BOARD_SIZE || position.row() >= BOARD_SIZE);
    }

    private boolean canPlacePieces(Position a, Position b)
    {
        if(!isOnBoard(a) || !isOnBoard(b))
        {
            return false;
        }

        return !(a.equals(b));
    }

    private boolean kingsLegalMove(Position newPosition)
    {
        if(newPosition.getAbsoluteDistance(kingPosition.getPosition()) > 1)
        {
            return false;
        }

        return isOnBoard(newPosition);
    }

    private boolean knightsLegalMove(Position newPosition)
    {
        int absoluteDistance = newPosition.getAbsoluteDistance(knightPosition.getPosition());
        if(absoluteDistance != 2 && absoluteDistance != 1)
        {
            return false;
        }

        return isOnBoard(newPosition);
    }
}
