package sample;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PauseMenu implements Initializable {
    @FXML
    private Group exception;
    @FXML
    private Group save;
    @FXML
    private Group restart;
    @FXML
    private Group pause;
    private Stage stage;
    private DataTable data;
    private DataBase files;

    public void setStage(Stage stage){
        this.stage=stage;
    }
    private void loadMainMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = (Parent)loader.load();
        MainMenu controller = (MainMenu) loader.getController();
        controller.setStage(stage);
        Scene s = new Scene(root, 512, 800);
        stage.setTitle("Main Menu");
        stage.setScene(s);
        stage.show();
    }
    @FXML
    private void mainMenu(MouseEvent mouseEvent) throws IOException {
        loadMainMenu();
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
        exception.setDisable(true);
        exception.setVisible(false);
    }
    public void resumeGame(MouseEvent mouseEvent) {
        game.getInstance().resume_game();
    }
    public void shouldRemoveFiles(){
        restart.setVisible(false);
        restart.setDisable(true);
        exception.setVisible(true);
        exception.setDisable(false);

    }
    public void saveGame(MouseEvent mouseEvent) throws IOException {
        files = game_launcher.getDatabase();
        if(files.getDatabaseFiles().size()>3){
            shouldRemoveFiles();
        }else {
            data = game.getInstance().updateData();
            files.removeData(data);
            data.saveGame();
            loadMainMenu();
        }
    }
    @FXML
    private void restartGame(MouseEvent mouseEvent) {
        if(game.getInstance().isWasLoaded()){
            game_launcher.getDatabase().removeData(game.getInstance().getData());
        }
        game g = new game();
        g.start_game(stage);
    }

    @FXML
    private void yes(MouseEvent mouseEvent) throws IOException {
        files.removeLast();
        data = game.getInstance().updateData();
        files.removeData(data);
        data.saveGame();
        loadMainMenu();
    }

    @FXML
    private void no(MouseEvent mouseEvent) {
        restart.setVisible(true);
        restart.setDisable(false);
        exception.setVisible(false);
        exception.setDisable(true);
    }
}
