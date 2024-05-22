package Chess.ModelTest;

import Chess.GameManager.ChessState;
import Chess.GameManager.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import puzzle.TwoPhaseMoveState;

import static org.junit.jupiter.api.Assertions.*;

class ChessStateTest
{
    @Test
    void DefaultConstructorValuesNotSolved()
    {
        var defaultChessState = new ChessState();

        var kingPosition = defaultChessState.getKingsPosition();
        var knightsPosition = defaultChessState.getKnightPosition();

        Assertions.assertFalse(defaultChessState.isSolved());
    }

    @Test
    void DefaultConstructorValuesNotOverlapping()
    {
        var defaultChessState = new ChessState();

        var kingPosition = defaultChessState.getKingsPosition();
        var knightsPosition = defaultChessState.getKnightPosition();

        Assertions.assertNotEquals(kingPosition, knightsPosition);
    }

    @Test
    void DefaultConstructorValuesHasLegalMove()
    {
        var defaultChessState = new ChessState();

        Assertions.assertNotNull(defaultChessState.getLegalMoves());
    }

    @Test
    void ConstructorTargetNotOnBoard()
    {
        var invalidTargetPosition = new Position(-1, -1);
        var kingPosition = new Position(0, 0);
        var knightsPosition = new Position(0, 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {new ChessState(invalidTargetPosition, kingPosition, knightsPosition);});
    }

