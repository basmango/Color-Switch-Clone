package sample;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public abstract class Screen_art
{
    protected   Image image_primary;

    private   double width;
    private   double height;
    private Canvas canvas;
    protected GraphicsContext gc;
    Screen_art(int x, int y)
    {
        this.init_canvas(x,y);
    }
    private void init_canvas(int x, int y){
        this.canvas = new Canvas(x,y);
        gc = canvas.getGraphicsContext2D();
    }

    public void addto(Group x){
        x.getChildren().add(canvas);
    }
    public void translateX(double val){
        this.canvas.setTranslateX(this.canvas.getTranslateX()+val);

    }
    public void translateY(double val){
        this.canvas.setTranslateY(this.canvas.getTranslateY()+val);


    }
    public double getPositionX(){
        return canvas.getLayoutX() ;

    }
    public double getPositionY(){
        return canvas.getLayoutY() ;

    }

    public void  setPositionX(double val){
        this.canvas.setLayoutX(val);

    }
    public void  setPositionY(double val){
        this.canvas.setLayoutY(val);

    }
    public double getWidth(){
        return canvas.getWidth();

    }
    public double getHeight(){
        return canvas.getHeight();

    }
    private void setImage(Image i)
    {
        this.image_primary = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    protected void setImage_primary(String filename,int width,int height)
    {
        Image i = new Image(filename,width,height,false,true);
        setImage(i);
    }

    public void render(){

        gc.drawImage( image_primary,0,0);

    }



}