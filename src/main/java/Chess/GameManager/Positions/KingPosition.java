package Chess.GameManager.Positions;

import Chess.GameManager.ChessPieceMove;

import java.util.HashSet;
import java.util.Set;

public class KingPosition extends Position
{
    /***
     * {@return a new position}
     *  @param row the row of the position
     *  @param col the column of the position
     */
    public KingPosition(int row, int col)
    {
        super(row, col);
    }

    /***
     * {@return returns the new position of the king, if it moved up}
     */
    public Position MoveUp()
    {
        return move(ChessPieceMove.KingUp);
    }

    /***
     * {@return returns the new position of the king, if it moved one up and one right}
     */
    public Position MoveUpRight()
    {
        return move(ChessPieceMove.KingUpRight);
    }

    /***
     * {@return returns the new position of the king, if it moved one up and one left}
     */
    public Position MoveUpLeft()
    {
        return move(ChessPieceMove.KingUpRight);
    }

    /***
     * {@return returns the new position of the king, if it moved down}
     */
    public Position MoveDown()
    {
        return move(ChessPieceMove.KingDown);
    }

    /***
     * {@return returns the new position of the king, if it moved one down and one right}
     */
    public Position MoveDownRight()
    {
        return move(ChessPieceMove.KingDownRight);
    }

    /***
     * {@return returns the new position of the king, if it moved one down and one left}
     */
    public Position MoveDownLeft()
    {
        return move(ChessPieceMove.KingDownRight);
    }

    /***
     * {@return returns the new position of the king, if it moved left}
     */
    public Position MoveLeft()
    {
        return move(ChessPieceMove.KingLeft);
    }

    /***
     * {@return returns the new position of the king, if it moved right}
     */
    public Position MoveRight()
    {
        return move(ChessPieceMove.KingRight);
    }

    /***
     * {@return returns a set of every possible position, where the king can move}
     */
    @Override
    public Set<Position> GetAllMoves()
    {
        var moves = new HashSet<Position>();

        moves.add(MoveUp());
        moves.add(MoveUpRight());
        moves.add(MoveUpLeft());
        moves.add(MoveDown());
        moves.add(MoveDownRight());
        moves.add(MoveDownLeft());
        moves.add(MoveLeft());
        moves.add(MoveRight());

        return moves;
    }
}
