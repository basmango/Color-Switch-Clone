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

public  class concurrent_circles extends Obstacle{
    private int angular_velocity = 5;

    public Group shape_group = new Group();
    public Group shape_group2 = new Group();
    private LinkedList<Shape> shapes = new LinkedList<Shape>();
    private Star star;
    public void assign_group (Pane x){
        x.getChildren().add(0,shape_group);
    }
  concurrent_circles(){

//
        shapes.add(add_arc(107,107-25,true,false,Color.web("0xFF0082")));
        shapes.add(add_arc(107,107-25,false,false,Color.web("0x8D13FA")));
        shapes.add(add_arc(107,107-25,true,true,Color.web("0x35E2F2")));
        shapes.add(add_arc(107,107-25,false,true,Color.web("0xF5DF0D")));

        shapes.add(add_arc(135,110,true,false,Color.web("0xFF0082")));
        shapes.add(add_arc(135,110,false,false,Color.web("0x8D13FA")));
        shapes.add(add_arc(135,110,true,true,Color.web("0x35E2F2")));
        shapes.add(add_arc(135,110,false,true,Color.web("0xF5DF0D")));

        shapes.add(add_arc(165,140,true,false,Color.web("0xFF0082")));
      shapes.add(add_arc(165,140,false,false,Color.web("0x8D13FA")));
      shapes.add(add_arc(165,140,true,true,Color.web("0x35E2F2")));
      shapes.add(add_arc(165,140,false,true,Color.web("0xF5DF0D")));
        //add_arc(this,-1,1,Color.CYAN);
        // add_arc(this,-1,-1,Color.YELLOW)
        // ;
        star = new Star();
        shape_group.getChildren().addAll(shapes);
//        shape_group2.setTranslateX(-300);

        complete_group.getChildren().clear();

        complete_group.getChildren().addAll(shape_group);
        star.addto(complete_group);
        complete_group.setTranslateX(105);
        complete_group.setTranslateY(-200);


    }

    public void check_star_collision(Player_ball pb, Scene sc,Score_board score_board){
        if(this.star.check_collision(pb,sc)){
            this.star.action(pb,score_board);
            this.star.setDisabled();
        }
    }
    public void motion(double elapsedtime){
        shape_group.getTransforms().add(new Rotate(shape_group.getRotate()+50*elapsedtime, 0, 0,0, Rotate.Z_AXIS));
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
