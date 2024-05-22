package Chess.GameManager;

import org.tinylog.Logger;
import puzzle.TwoPhaseMoveState;
import Chess.GameManager.Positions.*;

import java.util.HashSet;
import java.util.Objects;
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
            Logger.info(String.format("Target position set at %s", targetPosition.getPosition().toString()));
        }
        else
        {
            throw new IllegalArgumentException();
        }

        if(canPlacePieces(king, knight))
        {
            kingPosition = new KingPiecePosition(king.row(),king.col());
            knightPosition = new KnightPiecePosition(knight.row(),knight.col());
            Logger.info(String.format("Kings position set to %s and knights set to %s", kingPosition.getPosition().toString(), knightPosition.getPosition().toString()));
        }
        else
        {
            throw new IllegalArgumentException();
        }

        if(isSolved())
        {
            Logger.info("Game was already solved");
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
            if(move.from().equals(chessPieceMove))
            {
                Logger.info(String.format("Legal move found at %s", chessPieceMove));
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
        return (targetPosition.getPosition().equals(kingPosition.getPosition()) || targetPosition.getPosition().equals(knightPosition.getPosition()));
    }

    /***
     * {@return a boolean that validates the target move}
     * @param chessPieceMoveTwoPhaseMove contains the starting and ending position
     */
    @Override
    public boolean isLegalMove(TwoPhaseMove<Position> chessPieceMoveTwoPhaseMove)
    {
        if(chessPieceMoveTwoPhaseMove.from().equals(chessPieceMoveTwoPhaseMove.to()))
        {
            Logger.info("Piece stays in place");
            return true;
        }

        if(chessPieceMoveTwoPhaseMove.from().equals(kingPosition.getPosition()))
        {
            //Logger.info(String.format("Trying to move king from %s to %s", kingPosition.getPosition().toString(), chessPieceMoveTwoPhaseMove.to()));
            return (canPlacePieces(chessPieceMoveTwoPhaseMove.to(), knightPosition.getPosition()) &&
                    kingsLegalMove(chessPieceMoveTwoPhaseMove.to()));
        }
        else
        {
            //Logger.info(String.format("Trying to move knight from %s to %s", kingPosition.getPosition().toString(), chessPieceMoveTwoPhaseMove.to()));
            return (canPlacePieces(chessPieceMoveTwoPhaseMove.to(), kingPosition.getPosition()) &&
                    knightsLegalMove(chessPieceMoveTwoPhaseMove.to()));
        }
    }

    /***
     * Moves a piece in the board
     * @param chessPieceMoveTwoPhaseMove contains the starting, and the ending position
     */
    @Override
    public void makeMove(TwoPhaseMove<Position> chessPieceMoveTwoPhaseMove)
    {
        if(!isLegalToMoveFrom(chessPieceMoveTwoPhaseMove.from()) || !isLegalMove(chessPieceMoveTwoPhaseMove))
        {
            Logger.info(String.format("Illegal move from %s to %s", chessPieceMoveTwoPhaseMove.from(), chessPieceMoveTwoPhaseMove.to()));
            throw new IllegalArgumentException();
        }

        if(chessPieceMoveTwoPhaseMove.from().equals(kingPosition.getPosition()))
        {
            Logger.info(String.format("King moved from %s to %s", chessPieceMoveTwoPhaseMove.from(), chessPieceMoveTwoPhaseMove.to()));
            kingPosition.setPosition(chessPieceMoveTwoPhaseMove.to());
        }
        else
        {
            Logger.info(String.format("Knight moved from %s to %s", chessPieceMoveTwoPhaseMove.from(), chessPieceMoveTwoPhaseMove.to()));
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

        moves.removeIf(move -> !isLegalMove(move));

        Logger.info(String.format("%d moves found", moves.size()));

        return moves;
    }

    /***
     * {@return a copy of the state}
     */
    @Override
    public TwoPhaseMoveState<Position> clone()
    {
        Logger.info("State copied");
        return new ChessState(targetPosition.getPosition(), kingPosition.getPosition(), knightPosition.getPosition());
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

    private boolean kingsLegalMove(Position newPosition)
    {
        if(newPosition.getAbsoluteDistance(kingPosition.getPosition()) > 1)
        {
            return false;
        }

        return true;
    }

    private boolean knightsLegalMove(Position newPosition)
    {
        int absoluteDistance = newPosition.getAbsoluteDistance(knightPosition.getPosition());
        if(absoluteDistance != 2 && absoluteDistance != 1)
        {
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ChessState that = (ChessState) o;
        return Objects.equals(targetPosition.getPosition(), that.targetPosition.getPosition()) &&
                Objects.equals(kingPosition.getPosition(), that.kingPosition.getPosition()) &&
                Objects.equals(knightPosition.getPosition(), that.knightPosition.getPosition());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(targetPosition, kingPosition, knightPosition);
    }

    @Override
    public String toString()
    {
        return String.format("Target: %s, King: %s, Knight: %s", targetPosition, kingPosition, knightPosition);
    }
}
