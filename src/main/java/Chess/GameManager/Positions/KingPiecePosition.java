package Chess.GameManager.Positions;

import Chess.GameManager.ChessPieceMove;
import Chess.GameManager.Position;

import java.util.HashSet;
import java.util.Set;

public class KingPiecePosition extends PiecePosition
{
    /***
     * {@return a new position}
     *  @param row the row of the position
     *  @param col the column of the position
     */
    public KingPiecePosition(int row, int col)
    {
        super(row, col);
    }

    /***
     * {@return returns the new position of the king, if it moved up}
     */
    public PiecePosition MoveUp()
    {
        return move(ChessPieceMove.KingUp);
    }

    /***
     * {@return returns the new position of the king, if it moved one up and one right}
     */
    public PiecePosition MoveUpRight()
    {
        return move(ChessPieceMove.KingUpRight);
    }

    /***
     * {@return returns the new position of the king, if it moved one up and one left}
     */
    public PiecePosition MoveUpLeft()
    {
        return move(ChessPieceMove.KingUpRight);
    }

    /***
     * {@return returns the new position of the king, if it moved down}
     */
    public PiecePosition MoveDown()
    {
        return move(ChessPieceMove.KingDown);
    }

    /***
     * {@return returns the new position of the king, if it moved one down and one right}
     */
    public PiecePosition MoveDownRight()
    {
        return move(ChessPieceMove.KingDownRight);
    }

    /***
     * {@return returns the new position of the king, if it moved one down and one left}
     */
    public PiecePosition MoveDownLeft()
    {
        return move(ChessPieceMove.KingDownRight);
    }

    /***
     * {@return returns the new position of the king, if it moved left}
     */
    public PiecePosition MoveLeft()
    {
        return move(ChessPieceMove.KingLeft);
    }

    /***
     * {@return returns the new position of the king, if it moved right}
     */
    public PiecePosition MoveRight()
    {
        return move(ChessPieceMove.KingRight);
    }

    /***
     * {@return returns a set of every possible position, where the king can move}
     */
    @Override
    public HashSet<Position> GetAllMoves()
    {
        var moves = new HashSet<Position>();

        moves.add(MoveUp().getPosition());
        moves.add(MoveUpRight().getPosition());
        moves.add(MoveUpLeft().getPosition());
        moves.add(MoveDown().getPosition());
        moves.add(MoveDownRight().getPosition());
        moves.add(MoveDownLeft().getPosition());
        moves.add(MoveLeft().getPosition());
        moves.add(MoveRight().getPosition());

        return moves;
    }
}
