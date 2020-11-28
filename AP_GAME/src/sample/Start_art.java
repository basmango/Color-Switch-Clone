package sample;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Start_art extends Screen_art
{
    private Image image_finger;
    Start_art() {
        super(512,200);
        this.setImage_primary("spacebar.png",200,40);
        this.setImage_finger("finger.png");
        this.setPositionX((512-200)/2);
        this.setPositionY(600);
    }

    private void setImage_finger(Image i)
    {
        image_finger = i;
      }

    private void setImage_finger(String filename) {
        Image i = new Image(filename,60,60,false,true);
        setImage_finger(i);
    }

    @Override
    public void render() {
        gc.drawImage(image_primary,0,0);
        gc.drawImage(image_finger,75,50);

    }



}