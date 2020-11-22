package sample;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PauseMenu implements Initializable {
    @FXML
    private Group save;
    @FXML
    private Group pause;
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
    private static void makeScaleTransition(Group p) {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1),p);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(0.8);
        st.setToY(0.8);
        st.setCycleCount(Timeline.INDEFINITE);
        st.setAutoReverse(true);
        st.play();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeScaleTransition(pause);
    }
    public void resumeGame(MouseEvent mouseEvent) {
        game.getInstance().resume_game();
    }
}
