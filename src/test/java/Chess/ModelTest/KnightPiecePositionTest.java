package Chess.ModelTest;

import Chess.GameManager.Position;
import Chess.GameManager.Positions.KnightPiecePosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class KnightPiecePositionTest
{
    private KnightPiecePosition knightsPosition;

    @BeforeEach
    void setValuesToZero()
    {
        knightsPosition = new KnightPiecePosition(0,0);
    }

    @Test
    void moveUpUpperLeft()
    {
        var newPosition = knightsPosition.MoveUpUpperLeft();
        var expectedPosition = new Position(-1,-2);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveUpLowerLeft()
    {
        var newPosition = knightsPosition.MoveUpLowerLeft();
        var expectedPosition = new Position(-2,-1);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveUpUpperRight()
    {
        var newPosition = knightsPosition.MoveUpUpperRight();
        var expectedPosition = new Position(1,-2);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveUpLowerRight()
    {
        var newPosition = knightsPosition.MoveUpLowerRight();
        var expectedPosition = new Position(2,-1);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveDownUpperLeft()
    {
        var newPosition = knightsPosition.MoveDownUpperLeft();
        var expectedPosition = new Position(-2,1);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveDownLowerLeft()
    {
        var newPosition = knightsPosition.MoveDownLowerLeft();
        var expectedPosition = new Position(-1,2);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveDownUpperRight()
    {
        var newPosition = knightsPosition.MoveDownUpperRight();
        var expectedPosition = new Position(2,1);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void moveDownLowerRight()
    {
        var newPosition = knightsPosition.MoveDownLowerRight();
        var expectedPosition = new Position(1,2);

        Assertions.assertEquals(newPosition.getPosition(), expectedPosition);
    }

    @Test
    void getAllMoves()
    {
        var expectedHashSet = new HashSet<Position>();

        expectedHashSet.add(new Position(-1,-2));
        expectedHashSet.add(new Position(-2,-1));
        expectedHashSet.add(new Position(1,-2));
        expectedHashSet.add(new Position(2,-1));
        expectedHashSet.add(new Position(-2,1));
        expectedHashSet.add(new Position(-1,2));
        expectedHashSet.add(new Position(2,1));
        expectedHashSet.add(new Position(1,2));

        var actualHashSet = knightsPosition.GetAllMoves();

        Assertions.assertEquals(expectedHashSet, actualHashSet);
    }
}
