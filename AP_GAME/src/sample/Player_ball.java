package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player_ball extends Circle {
    private double velocity;
    Player_ball(){
        super(25, Color.GREEN);
        velocity = 0;

    }
    public void addVelocity(double v){
        velocity+=v;
    }
    public void setVelocity(double v){
        this.velocity = v;
    }
    public void update(double ti){
        this.setLayoutY(this.getLayoutY()+velocity*ti);
    }
}
