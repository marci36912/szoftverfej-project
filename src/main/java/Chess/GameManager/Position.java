package Chess.GameManager;

import java.util.Objects;

/**
 * Represents the coordinates of a chess board.
 *
 * @param row the row coordinate
 * @param col the column coordinate
 */
public record Position(int row, int col)
{
    /***
     * {@return the position as a string}
     */
    @Override
    public String toString()
    {
        return String.format("(%d,%d)", row, col);
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
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(row, col);
    }

    /***
     * {@return the maximum of an absolute distance between the rows, or the columns}
     * @param position the position of the other point
     */
    public int getAbsoluteDistance(Position position)
    {
        return Math.max(Math.abs(col-position.col()), Math.abs(row-position.row()));
    }
}
