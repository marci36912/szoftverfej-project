package Chess.GameManager.Positions;

import Chess.GameManager.ChessPieceMove;

import java.util.HashSet;
import java.util.Set;

public class KnightPiecePosition extends PiecePosition
{
    /***
     * {@return a new position}
     *  @param row the row of the position
     *  @param col the column of the position
     */
    public KnightPiecePosition(int row, int col)
    {
        super(row, col);
    }

    /***
     * {@return returns the new position of the knight, if it moved two up and one left}
     */
    public PiecePosition MoveUpUpperLeft()
    {
        return move(ChessPieceMove.KnightUpUpperLeft);
    }

    /***
     * {@return returns the new position of the knight, if it moved one up and two left}
     */
    public PiecePosition MoveUpLowerLeft()
    {
        return move(ChessPieceMove.KnightUpLowerLeft);
    }

    /***
     * {@return returns the new position of the knight, if it moved two up and one right}
     */
    public PiecePosition MoveUpUpperRight()
    {
        return move(ChessPieceMove.KnightUpUpperRight);
    }
    /***
     * {@return returns the new position of the knight, if it moved one up and two right}
     */
    public PiecePosition MoveUpLowerRight()
    {
        return move(ChessPieceMove.KnightUpLowerRight);
    }

    /***
     * {@return returns the new position of the knight, if it moved two down and one left}
     */
    public PiecePosition MoveDownUpperLeft()
    {
        return move(ChessPieceMove.KnightDownUpperLeft);
    }

    /***
     * {@return returns the new position of the knight, if it moved two down and one left}
     */
    public PiecePosition MoveDownLowerLeft()
    {
        return move(ChessPieceMove.KnightDownLowerLeft);
    }

    /***
     * {@return returns the new position of the knight, if it moved one down and two right}
     */
    public PiecePosition MoveDownUpperRight()
    {
        return move(ChessPieceMove.KnightDownUpperRight);
    }
    /***
     * {@return returns the new position of the knight, if it moved two down and one right}
     */
    public PiecePosition MoveDownLowerRight()
    {
        return move(ChessPieceMove.KnightDownLowerRight);
    }

    /***
     * {@return returns a set of every possible position, where the king can move}
     */
    @Override
    public Set<PiecePosition> GetAllMoves()
    {
        var moves = new HashSet<PiecePosition>();

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
