package Chess.ModelTest;

import Chess.GameManager.Position;
import Chess.GameManager.Positions.KingPiecePosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

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
        var expectedPosition = new Position(0,-1);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveUpRight()
    {
        var newPosition = kingsPosition.MoveUpRight();
        var expectedPosition = new Position(1,-1);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveUpLeft()
    {
        var newPosition = kingsPosition.MoveUpLeft();
        var expectedPosition = new Position(-1,-1);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveDown()
    {
        var newPosition = kingsPosition.MoveDown();
        var expectedPosition = new Position(0,1);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveDownRight()
    {
        var newPosition = kingsPosition.MoveDownRight();
        var expectedPosition = new Position(1,1);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveDownLeft()
    {
        var newPosition = kingsPosition.MoveDownLeft();
        var expectedPosition = new Position(-1,1);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveLeft()
    {
        var newPosition = kingsPosition.MoveLeft();
        var expectedPosition = new Position(-1,0);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveRight()
    {
        var newPosition = kingsPosition.MoveRight();
        var expectedPosition = new Position(1,0);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void getAllMoves()
    {
        var expectedHashSet = new HashSet<Position>();

        expectedHashSet.add(new Position(0,-1));
        expectedHashSet.add(new Position(1, -1));
        expectedHashSet.add(new Position(-1, -1));
        expectedHashSet.add(new Position(0, 1));
        expectedHashSet.add(new Position(1, 1));
        expectedHashSet.add(new Position(-1, 1));
        expectedHashSet.add(new Position(-1, 0));
        expectedHashSet.add(new Position(1, 0));

        var actualHashSet = kingsPosition.GetAllMoves();

        Assertions.assertEquals(expectedHashSet, actualHashSet);
    }
}