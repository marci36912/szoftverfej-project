package Chess.ModelTest;

import Chess.GameManager.Positions.KingPiecePosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingPiecePositionTest
{
    private KingPiecePosition kingsPosition;

    @BeforeEach
    void setValuesToZero()
    {
        kingsPosition = new KingPiecePosition(0,0);
    }


    @Test
    void moveUp()
    {
        var newPosition = kingsPosition.MoveDown();

        
    }

    @Test
    void moveUpRight()
    {
    }

    @Test
    void moveUpLeft()
    {
    }

    @Test
    void moveDown()
    {
    }

    @Test
    void moveDownRight()
    {
    }

    @Test
    void moveDownLeft()
    {
    }

    @Test
    void moveLeft()
    {
    }

    @Test
    void moveRight()
    {
    }

    @Test
    void getAllMoves()
    {
    }
}