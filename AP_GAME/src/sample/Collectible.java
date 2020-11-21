package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Collectible {
    protected Image image_primary;
    private boolean disabled = false;
    private   double width;
    private   double height;
    private Canvas canvas;
    protected GraphicsContext gc;

    public void init_canvas(){
        this.canvas = new Canvas(50,50);
        gc = canvas.getGraphicsContext2D();
        canvas.setLayoutX(-25 );
        canvas.setLayoutY(-30);
    }
    public void setDisabled(){
        disabled = true;
        this.remove();
    }
    private void remove(){
        gc.clearRect(0, 0, 100, 200);
    }
    public boolean getDisabled(){
        return disabled;
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
    public abstract void action (Player_ball pb,Score_board sc);
    public void render(){

        gc.drawImage( image_primary,0,0);

    }
    public boolean check_collision(Player_ball pb, Scene scene){
        return (canvas.localToScene(canvas.getBoundsInLocal()).getMaxY()>pb.localToScene(pb.getBoundsInLocal()).getMinY());
    }



}