    @Test
    void ConstructorIsSolved()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(0, 0);
        var knightsPosition = new Position(0, 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {new ChessState(targetPosition, kingPosition, knightsPosition);});
    }

    @Test
    void ConstructorPiecesNotOnBoard()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(-1, 0);
        var knightsPosition = new Position(0, 8);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {new ChessState(targetPosition, kingPosition, knightsPosition);});
    }

    @Test
    void ConstructorPiecesOverlapping()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 1);
        var knightsPosition = new Position(1, 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {new ChessState(targetPosition, kingPosition, knightsPosition);});
    }


    @Test
    void isLegalToMoveFromKnightCanMove()
    {
        var targetPosition = new Position(7, 7);
        var kingPosition = new Position(2, 1);
        var knightsPosition = new Position(3, 4);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        Assertions.assertTrue(chessState.isLegalToMoveFrom(knightsPosition));
    }

    @Test
    void isLegalToMoveFromKingCanMove()
    {
        var targetPosition = new Position(7, 7);
        var kingPosition = new Position(2, 1);
        var knightsPosition = new Position(3, 4);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        Assertions.assertTrue(chessState.isLegalToMoveFrom(kingPosition));
    }

    @Test
    void isLegalToMoveFromKnightCannotMove()
    {
        var targetPosition = new Position(7, 7);
        var kingPosition = new Position(2, 0);
        var knightsPosition = new Position(3, 4);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        Assertions.assertFalse(chessState.isLegalToMoveFrom(knightsPosition));
    }

    @Test
    void isLegalToMoveFromKingCannotMove()
    {
        var targetPosition = new Position(7, 7);
        var kingPosition = new Position(2, 0);
        var knightsPosition = new Position(3, 4);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        Assertions.assertFalse(chessState.isLegalToMoveFrom(kingPosition));
    }

    @Test
    void isSolvedFalse()
    {
        var targetPosition = new Position(7, 7);
        var kingPosition = new Position(2, 0);
        var knightsPosition = new Position(3, 4);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        Assertions.assertFalse(chessState.isSolved());
    }

    @Test
    void isSolvedTrue()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        chessState.makeMove(new TwoPhaseMoveState.TwoPhaseMove<Position>(kingPosition, targetPosition));

        Assertions.assertTrue(chessState.isSolved());
    }

    @Test
    void isLegalMoveTrueKing()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        var move = new TwoPhaseMoveState.TwoPhaseMove<Position>(kingPosition, targetPosition);

        Assertions.assertTrue(chessState.isLegalMove(move));
    }

    @Test
    void isLegalMoveTrueKnight()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        var move = new TwoPhaseMoveState.TwoPhaseMove<Position>(knightsPosition, targetPosition);

        Assertions.assertTrue(chessState.isLegalMove(move));
    }

    @Test
    void isLegalMoveOutOfBoundsKing()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var movePosition = new Position(-1, 0);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        var move = new TwoPhaseMoveState.TwoPhaseMove<Position>(kingPosition, movePosition);

        Assertions.assertFalse(chessState.isLegalMove(move));
    }

    @Test
    void isLegalMoveOutOfBoundsKnight()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var movePosition = new Position(-1, 0);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        var move = new TwoPhaseMoveState.TwoPhaseMove<Position>(knightsPosition, movePosition);

        Assertions.assertFalse(chessState.isLegalMove(move));
    }

    @Test
    void isLegalMoveFalseKing()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var movePosition = new Position(6, 6);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        var move = new TwoPhaseMoveState.TwoPhaseMove<Position>(kingPosition, movePosition);

        Assertions.assertFalse(chessState.isLegalMove(move));
    }

    @Test
    void isLegalMoveFalseKnight()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var movePosition = new Position(6, 6);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        var move = new TwoPhaseMoveState.TwoPhaseMove<Position>(knightsPosition, movePosition);

        Assertions.assertFalse(chessState.isLegalMove(move));
    }

    @Test
    void makeMoveKingMoved()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        chessState.makeMove(new TwoPhaseMoveState.TwoPhaseMove<Position>(kingPosition, targetPosition));

        Assertions.assertEquals(chessState.getKingsPosition(), targetPosition);
    }

    @Test
    void makeMoveKnightMoved()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        chessState.makeMove(new TwoPhaseMoveState.TwoPhaseMove<Position>(knightsPosition, targetPosition));

        Assertions.assertEquals(chessState.getKnightPosition(), targetPosition);
    }

    @Test
    void makeMoveKingOutOfBoundsMove()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);
        var movePosition = new Position(-1, 0);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            chessState.makeMove(new TwoPhaseMoveState.TwoPhaseMove<Position>(kingPosition, movePosition));
        });
    }

    @Test
    void makeMoveKnightOutOfBoundsMove()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);
        var movePosition = new Position(-1, 0);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            chessState.makeMove(new TwoPhaseMoveState.TwoPhaseMove<Position>(knightsPosition, movePosition));
        });
    }

    @Test
    void makeMoveOverlapping()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            chessState.makeMove(new TwoPhaseMoveState.TwoPhaseMove<Position>(kingPosition, knightsPosition));
        });
    }

    @Test
    void makeMoveKingInvalidMove()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);
        var movePosition = new Position(6, 5);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            chessState.makeMove(new TwoPhaseMoveState.TwoPhaseMove<Position>(kingPosition, movePosition));
        });
    }

    @Test
    void makeMoveKnightInvalidMove()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);
        var movePosition = new Position(6, 5);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            chessState.makeMove(new TwoPhaseMoveState.TwoPhaseMove<Position>(knightsPosition, movePosition));
        });
    }

    @Test
    void makeMoveKingStayInPlace()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        var move = new TwoPhaseMoveState.TwoPhaseMove<Position>(kingPosition, kingPosition);

        chessState.makeMove(move);

        Assertions.assertEquals(chessState.getKingsPosition(), kingPosition);
    }

    @Test
    void makeMoveKnightStayInPlace()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        var move = new TwoPhaseMoveState.TwoPhaseMove<Position>(knightsPosition, knightsPosition);

        chessState.makeMove(move);

        Assertions.assertEquals(chessState.getKnightPosition(), knightsPosition);
    }

    @Test
    void getLegalMovesNotEmpty()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(1, 2);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        var moves = chessState.getLegalMoves();

        Assertions.assertNotNull(moves);
    }

    @Test
    void getLegalMovesEmpty()
    {
        var targetPosition = new Position(0, 0);
        var kingPosition = new Position(1, 0);
        var knightsPosition = new Position(5, 2);

        var chessState = new ChessState(targetPosition, kingPosition, knightsPosition);

        var moves = chessState.getLegalMoves();

        Assertions.assertNull(moves);
    }

    @Test
    void testCloneEquals()
    {
        var chessState = new ChessState();
        var clone = chessState.clone();

        Assertions.assertEquals(chessState, clone);
    }

    @Test
    void testCloneNotSame()
    {
        var chessState = new ChessState();
        var clone = chessState.clone();

        Assertions.assertNotSame(chessState, clone);
    }
}
