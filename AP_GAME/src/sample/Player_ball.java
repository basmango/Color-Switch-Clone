package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player_ball extends Circle {
    private double velocity;
    Player_ball(){
        super(17, Color.web("0xFF0082",1.0));
        velocity = 0;

    }
    public void addVelocity(double v){
        velocity+=v;
    }
    public double getVelocity(){return velocity;}
    public void setVelocity(double v){
        this.velocity = v;
    }
    public void update(double ti){
        this.setLayoutY(this.getLayoutY()+velocity*ti);
    }
}
