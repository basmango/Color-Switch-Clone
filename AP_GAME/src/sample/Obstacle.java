package sample;

import java.util.LinkedList;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public  abstract class Obstacle {
    private int angular_velocity = 5;
    public Group complete_group = new Group();
    public Group shape_group = new Group();
    protected LinkedList<Shape> shapes = new LinkedList<Shape>();
    protected Star star;
    public void assign_group (Pane x){
        x.getChildren().add(0,shape_group);
    }


    protected  Shape add_arc(int out,int in,boolean is_up, boolean is_right,Color c) {

        //drawing circle
        int x = 0;
        int y = 0;
        int r = out;
        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(r);
        Circle c_in = new Circle();
        c_in.setCenterX(x);
        c_in.setCenterY(y);
        c_in.setRadius(in);
        //TOP RIGHT ARC
        Shape shape = Shape.subtract(circle, c_in);
        Rectangle rec = new Rectangle(x, y, r, r);
        Rectangle rec2 = new Rectangle(x - r, y, r, r);
        Rectangle rec3 = new Rectangle(x - r, y - r, r, r);
        Rectangle rec4 = new Rectangle(x, y - r, r, r);

        if (!is_right || is_up) shape = shape.subtract(shape, rec);
        if (is_right || is_up) shape = shape.subtract(shape, rec2);
        if (is_right || !is_up) shape = shape.subtract(shape, rec3);
        if (!is_right || !is_up) shape = shape.subtract(shape, rec4);
        shape.setFill(c);


        return shape;
        //TOP LEFT ARC


    }
    public void check_star_collision(Player_ball pb, Scene sc,Score_board score_board){
        if(this.star.check_collision(pb,sc)){
            this.star.action(pb,score_board);
            this.star.setDisabled();
                }
    }
    public abstract  void motion(double elapsedtime);


    public abstract boolean check_collision(Player_ball p);



}
