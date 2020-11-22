package sample;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Pause extends Screen_art
{
    private boolean isClicked = false;
    Pause()
    {
        super(50,50);
        this.setImage_primary("pause.png",50,50);
        this.setPositionX(457);
        this.setPositionY(5);

        this.gc.getCanvas().setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    isClicked  = true;
                    }
                });

    }
    public boolean isClicked(){
        return isClicked;
   }
   public void Clickcheckdone(){
        this.isClicked = false;
   }

}