package sample;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResumeMenu implements Initializable {
    @FXML
    private Text stars1;
    @FXML
    private Text stars2;
    @FXML
    private Text stars3;
    @FXML
    private Text stars4;
    @FXML
    private Text date1;
    @FXML
    private Text time1;
    @FXML
    private Text date2;
    @FXML
    private Text time2;
    @FXML
    private Text date3;
    @FXML
    private Text time3;
    @FXML
    private Text date4;
    @FXML
    private Text time4;
    @FXML
    private Group game1;
    @FXML
    private Group game2;
    @FXML
    private Group game3;
    @FXML
    private Group game4;
    private Stage stage;
    @FXML
    private Group inner1;
    @FXML
    private Group inner11;
    private ArrayList<DataTable> databaseFiles;
    public void setStage(Stage stage){
        this.stage=stage;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRotate(inner1, 360);
        setRotate(inner11, -360);
        try {
            game_launcher.deserialize();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseFiles = game_launcher.getDatabase().getDatabaseFiles();
        showNone();
        switch(databaseFiles.size()){
            case 0:
                break;
            case 1:
                showOne();
                break;
            case 2:
                showTwo();
                break;
            case 3:
                showThree();
                break;
            case 4:
                showFour();
                break;
            default:
                System.out.println("check array list some error!!");
        }
    }
    private void showNone(){
        game1.setVisible(false);
        game1.setDisable(true);
        game2.setVisible(false);
        game2.setDisable(true);
        game3.setVisible(false);
        game3.setDisable(true);
        game4.setVisible(false);
        game4.setDisable(true);
    }
    private void showOne(){
        game1.setVisible(true);
        game1.setDisable(false);
        time1.setText(databaseFiles.get(0).getTime());
        date1.setText(databaseFiles.get(0).getDate());
        stars1.setText(Integer.toString(databaseFiles.get(0).getStars_collected()));
    }
    private void showTwo(){
        showOne();
        game2.setVisible(true);
        game2.setDisable(false);
        time2.setText(databaseFiles.get(1).getTime());
        date2.setText(databaseFiles.get(1).getDate());
        stars2.setText(Integer.toString(databaseFiles.get(1).getStars_collected()));
    }
    private void showThree(){
        showTwo();
        game3.setVisible(true);
        game3.setDisable(false);
        time3.setText(databaseFiles.get(2).getTime());
        date3.setText(databaseFiles.get(2).getDate());
        stars3.setText(Integer.toString(databaseFiles.get(2).getStars_collected()));
    }
    private void showFour(){
        showThree();
        game4.setVisible(true);
        game4.setDisable(false);
        time4.setText(databaseFiles.get(3).getTime());
        date4.setText(databaseFiles.get(3).getDate());
        stars4.setText(Integer.toString(databaseFiles.get(3).getStars_collected()));
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
    @FXML
    private void loadGame1(MouseEvent mouseEvent){
        game g = new game();
        g.load_a_game(stage,databaseFiles.get(0));
    }
    @FXML
    private void loadGame2(MouseEvent mouseEvent){
        game g = new game();
        g.load_a_game(stage,databaseFiles.get(1));
    }
    @FXML
    private void loadGame3(MouseEvent mouseEvent){
        game g = new game();
        g.load_a_game(stage,databaseFiles.get(2));
    }
    @FXML
    private void loadGame4(MouseEvent mouseEvent){
        game g = new game();
        g.load_a_game(stage,databaseFiles.get(3));
    }
    @FXML
    private void clearGames(MouseEvent mouseEvent) throws IOException {
        game_launcher.newDatabase();
        showNone(); 
    }
}
