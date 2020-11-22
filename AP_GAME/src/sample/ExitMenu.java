package sample;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
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

public class ExitMenu implements Initializable {
    @FXML
    private Group replay;
    private Stage stage;
    @FXML
    private Group inner1;
    @FXML
    private Group inner11;
    public void setStage(Stage stage){
        this.stage=stage;
    }
    private void setRotate(Group p, int angle){
        RotateTransition rt = new RotateTransition(Duration.seconds(4),p);
        rt.setFromAngle(0);
        rt.setToAngle(angle);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRotate(inner1, 360);
        setRotate(inner11, -360);
        makeScaleTransition(replay);
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

}
