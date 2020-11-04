package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.Parent;
// Collect the Money Bags!

import javafx.scene.control.Button;

public class game_launcher extends Application
{
    private static void main(String[] args)
    {
        launch(args);
    }


    public void start(Stage theStage)
    {
    try{
        show_main_menu(theStage);
    }
    catch(Exception e ){
        System.out.println(e.getMessage());

    }
    }

    public void show_main_menu(Stage stage) throws Exception{
//        Parent gui;
        StackPane r = new StackPane();
        Button b = new Button("start game");

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                game g = new game();
                g.start_game(stage);
            }
        };
        b.setOnAction(event);
        r.getChildren().add(b);
        r.setAlignment(Pos.CENTER);
        Scene s = new Scene(r, 300, 275);
        stage.setTitle("Game Launcher");
            stage.setScene(s);
            stage.show();




//        Group g = new Group();
//        Button b1 = new Button("start game");
//        Button b2 = new Button("exit");
//        g.getChildren().add(b1);
//        g.getChildren().add(b2);


    }
}
