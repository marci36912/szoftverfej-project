package Chess.UI;


import Chess.GameManager.ChessState;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameController
{
    @FXML
    public GridPane GameSpace;
    private Label[] labels;
    private ChessState state;
    private int size;

    public void initialize()
    {
        createBoard();
        state = new ChessState();
        colorChessBoard();
    }

    private void createBoard()
    {
        size = ChessState.BOARD_SIZE * ChessState.BOARD_SIZE;
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
            GameSpace.add(labels[i], getCol(i), getRow(i));
        }
    }

    private void colorChessBoard()
    {
        for (int i = 0; i < size; i++)
        {
            if((getRow(i) % 2 != 0 && getCol(i) % 2 == 0) || (getRow(i) % 2 == 0 && getCol(i) % 2 != 0))
            {
                labels[i].setBackground(new Background(new BackgroundFill(Color.OLIVEDRAB, null, null)));
            }
            else
            {
                labels[i].setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));
            }
        }

        var knight = state.getKnightPosition();
        var king = state.getKingsPosition();
        var target = state.getTargetsPosition();
        labels[getIndex(knight.row(), knight.col())].setBackground(new Background(new BackgroundFill(Color.BROWN, null, null)));
        labels[getIndex(king.row(), king.col())].setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
        labels[getIndex(target.row(), target.col())].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
    }

    private int getRow(int i)
    {
        return (int)(i / ChessState.BOARD_SIZE);
    }

    private int getCol(int i)
    {
        return (int)(i % ChessState.BOARD_SIZE);
    }

    private int getIndex(int row, int col)
    {
        return (row * state.BOARD_SIZE) + col;
    }

    public void onLabelClick(MouseEvent event, int n)
    {

    }
}
