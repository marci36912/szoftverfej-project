package Chess.UI;


import Chess.GameManager.ChessState;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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


        float gameSize = (float)GameSpace.getPrefHeight() / ChessState.BOARD_SIZE;
        for (int i = 0; i < ChessState.BOARD_SIZE; i++)
        {
            var r = new RowConstraints(gameSize);
            r.setVgrow(Priority.ALWAYS);
            var c = new ColumnConstraints(gameSize);
            c.setHgrow(Priority.ALWAYS);

            GameSpace.getRowConstraints().add(r);
            GameSpace.getColumnConstraints().add(c);
        }

        for(int i = 0; i < size; i++)
        {
            labels[i] = new Label();
            labels[i].setPrefSize(gameSize, gameSize);
            int finalI = i;
            labels[i].setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    onLabelClick(event, finalI);
                }
            });
            if((getRow(i) % 2 != 0 && getCol(i) % 2 == 0) || (getRow(i) % 2 == 0 && getCol(i) % 2 != 0))
            {
                labels[i].setBackground(new Background(new BackgroundFill(Color.OLIVEDRAB, null, null)));
            }
            else
            {
                labels[i].setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));
            }

            GameSpace.add(labels[i], getCol(i), getRow(i));
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

    public void onLabelClick(MouseEvent event, int n)
    {

    }
}
