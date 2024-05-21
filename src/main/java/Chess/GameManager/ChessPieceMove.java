package Chess.GameManager;

public enum ChessPieceMove
{
    KingUp(0, 1),
    KingUpRight(1, 1),
    KingUpLeft(-1, 1),
    KingDown(0, -1),
    KingDownRight(1, -1),
    KingDownLeft(-1, -1),
    KingLeft(-1, 0),
    KingRight(1, 0),

    KnightUpUpperLeft(-1,2),
    KnightUpLowerLeft(-2,1),
    KnightUpUpperRight(1,2),
    KnightUpLowerRight(2,1),
    KnightDownUpperLeft(-2,-1),
    KnightDownLowerLeft(-1,-2),
    KnightDownUpperRight(2,-1),
    KnightDownLowerRight(1,-2);

    private final int rowChange;
    private final int colChange;

    ChessPieceMove(int rowChange, int colChange)
    {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    /**
     * {@return the change in the row coordinate when moving to the direction}
     */
    public int getRowChange()
    {
        return rowChange;
    }

    /**
     * {@return the change in the column coordinate when moving to the
     * direction}
     */
    public int getColChange()
    {
        return colChange;
    }

    /**
     * {@return the direction that corresponds to the coordinate changes
     * specified}
     *
     * @param rowChange the change in the row coordinate
     * @param colChange the change in the column coordinate
     * @exception IllegalArgumentException If the moves are invalid
     */
    public static ChessPieceMove of(int rowChange, int colChange)
    {
        for (var move : values())
        {
            if (move.rowChange == rowChange && move.colChange == colChange)
            {
                return move;
            }
        }
        throw new IllegalArgumentException();
    }
}
