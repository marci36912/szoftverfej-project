package Chess.GameManager;

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

    /***
     * Default constructor for the game, starts with the target at (7,8), king at (2,6) and knight at (3,6).
     */
    public ChessState()
    {
        this(new Position(6,7), new Position(1,5), new Position(2,5));
    }

    /***
     * Constructor for the game, with costume positions
     * @param target the position of the target
     * @param king the starting position of the king
     * @param knight the starting position of the knight
     *
     * @exception IllegalArgumentException when a position is out of bounds, or overlapping with each others.
     */
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

    /***
     * {@return a boolean that validates the move}
     * @param chessPieceMove the position we would like to move from
     */
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

    /***
     * {@return a boolean value that tells us if the game is solved}
     */
    @Override
    public boolean isSolved()
    {
        return targetPosition.equals(kingPosition) || targetPosition.equals(knightPosition);
    }

    /***
     * {@return a boolean that validates the target move}
     * @param chessPieceMoveTwoPhaseMove contains the starting and ending position
     */
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

    /***
     * Moves a piece in the board
     * @param chessPieceMoveTwoPhaseMove contains the starting, and the ending position
     */
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

    /***
     * {@return a set of the possible moves}
     */
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

    /***
     * {@return a copy of the state}
     */
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

    /***
     * {@return the current position of the king}
     */
    public Position getKingsPosition()
    {
        return kingPosition.getPosition();
    }

    /***
     * {@return the current position of the knight}
     */
    public Position getKnightPosition()
    {
        return knightPosition.getPosition();
    }


}
