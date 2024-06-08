package Chess.GameManager;

/***
 * An enum, storing all the possible moves.
 */
public enum ChessPieceMove
{
    /***
     * Moves the king up a field.
     */
    KingUp(0, -1),
    /***
     * Moves the king up and right a field.
     */
    KingUpRight(1, -1),
    /***
     * Moves the king up and left a field.
     */
    KingUpLeft(-1, -1),
    /***
     * Moves the king down a field.
     */
    KingDown(0, 1),
    /***
     * Moves the king down and right a field.
     */
    KingDownRight(1, 1),
    /***
     * Moves the king down and left a field.
     */
    KingDownLeft(-1, 1),
    /***
     * Moves the king left a field.
     */
    KingLeft(-1, 0),
    /***
     * Moves the king right a field.
     */
    KingRight(1, 0),

    /***
     * Moves the knight up to the upper left.
     */
    KnightUpUpperLeft(-1,-2),
    /***
     * Moves the knight up to the lower left.
     */
    KnightUpLowerLeft(-2,-1),
    /***
     * Moves the knight up to the upper right.
     */
    KnightUpUpperRight(1,-2),
    /***
     * Moves the knight up to the lower right.
     */
    KnightUpLowerRight(2,-1),
    /***
     * Moves the knight down to the upper left.
     */
    KnightDownUpperLeft(-2,1),
    /***
     * Moves the knight down to the lower left.
     */
    KnightDownLowerLeft(-1,2),
    /***
     * Moves the knight down to the upper right.
     */
    KnightDownUpperRight(2,1),
    /***
     * Moves the knight down to the lower right.
     */
    KnightDownLowerRight(1,2);

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
