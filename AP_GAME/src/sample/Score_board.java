package sample;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class Score_board extends Screen_art
{
    private int score = 0;
    Score_board(){
        super(100,50);

        this.translateX(20);
        this.translateY(2);
        gc.setStroke(Color.WHITE);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(" Noto Sans Math",50));


    }

    public void render(){
        gc.clearRect(0, 0, 100, 200);
        gc.fillText(score+"",0,45);
    }
    public void increment(){
        score++;
    }
}