package Chess.UI;

import Chess.Saves.DataHolder;
import Chess.Saves.GameInfo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuController
{
    @FXML
    public TextField nameField;

    @FXML
    public TableView Plays;

    public static String UserName = "User";

    @FXML
    public void initialize()
    {
        if(nameField != null)
        {
            nameField.setText(UserName);
        }
    }

    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void switchToLeaderboard(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void switchToGame(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void loadSaves(ActionEvent event) throws IOException
    {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile == null)
        {
            return;
        }

        if(!"save.json".equals(selectedFile.getName().toString()))
        {
            return;
        }

        DataHolder dataHolder = new DataHolder();
        try
        {
            dataHolder.LoadSaves(selectedFile);
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Hiba tortent a file mentese kozben.");
            alert.showAndWait();
        }

        TableColumn<GameInfo, String> nameCol = new TableColumn<GameInfo, String>("Nev");
        nameCol.setCellValueFactory(new PropertyValueFactory<GameInfo, String>("userName"));

        TableColumn<GameInfo, String> durationCol = new TableColumn<GameInfo, String>("Idotartam");
        durationCol.setCellValueFactory(new PropertyValueFactory<GameInfo, String>("duration"));

        TableColumn<GameInfo, Integer> stepsCol = new TableColumn<GameInfo, Integer>("Lepesek");
        stepsCol.setCellValueFactory(new PropertyValueFactory<GameInfo, Integer>("steps"));

        TableColumn<GameInfo, String> dateCol = new TableColumn<GameInfo, String>("Datum");
        dateCol.setCellValueFactory(new PropertyValueFactory<GameInfo, String>("date"));

        Plays.getColumns().addAll(nameCol, durationCol, stepsCol, dateCol);
        Plays.getItems().addAll(DataHolder.GameInfos);

        Plays.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Plays.refresh();
    }

    @FXML
    public void quitGame(ActionEvent event) {
        Platform.exit();
    }

    public void ChangeName(ActionEvent event)
    {
        Pattern p = Pattern.compile("[a-zA-Z]{0,30}");
        Matcher m = p.matcher(nameField.getText());
        if(m.matches())
        {
            UserName = nameField.getText();
        }
    }
}
