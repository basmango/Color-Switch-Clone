package sample;

import java.util.Collection;
import java.util.LinkedList;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public  class small2circs extends Obstacle{
    private int angular_velocity = 5;

    public Group shape_group = new Group();
    public Group shape_group2 = new Group();
    private LinkedList<Shape> shapes = new LinkedList<Shape>();
    private LinkedList<Shape> shapes2 = new LinkedList<Shape>();
    private Star star;
    public void assign_group (Pane x){
        x.getChildren().add(0,shape_group);
    }
    small2circs(){

//
        shapes.add(add_arc(true,true,Color.web("0xFF0082")));
        shapes.add(add_arc(false,true,Color.web("0x8D13FA")));
        shapes.add(add_arc(true,false,Color.web("0x35E2F2")));
        shapes.add(add_arc(false,false,Color.web("0xF5DF0D")));

        shapes2.add(add_arc(true,false,Color.web("0xFF0082")));
        shapes2.add(add_arc(false,false,Color.web("0x8D13FA")));
        shapes2.add(add_arc(true,true,Color.web("0x35E2F2")));
        shapes2.add(add_arc(false,true,Color.web("0xF5DF0D")));

        //add_arc(this,-1,1,Color.CYAN);
        // add_arc(this,-1,-1,Color.YELLOW)
        // ;
       shape_group2.getChildren().addAll(shapes2);
        star = new Star();
        star.translateY(-120);
        star.translateX(110);
        shape_group.getChildren().addAll(shapes);
//        shape_group2.setTranslateX(-300);
        shape_group2.setTranslateX(215);
        complete_group.getChildren().clear();
        shapes.addAll(shapes2);
        complete_group.getChildren().addAll(shape_group,shape_group2);
        star.addto(complete_group);
        complete_group.setTranslateX(45);
        complete_group.setTranslateY(-200);


    }
    private Shape add_arc(boolean is_up, boolean is_right,Color c) {

        //drawing circle
        int x = 0;
        int y = 0;
        int r = 107;
        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(r);
        Circle c_in = new Circle();
        c_in.setCenterX(x);
        c_in.setCenterY(y);
        c_in.setRadius(r - 20);
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
    public void motion(double elapsedtime){
        shape_group.getTransforms().add(new Rotate(shape_group.getRotate()+50*elapsedtime, 0, 0,0, Rotate.Z_AXIS));
        shape_group2.getTransforms().add(new Rotate(shape_group.getRotate()-50*elapsedtime, 0, 0,0, Rotate.Z_AXIS));
    }
    public void move(double val){
        this.shape_group.setTranslateY(this.shape_group.getTranslateY()+val);
    }


    public boolean check_collision(Player_ball p){
        for(Shape s : this.shapes){
            if(Shape.intersect(p,s).getBoundsInLocal().getWidth()!=-1 && !s.getFill().equals(p.getFill()))
                return true;



        }
        return false;
    }



}
