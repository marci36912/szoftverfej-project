package Chess.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class Game extends Application
{

    @Override
    public void start(Stage stage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        stage.setScene(new Scene(root));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.titleProperty().setValue("Chess game");
        Image king =  new Image(getClass().getResourceAsStream("king.png"));
        stage.getIcons().add(king);
        stage.show();

        Logger.info("Game started with UI");
    }
}
