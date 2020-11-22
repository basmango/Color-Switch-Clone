package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ExitMenu {
    private Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
    }
    @FXML
    private void mainMenu(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = (Parent)loader.load();
        MainMenu controller = (MainMenu) loader.getController();
        controller.setStage(stage);
        Scene s = new Scene(root, 512, 800);
        stage.setTitle("Main Menu");
        stage.setScene(s);
        stage.show();
    }
}
