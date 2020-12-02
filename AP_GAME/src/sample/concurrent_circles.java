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
    private LinkedList<Shape> shapes_sec = new LinkedList<Shape>();
    private int r1,r2,r3,ir1,ir2,ir3;
    private int thickness = 20;
    public void assign_group (Pane x){
        x.getChildren().add(0,shape_group);
    }
  concurrent_circles(){

//
    init();

    }
    protected void init(){
        this.rect_bound();
        boundbox.setHeight(600);
        r1 = 176;
        ir1=r1-thickness;
        r2 = ir1-5;
        ir2 = r2-thickness;
        r3 = ir2 - 5;
        ir3 = r3-thickness;
        shapes.add(add_arc(r3,ir3,true,false,Color.web("0xFF0082")));
        shapes.add(add_arc(r3,ir3,false,false,Color.web("0x8D13FA")));
        shapes.add(add_arc(r3,ir3,true,true,Color.web("0x35E2F2")));
        shapes.add(add_arc(r3,ir3,false,true,Color.web("0xF5DF0D")));
        shapes_sec.add(add_arc(r2,ir2,true,false,Color.web("0xFF0082")));
        shapes_sec.add(add_arc(r2,ir2,false,false,Color.web("0x8D13FA")));
        shapes_sec.add(add_arc(r2,ir2,true,true,Color.web("0x35E2F2")));
        shapes_sec.add(add_arc(r2,ir2,false,true,Color.web("0xF5DF0D")));

        shapes.add(add_arc(r1,ir1,true,false,Color.web("0xFF0082")));//no
        shapes.add(add_arc(r1,ir1,false,false,Color.web("0x8D13FA")));//blue
        shapes.add(add_arc(r1,ir1,true,true,Color.web("0x35E2F2")));//light_blue
        shapes.add(add_arc(r1,ir1,false,true,Color.web("0xF5DF0D")));//pink

        shape_group.getTransforms().add(new Rotate(shape_group.getRotate()+22.5, 0, 0,0, Rotate.Z_AXIS));
        shape_group2.getTransforms().add(new Rotate(shape_group2.getRotate()+22.5, 0, 0,0, Rotate.Z_AXIS));
//        for (Shape s : shapes_sec)s.setRotate(22.5);
        shape_group.getChildren().addAll(shapes);
        shape_group2.getChildren().addAll(shapes_sec);
//        shape_group1.setTranslateX(-300);
        shapes.addAll(shapes_sec);
//        complete_group.getChildren().clear();
        Color color_vals[] = new Color[]{Color.web("0xF5DF0D"),Color.web("0xFF0082")};
        addStar();
        add_color_switcher(color_vals);
        cs.translateY(10);
        shape_group2.setRotate(shape_group2.getRotate()+22.5);
        complete_group.getChildren().addAll(shape_group,shape_group2);
        complete_group.setTranslateX(80);
//        complete_group.setTranslateY(-200);
//        complete_group.setRotate(22.5);

        this.render_collectibles();

//        complete_group.setTranslateY(-201);
    }

    public void motion(double elapsedtime){
        shape_group.getTransforms().add(new Rotate(shape_group.getRotate()+100*elapsedtime, 0, 0,0, Rotate.Z_AXIS));

        shape_group2.getTransforms().add(new Rotate(shape_group.getRotate()-100*elapsedtime, 0, 0,0, Rotate.Z_AXIS));
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
