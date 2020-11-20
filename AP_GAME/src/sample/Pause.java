package sample;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Pause
{
    private Image image_pause;

    private double positionX;
    private double positionY;
    private double width;
    private double height;
    private Canvas canvas;
    private GraphicsContext gc;
    Pause(Canvas v)
    {
        this.canvas = v;
        gc = canvas.getGraphicsContext2D();
        positionX = 0;
        positionY = 0;
        this.setImage_pause("pause.png");

    }
    public void setPositionX(double val){
        positionX = val;

    }
    public void setPositionY(double val){
        positionY = val;

    }
    public double getWidth(){
        return width;

    }
    public double getHeight(){
        return height;

    }
    public void setImage_pause(Image i)
    {
        image_pause = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage_pause(String filename)
    {
        Image i = new Image(filename,50,50,false,true);
        setImage_pause(i);
    }

    public void render()
    {

        gc.drawImage( image_pause, 0, 0 );
        //gc.drawImage( image2, positionX, positionY )
    }



}