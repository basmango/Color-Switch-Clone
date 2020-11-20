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
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class game {
    game(){

    }
    Canvas art;
    Canvas pause_canvas;
    GraphicsContext gc;
    VBox ObstaclePanel;
    Player_ball pb;
    private double obs_vel = 0;
    public void start_game(Stage theStage){
        theStage.setTitle( "Color Switch" );
        Group gp = new Group();
        art = new Canvas(500,600);
        pause_canvas  = new Canvas(50,50);
        Pause pause_button = new Pause(pause_canvas);
        gc = art.getGraphicsContext2D();
        Clip_art spacebar = new Clip_art();
        spacebar.setPositionX(0);
        spacebar.setPositionY(0);
         ObstaclePanel = new VBox();

        ObstaclePanel.setSpacing(200);
        LinkedList<Obstacle> obs= new LinkedList<Obstacle>();
        obs.add(new Obstacle());
        obs.add(new Obstacle());

        for(Obstacle o: obs){
            o.assign_group(ObstaclePanel);
            o.shape_group.setTranslateY(-100);
        }
        //ObstaclePanel.getChildren().add(art);
         pb = new Player_ball();
        gp.getChildren().addAll(art,ObstaclePanel,pb,pause_canvas);
        art.setLayoutY(600);
        art.setLayoutX(155);
        pb.setLayoutX(256);
        pb.setLayoutY(500);
        pause_canvas.setLayoutY(5);
        pause_canvas.setLayoutX(457);
        ObstaclePanel.setLayoutX(0);
        for (Node nm: ObstaclePanel.getChildren()){
         nm.setLayoutX(256);
         nm.setTranslateY(-500);
        }

        Scene theScene =new Scene(gp,512,800,Color.web("#242020"));


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
                for(Node x: ObstaclePanel.getChildren()){
                    x.getTransforms().add(new Rotate(x.getRotate()+100*elapsedTime, 0, 0,0, Rotate.Z_AXIS));

                }
                while (pb.getBoundsInParent().getMaxY()<theScene.getHeight()/2-60){
                    apply_vel();
                }
                if(pb.getLayoutY()>=art.getLayoutY()-50){pb.setVelocity(0);
                pb.setLayoutY(art.getLayoutY()-50);
                }
                else{
                    pb.addVelocity(1200*elapsedTime);

                }


                if(input.contains("UP")){
                    Obstacle ob = new Obstacle();
                    double max = 0;
                    for(Node x: ObstaclePanel.getChildren()){
                        x.setTranslateY(x.getTranslateY()-ob.shape_group.getBoundsInLocal().getHeight()-200);
                        max = x.getTranslateY();
                    }
                    ob.assign_group(ObstaclePanel);
                    ob.shape_group.setTranslateY(max);

                }

                pb.update(elapsedTime);


                if (input.contains("SPACE")){


                        pb.setVelocity(-340);




                    double max = 0;



                }
                for(Obstacle ob : obs){
                    ob.check_collision(pb);

                }

                input.clear();
                pb.update(elapsedTime);
                spacebar.render(gc);
                pause_button.render();

            }
        }.start();

        theStage.show();
    }
    public void apply_vel(){
        for(Node x: ObstaclePanel.getChildren()){
            x.setTranslateY(x.getTranslateY()+2);

        }
        pb.setTranslateY(pb.getTranslateY()+2);
        art.setTranslateY(art.getTranslateY()+2);
    }
}
