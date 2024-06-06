package Chess.UI;


import Chess.GameManager.ChessState;
import Chess.GameManager.Position;
import Chess.Saves.DataHolder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import puzzle.TwoPhaseMoveState;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class GameController
{
    @FXML
    public GridPane GameSpace;
    @FXML
    public Label Moves;
    @FXML
    public Label Infos;
    @FXML
    public Button saveButton;
    private Label[] labels;
    private ChessState state;
    private int size;
    private Position selectedStartingPosition;
    private int moves;
    private ImageView knightImage;
    private ImageView kingImage;

    private long timer;

    @FXML
    public void SaveGame()
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(GameSpace.getScene().getWindow());
        if(selectedDirectory == null)
        {
            return;
        }

        File savePath = new File(String.valueOf(Path.of(selectedDirectory.getAbsolutePath(), "save.json")));

        DataHolder dataHolder = new DataHolder();

        dataHolder.AddGame(MenuController.UserName, moves, timer);
        try
        {
            dataHolder.WriteSaves(savePath);
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba tortent a file mentese kozben.");
            alert.showAndWait();
        }
    }

    @FXML
    public void resetGame(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void backToMainMenu(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
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
        timer = System.currentTimeMillis();

        saveButton.setVisible(false);
    }

    private void loadImages(float size)
    {
        Image knight =  new Image(getClass().getResourceAsStream("knight.png"));
        knightImage = new ImageView(knight);
        knightImage.setFitHeight(size);
        knightImage.setPreserveRatio(true);
        Image king =  new Image(getClass().getResourceAsStream("king.png"));
        kingImage = new ImageView(king);
        kingImage.setFitHeight(size);
        kingImage.setPreserveRatio(true);
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

        loadImages(gameSize);
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

            labels[i].setGraphic(null);
        }

        var knight = state.getKnightPosition();
        var king = state.getKingsPosition();
        var target = state.getTargetsPosition();
        labels[getIndex(knight.row(), knight.col())].setGraphic(knightImage);
        labels[getIndex(king.row(), king.col())].setGraphic(kingImage);
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

        if(knightIndex == n && state.isLegalToMoveFrom(state.getKnightPosition()))
        {
            selectedStartingPosition = knight;
            colorLegalMoves();
        }
        else if (kingIndex == n && state.isLegalToMoveFrom(state.getKingsPosition()))
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

            timer = (System.currentTimeMillis() - timer) / 1000;

            saveButton.setVisible(true);
        }
        else if(state.getLegalMoves().size() == 0)
        {
            for(var label : labels)
            {
                label.setDisable(true);
            }

            Infos.setText("Nincs megjelenitheto ervenyes lepes. Vesztettel!");
            Infos.setVisible(true);
        }
    }
}
