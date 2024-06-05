package Chess.UI;


import Chess.GameManager.ChessState;
import Chess.GameManager.Position;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import puzzle.TwoPhaseMoveState;

import java.io.IOException;

public class GameController
{
    @FXML
    public GridPane GameSpace;
    @FXML
    public Label Moves;
    @FXML
    public Label Infos;
    private Label[] labels;
    private ChessState state;
    private int size;
    private Position selectedStartingPosition;
    private int moves;

    public void resetGame(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }

    public void backToMainMenu(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onLabelClick(MouseEvent event, int n)
    {
        if(selectedStartingPosition == null)
        {
            setSelectedStartingPosition(n);
        }
        else
        {
            try
            {
                makeMove(n);
            }
            catch(IllegalArgumentException e)
            {
                Infos.setText("Ervenytelen lepes!");
                Infos.setVisible(true);
            }
            catch(Exception e)
            {
                //error
            }
            finally
            {
                selectedStartingPosition = null;
                colorChessBoard();
                checkWinCondition();
            }
        }
    }

    public void initialize()
    {
        createBoard();
        state = new ChessState();
        colorChessBoard();
        moves = 0;
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

    private void makeMove(int n) throws IllegalArgumentException
    {
        var selectedMovePosition = new Position(getRow(n), getCol(n));
        state.makeMove(new TwoPhaseMoveState.TwoPhaseMove<Position>(selectedStartingPosition, selectedMovePosition));
        moves++;
        Moves.setText(String.valueOf(moves));
    }

    private void setSelectedStartingPosition(int n)
    {
        var knight = state.getKnightPosition();
        var king = state.getKingsPosition();

        int knightIndex = getIndex(knight.row(), knight.col());
        int kingIndex = getIndex(king.row(), king.col());

        if(knightIndex == n)
        {
            selectedStartingPosition = knight;
            colorLegalMoves();
        }
        else if (kingIndex == n)
        {
            selectedStartingPosition = king;
            colorLegalMoves();
        }
        else
        {
            selectedStartingPosition = null;
        }

        Infos.setVisible(false);
    }

    private void colorLegalMoves()
    {
        var moves = state.getLegalMoves();

        for (var move : moves)
        {
            int i = getIndex(move.to().row(), move.to().col());

            labels[i].setBackground(new Background(new BackgroundFill(Color.TOMATO, null, null)));
        }
    }

    private void checkWinCondition()
    {
        if(state.isSolved())
        {
            for(var label : labels)
            {
                label.setDisable(true);
            }

            Infos.setText("Nyertel!");
            Infos.setVisible(true);
        }
    }
}
