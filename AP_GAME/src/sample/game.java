package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public class game {
    game(){

    }
    public void start_game(Stage theStage){
        theStage.setTitle( "Collect the Money Bags!" );
        StackPane stackPane = new StackPane();
        stackPane.getChildren().clear();

        Canvas canvas = new Canvas( 512, 512 );
        stackPane.getChildren().add( canvas );
        Scene theScene =new Scene(stackPane,512,512);
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

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        Sprite briefcase = new Sprite();
        briefcase.setImage("briefcase.png");
        briefcase.setPosition(200, 200);
        Point2D FORCE_GRAVITY = new Point2D(0,3);
        ArrayList<Sprite> moneybagList = new ArrayList<Sprite>();

        for (int i = 0; i < 15; i++)
        {
            Sprite moneybag = new Sprite();
            moneybag.setImage("moneybag.png");
            double px = 350 * Math.random() + 50;
            double py = 350 * Math.random() + 50;
            moneybag.setPosition(px,py);
            moneybagList.add( moneybag );
        }



        int score[] = new int[1];

        new AnimationTimer()
        {long lastNanoTime =System.nanoTime();
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;

                // game logic

                briefcase.addVelocity(0,20);


                if (input.contains("SPACE")){
                    briefcase.setVelocity(0,-500);

                }


                briefcase.update(elapsedTime);
                input.clear();

                // collision detection

                Iterator<Sprite> moneybagIter = moneybagList.iterator();
                while ( moneybagIter.hasNext() )
                {
                    Sprite moneybag = moneybagIter.next();
                    if ( briefcase.intersects(moneybag) )
                    {
                        moneybagIter.remove();
                        score[0]++;
                    }
                }

                // render

                gc.clearRect(0, 0, 512,512);
                briefcase.render( gc );

                for (Sprite moneybag : moneybagList )
                    moneybag.render( gc );

                String pointsText = "Cash: $" + (100 * score[0]);
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );
            }
        }.start();

        theStage.show();
    }
}
