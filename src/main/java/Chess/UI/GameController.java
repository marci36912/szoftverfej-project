package Chess.UI;


import Chess.GameManager.ChessState;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GameController
{
    @FXML
    public GridPane GameSpace;
    private Label[] labels;

    public void initialize()
    {
        createBoard();
    }

    private void createBoard()
    {
        int size = ChessState.BOARD_SIZE * ChessState.BOARD_SIZE;
        labels = new Label[size];



        for (int i = 0; i < ChessState.BOARD_SIZE; i++)
        {
            float s = (float)GameSpace.getPrefHeight() / ChessState.BOARD_SIZE;

            var r = new RowConstraints(s);
            r.setVgrow(Priority.ALWAYS);
            var c = new ColumnConstraints(s);
            c.setHgrow(Priority.ALWAYS);

            GameSpace.getRowConstraints().add(r);
            GameSpace.getColumnConstraints().add(c);
        }

        for(int i = 0; i < size; i++)
        {
            labels[i] = new Label(String.valueOf(i));
           // labels[i].setPadding(new Insets(10, 10, 10, 10));
          //  labels[i].setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));

            if(GameSpace != null)
            {
                GameSpace.add(labels[i], getCol(i), getRow(i));
            }
        }
    }

    private int getRow(int i)
    {
        return (int)(i / ChessState.BOARD_SIZE);
    }

    private int getCol(int i)
    {
        return (int)(i % ChessState.BOARD_SIZE);
    }

}
