package Chess.UI;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController
{
    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void switchToLeaderboard(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Leaderboard.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void switchToGame(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Game.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void loadSaves(ActionEvent event) throws IOException
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showSaveDialog(new Stage());
    }

    @FXML
    public void quitGame(ActionEvent event) {
        Platform.exit();
    }


}
