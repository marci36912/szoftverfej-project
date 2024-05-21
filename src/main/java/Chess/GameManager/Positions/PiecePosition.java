package Chess.GameManager.Positions;

import Chess.GameManager.ChessPieceMove;

import java.util.Objects;
import java.util.Set;

public class PiecePosition
{
    protected Chess.GameManager.Position piecePosition;

    /***
     * {@return a new position}
     *
     * @param row the row of the position
     * @param col the column of the position
     */
    public PiecePosition(int row, int col)
    {
        piecePosition = new Chess.GameManager.Position(row, col);
    }

    /***
     * {@return if the two positions are the same}
     */
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
        PiecePosition position = (PiecePosition) o;
        return Objects.equals(piecePosition, position.piecePosition);
    }

    /***
     * {@return the hash code of the position}
     */
    @Override
    public int hashCode()
    {
        return Objects.hashCode(piecePosition);
    }

    protected PiecePosition move(ChessPieceMove move)
    {
        return new PiecePosition(piecePosition.row()+move.getRowChange(), piecePosition.col()+move.getColChange());
    }

    protected Set<PiecePosition> GetAllMoves()
    {
        return null;
    }
}
