package Chess.UI;


import Chess.GameManager.ChessState;
import javafx.scene.control.Button;

public class GameController
{
    private ChessState chessState = new ChessState();
    private Button[] buttons;


    private void createBoard()
    {
        int size = chessState.BOARD_SIZE*chessState.BOARD_SIZE;
        buttons = new Button[size];

        for(int i = 0; i < size; i++)
        {
            buttons[i] = new Button(Integer.toString(i));

        }
    }

    private int getRow(int i)
    {
        return (int)(i / chessState.BOARD_SIZE);
    }

    private int getCol(int i)
    {
        return (int)(i % chessState.BOARD_SIZE);
    }

}
