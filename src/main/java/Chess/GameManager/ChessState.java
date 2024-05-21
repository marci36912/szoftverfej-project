package Chess.GameManager;

import puzzle.State;

import java.util.Set;

public class ChessState implements State<ChessPieceMove>
{

    @Override
    public boolean isSolved()
    {
        return false;
    }

    @Override
    public boolean isLegalMove(ChessPieceMove chessPieceMove)
    {
        return false;
    }

    @Override
    public void makeMove(ChessPieceMove chessPieceMove)
    {

    }

    @Override
    public Set<ChessPieceMove> getLegalMoves()
    {
        return Set.of();
    }

    @Override
    public State<ChessPieceMove> clone()
    {
        return null;
    }
}
