package Chess.ModelTest;

import Chess.GameManager.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest
{

    @Test
    void testToString()
    {
        var position = new Position(0, 0);
        String expected = "(0,0)";

        Assertions.assertEquals(expected, position.toString());
    }

    @Test
    void getAbsoluteDistanceColDistance()
    {
        int distance = -2;

        var position1 = new Position(0, 0);
        var position2 = new Position(0, distance);

        Assertions.assertEquals(Math.abs(distance), position1.getAbsoluteDistance(position2));
    }

    @Test
    void getAbsoluteDistanceRowDistance()
    {
        int distance = -7;

        var position1 = new Position(0, 0);
        var position2 = new Position(5, distance);

        Assertions.assertEquals(Math.abs(distance), position1.getAbsoluteDistance(position2));
    }
}