package Chess.GameManager.Positions;

import Chess.GameManager.ChessPieceMove;

import java.util.Objects;
import java.util.Set;

public class Position
{
    protected int row;
    protected int col;

    /***
     * {@return a new position}
     *
     * @param row the row of the position
     * @param col the column of the position
     */
    public Position(int row, int col)
    {
        this.row = row;
        this.col = col;
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
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    /***
     * {@return the hash code of the position}
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(row, col);
    }

    /***
     * {@return the position as a string}
     */
    @Override
    public String toString()
    {
        return String.format("(%d,%d)", row, col);
    }

    protected Position move(ChessPieceMove move)
    {
        return new Position(row+move.getRowChange(), col+move.getColChange());
    }

    protected Set<Position> GetAllMoves()
    {
        return null;
    }
}
