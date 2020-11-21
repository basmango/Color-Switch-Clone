package sample;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Pause extends Screen_art
{

    Pause()
    {
        super(50,50);
        this.setImage_primary("pause.png",50,50);
        this.setPositionX(457);
        this.setPositionY(5);

    }


}