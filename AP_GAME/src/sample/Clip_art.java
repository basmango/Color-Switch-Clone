package sample;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Clip_art
{
    private Image image_space;
    private Image image_finger;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

    Clip_art()
    {
        positionX = 0;
        positionY = 0;
        this.setImage_space("spacebar.png");
        this.setImage_finger("finger.png");
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
    public void setImage_space(Image i)
    {
        image_space = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage_space(String filename)
    {
        Image i = new Image(filename,200,40,false,true);
        setImage_space(i);
    }
    public void setImage_finger(Image i)
    {
        image_finger = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage_finger(String filename)
    {
        Image i = new Image(filename,60,60,false,true);
        setImage_finger(i);
    }


    public void render(GraphicsContext gc)
    {

        gc.drawImage( image_space, positionX, positionY );
        gc.drawImage(image_finger,positionX+ image_space.getWidth()/2-30,positionY+50);
        //gc.drawImage( image2, positionX, positionY )
    }



}