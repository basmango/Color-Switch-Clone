package sample;

import java.util.LinkedList;


import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Shape;

public  class Obstacle {
    private int angular_velocity = 5;
    public Group shape_group = new Group();
    private LinkedList<Shape> shapes = new LinkedList<Shape>();
//    pritvate update_rotate(int time_elapsed){
//    this.setRotate(angular_velocity*5);
//
//    }
    public void assign_group (Pane x){
        x.getChildren().add(0,shape_group);
    }
    Obstacle(){

//
        shapes.add(add_arc(true,true,Color.DODGERBLUE));
        shapes.add(add_arc(false,true,Color.PURPLE));
        shapes.add(add_arc(true,false,Color.DARKCYAN));
        shapes.add(add_arc(false,false,Color.ORANGE));
          //add_arc(this,-1,1,Color.CYAN);
       // add_arc(this,-1,-1,Color.YELLOW);
        shape_group.getChildren().addAll(shapes);
        shape_group.setTranslateX(105);
        shape_group.setTranslateY(-200);


    }
    private Shape add_arc(boolean is_up, boolean is_right,Color c) {

        //drawing circle
        int x = 0;
        int y = 0;
        int r = 150;
        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(r);
        Circle c_in = new Circle();
        c_in.setCenterX(x);
        c_in.setCenterY(y);
        c_in.setRadius(r - 25);
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
    public void move(double val){
       this.shape_group.setTranslateY(this.shape_group.getTranslateY()+val);
    }


    public boolean check_collision(Player_ball p){
       for(Shape s : this.shapes){

           return Shape.intersect(p,s).getBoundsInLocal().getWidth()!=-1 && s.getFill()!=p.getFill();



       }
        return false;
    }



}
