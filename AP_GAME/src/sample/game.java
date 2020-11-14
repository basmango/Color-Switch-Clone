package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import java.util.ArrayList;
import java.util.Iterator;

public class game {
    game(){

    }
    public void start_game(Stage theStage){
        theStage.setTitle( "Color Switch" );
        Group gp = new Group();
        VBox ObstaclePanel = new VBox();

        ObstaclePanel.getChildren().add(new Obstacle());
        ObstaclePanel.getChildren().add(new Obstacle());

        Player_ball pb = new Player_ball();
        gp.getChildren().addAll(ObstaclePanel,pb);

        pb.setLayoutX(256);
        pb.setLayoutY(600);
        ObstaclePanel.setLayoutX(70);
        for (Node nm: ObstaclePanel.getChildren()){
         nm.setLayoutX(256);
        }
        Scene theScene =new Scene(gp,512,800);
        ArrayList<String> input = new ArrayList<String>();
        theStage.setScene(theScene);
        //theScene.setOnKeyPressed(
//            new EventHandler<KeyEvent>()
//            {
//                public void handle(KeyEvent e)
//                {
//                    String code = e.getCode().toString();
//                    if ( !input.contains(code) )
//                        input.add( code );
//                }
//            });
        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
//                    input.remove( code );
                        input.add( code );
                    }
                });







        int score[] = new int[1];

        new AnimationTimer()
        {long lastNanoTime =System.nanoTime();
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;

                // game logic
                // acceleration below

                pb.addVelocity(800*elapsedTime);
                if (input.contains("SPACE")){

                    pb.setVelocity(-500);
                }


                input.clear();
                pb.update(elapsedTime);


            }
        }.start();

        theStage.show();
    }
}
