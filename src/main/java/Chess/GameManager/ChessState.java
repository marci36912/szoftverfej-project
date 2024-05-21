package Chess.GameManager;

import puzzle.State;

import java.util.Set;

public class ChessState implements State<ChessBoard>
{

    @Override
    public boolean isSolved()
    {
        return false;
    }

    @Override
    public boolean isLegalMove(ChessBoard chessBoard)
    {
        return false;
    }

    @Override
    public void makeMove(ChessBoard chessBoard)
    {

    }

    @Override
    public Set<ChessBoard> getLegalMoves()
    {
        return Set.of();
    }

    @Override
    public State<ChessBoard> clone()
    {
        return null;
    }
}
