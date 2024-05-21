package Chess.GameManager.Positions;

import Chess.GameManager.ChessPieceMove;

import java.util.HashSet;
import java.util.Set;

public class KnightPosition extends Position
{
    /***
     * {@return a new position}
     *  @param row the row of the position
     *  @param col the column of the position
     */
    public KnightPosition(int row, int col)
    {
        super(row, col);
    }

    /***
     * {@return returns the new position of the knight, if it moved two up and one left}
     */
    public Position MoveUpUpperLeft()
    {
        return move(ChessPieceMove.KnightUpUpperLeft);
    }

    /***
     * {@return returns the new position of the knight, if it moved one up and two left}
     */
    public Position MoveUpLowerLeft()
    {
        return move(ChessPieceMove.KnightUpLowerLeft);
    }

    /***
     * {@return returns the new position of the knight, if it moved two up and one right}
     */
    public Position MoveUpUpperRight()
    {
        return move(ChessPieceMove.KnightUpUpperRight);
    }
    /***
     * {@return returns the new position of the knight, if it moved one up and two right}
     */
    public Position MoveUpLowerRight()
    {
        return move(ChessPieceMove.KnightUpLowerRight);
    }

    /***
     * {@return returns the new position of the knight, if it moved two down and one left}
     */
    public Position MoveDownUpperLeft()
    {
        return move(ChessPieceMove.KnightDownUpperLeft);
    }

    /***
     * {@return returns the new position of the knight, if it moved two down and one left}
     */
    public Position MoveDownLowerLeft()
    {
        return move(ChessPieceMove.KnightDownLowerLeft);
    }

    /***
     * {@return returns the new position of the knight, if it moved one down and two right}
     */
    public Position MoveDownUpperRight()
    {
        return move(ChessPieceMove.KnightDownUpperRight);
    }
    /***
     * {@return returns the new position of the knight, if it moved two down and one right}
     */
    public Position MoveDownLowerRight()
    {
        return move(ChessPieceMove.KnightDownLowerRight);
    }

    /***
     * {@return returns a set of every possible position, where the king can move}
     */
    @Override
    public Set<Position> GetAllMoves()
    {
        var moves = new HashSet<Position>();

        moves.add(MoveUpUpperLeft());
        moves.add(MoveUpLowerLeft());
        moves.add(MoveUpUpperRight());
        moves.add(MoveUpLowerLeft());
        moves.add(MoveDownUpperLeft());
        moves.add(MoveDownLowerLeft());
        moves.add(MoveDownUpperRight());
        moves.add(MoveDownLowerRight());

        return moves;
    }
}
