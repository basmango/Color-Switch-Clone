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
    private static int id = 5;
    public Group shape_group = new Group();
    public Group shape_group2 = new Group();
    private LinkedList<Shape> shapes = new LinkedList<Shape>();
    private LinkedList<Shape> shapes2 = new LinkedList<Shape>();
    public void assign_group (Pane x){
        x.getChildren().add(0,shape_group);
    }
    small2circs(float difficulty){
        setTimeOfCreation();
        setDifficulty_float(difficulty);
        angular_velocity = getDifficulity_float() * angular_velocity;
        init();
    }
    protected  void init(){
        this.rect_bound();
        boundbox.setTranslateY(-220);
        shapes.add(add_arc(107,107-25,true,true,Color.web("0xFF0082")));
        shapes.add(add_arc(107,107-25,false,true,Color.web("0x8D13FA")));
        shapes.add(add_arc(107,107-25,true,false,Color.web("0x35E2F2")));
        shapes.add(add_arc(107,107-25,false,false,Color.web("0xF5DF0D")));

        shapes2.add(add_arc(107,107-25,true,false,Color.web("0xFF0082")));
        shapes2.add(add_arc(107,107-25,false,false,Color.web("0x8D13FA")));
        shapes2.add(add_arc(107,107-25,true,true,Color.web("0x35E2F2")));
        shapes2.add(add_arc(107,107-25,false,true,Color.web("0xF5DF0D")));

        //add_arc(this,-1,1,Color.CYAN);
        // add_arc(this,-1,-1,Color.YELLOW)
        // ;
        shape_group2.getChildren().addAll(shapes2);
        shape_group.getChildren().addAll(shapes);
//        shape_group2.setTranslateX(-300);
        shape_group2.setTranslateX(215);
//        complete_group.getChildren().clear();
        shapes.addAll(shapes2);
        complete_group.getChildren().addAll(shape_group,shape_group2);
        complete_group.setTranslateX(45);
//        complete_group.setTranslateY(-200);
        addStar();
        star.translateY(-170);
        this.star.translateX(105);
        chance_add_switcher();
        if(hasswitch) {
            cs.translateY(10);
            cs.translateX(105);
        }
        this.render_collectibles();

    }

    public void motion(double elapsedtime){
        shape_group.getTransforms().add(new Rotate(shape_group.getRotate()-angular_velocity*elapsedtime, 0, 0,0, Rotate.Z_AXIS));
        shape_group2.getTransforms().add(new Rotate(shape_group.getRotate()+angular_velocity*elapsedtime, 0, 0,0, Rotate.Z_AXIS));
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
