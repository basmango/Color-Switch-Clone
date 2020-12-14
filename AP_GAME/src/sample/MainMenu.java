package sample;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    @FXML
    private Text noSave;
    @FXML
    private Group arrow;
    @FXML
    private Group resume;
    @FXML
    private Group inner;
    @FXML
    private Group middle;
    @FXML
    private Group outer;
    @FXML
    private Group inner1;
    @FXML
    private Group inner11;
    private Stage stage;

    public void setStage(Stage stage){
        this.stage=stage;
    }
    private void checkForSavedGames() throws NoSavedGameException{
        if(game_launcher.getDatabase().getDatabaseFiles().size()<1){
            throw new NoSavedGameException();
        }
    }
    @FXML
    private void resumeMenu(MouseEvent mouseEvent) throws IOException {
        try {
            checkForSavedGames();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResumeMenu.fxml"));
            Parent root = (Parent)loader.load();
            ResumeMenu controller = (ResumeMenu) loader.getController();
            controller.setStage(stage);
            Scene s = new Scene(root, 512, 800);
            stage.setTitle("Resume Games");
            stage.setScene(s);
            stage.show();
        } catch (NoSavedGameException e) {
            noSave.setText("NO SAVED GAME");
            System.out.println("No saved game in database");
        }

    }
    @FXML
    private void playGame(MouseEvent mouseEvent) {
        game g = new game();
        g.start_game(stage);
    }
    @FXML
    private void exitGame(MouseEvent mouseEvent) {
        System.exit(0);
    }
    private void setRotate(Group p, int angle){
        RotateTransition rt = new RotateTransition(Duration.seconds(4),p);
        rt.setFromAngle(0);
        rt.setToAngle(angle);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
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
        setRotate(inner, 360);
        setRotate(middle, -360);
        setRotate(outer, 360);
        setRotate(inner1, 360);
        setRotate(inner11, -360);
        makeScaleTransition(resume);
        noSave.setText("");
    }
}
