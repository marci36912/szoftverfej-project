package Chess.GameManager;

import puzzle.State;

import java.util.Set;

public class ChessState implements State<ChessPieces>
{

    @Override
    public boolean isSolved()
    {
        return false;
    }

    @Override
    public boolean isLegalMove(ChessPieces chessPieces)
    {
        return false;
    }

    @Override
    public void makeMove(ChessPieces chessPieces)
    {

    }

    @Override
    public Set<ChessPieces> getLegalMoves()
    {
        return Set.of();
    }

    @Override
    public State<ChessPieces> clone()
    {
        return null;
    }
}
