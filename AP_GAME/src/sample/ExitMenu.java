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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ExitMenu implements Initializable {
    @FXML
    private Text best_score;
    @FXML
    private Text collected;
    @FXML
    private Text score;
    @FXML
    private Group replay;
    private Stage stage;
    @FXML
    private Group inner1;
    @FXML
    private Group inner11;
    private long current_score;
    private long current_best_score;
    private long collected_score;
    private Score_board score_board;

    public void setCurrent_best_score(long current_best_score) {
        this.current_best_score = current_best_score;
    }

    public void setCollected_score(long collected_score) {
        this.collected_score = collected_score;
    }

    public long getCurrent_best_score() {
        return current_best_score;
    }

    public long getCollected_score() {
        return collected_score;
    }

    public void setCurrent_score(long current_score) {
        this.current_score = current_score;
    }

    public long getCurrent_score() {
        return current_score;
    }

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
        score_board = game.getInstance().getScore_board();
        setCurrent_score(score_board.getScore());
        score.setText(getCurrent_score()+"");
        //reading best score
        try {
            FileReader obj = new FileReader("best_score.txt");
            Scanner myReader = new Scanner(obj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                setCurrent_best_score(Long.parseLong(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //updating and writing best score
        if(getCurrent_best_score()<getCurrent_score()) {
            try {
                FileWriter myWriter = new FileWriter("best_score.txt");
                myWriter.write(Long.toString(getCurrent_score()));
                setCurrent_best_score(getCurrent_score());
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        best_score.setText(getCurrent_best_score() +"");
        //reading collected stars
        try {
            FileReader obj = new FileReader("collected_stars.txt");
            Scanner myReader = new Scanner(obj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                setCollected_score(Long.parseLong(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //updating and writing collected stars
        try {
            FileWriter myWriter = new FileWriter("collected_stars.txt");
            setCollected_score(getCurrent_score()+getCollected_score());
            myWriter.write(Long.toString(getCollected_score()));
            collected.setText(getCollected_score()+"");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        setRotate(inner1, 360);
        setRotate(inner11, -360);
        makeScaleTransition(replay);
    }
    @FXML
    private void mainMenu(MouseEvent mouseEvent) throws IOException {
        if(game.getInstance().isWasLoaded()){
            game_launcher.getDatabase().removeData(game.getInstance().getData());
        }
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

    @FXML
    private void startGame(MouseEvent mouseEvent) {
        if(game.getInstance().isWasLoaded()){
            game_launcher.getDatabase().removeData(game.getInstance().getData());
        }
        game g = new game();
        g.start_game(stage);
    }

    @FXML
    private void restartGame(MouseEvent mouseEvent) {
        game.getInstance().resume_game();
    }
}
