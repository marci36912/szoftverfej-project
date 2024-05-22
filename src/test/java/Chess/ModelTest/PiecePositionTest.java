package Chess.ModelTest;

import Chess.GameManager.Positions.PiecePosition;
import Chess.GameManager.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PiecePositionTest
{

    @Test
    void getPositionValuesLegit()
    {
        int row = 0;
        int col = 0;

        var position = new PiecePosition(row, col);

        Assertions.assertEquals(position.getPosition(), new Position(row, col));
    }

    @Test
    void setPosition()
    {
        var position = new PiecePosition(1, 2);
        var newPosition = new Position(0, 0);

        position.setPosition(newPosition);

        Assertions.assertEquals(position.getPosition(), newPosition);
    }
}