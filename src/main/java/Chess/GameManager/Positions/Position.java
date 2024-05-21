package Chess.GameManager.Positions;

import Chess.GameManager.ChessPieceMove;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Position
{
    protected int row;
    protected int col;

    public Position(int row, int col)
    {
        this.row = row;
        this.col = col;
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
