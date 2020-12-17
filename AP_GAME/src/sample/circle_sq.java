package sample;


import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class circle_sq extends Obstacle {
    @Override
    public int getId() {
        return -1;
    }

    Group gp1 = new Group();
    double accumulated_time = 0;
    LinkedList<Circle> shapes= new LinkedList<Circle>();

    private double accumulated;
    private double side_length = 250;
    private double offset = 240;
    private int circle_radius = 20;
    private int seperation = 0;

    private double velocity = 250;
    private double time_period = (4f*side_length)/velocity;
    circle_sq(float difficulty){
        setTimeOfCreation();
        setDifficulty_float(difficulty);
        velocity = velocity*getDifficulity_float();
        time_period = (4*side_length)/velocity;
        init();
    }

    @Override
    public void motion(double elapsedtime) {
        accumulated_time += elapsedtime;
        accumulated_time = accumulated_time%time_period;
        double offset = 0;
     for(Circle c : shapes) {
         apply_periodic_function(c, accumulated_time + offset);
     offset +=(circle_radius*2+10)/velocity  ;
     }
    }
    private void apply_periodic_function(Circle c, double time){
        time = time% time_period;
        double seg = time%(time_period/4);
        if(time < time_period/4){
            c.setTranslateY(0);
            c.setTranslateX(velocity*seg);
//            System.out.println("First");
        }
        else if(time >= time_period/4 && time <time_period/2){
            c.setTranslateX((time_period/4)*velocity);
            c.setTranslateY(velocity * seg);
//            System.out.println("Second");
        }
        else if(time >= 3*(time_period)/4){
            c.setTranslateY((time_period/4)*velocity  - velocity* seg);
            c.setTranslateX(0);
//            System.out.println("Last");
        }
        else if(time >= time_period/2 ){
            c.setTranslateX((time_period/4)*velocity - velocity * seg);
            c.setTranslateY((time_period/4)*velocity);
            //            System.out.println("Second Last");
        }

    }
    private void apply_motion_rule(Circle c,double elapsedtime){
        if(c.getTranslateX() <= 0 && c.getTranslateY()>0){
            c.setTranslateY(c.getTranslateY() -300 * elapsedtime);
        }
       if(c.getTranslateX()<= side_length && c.getTranslateY()<=0){
           c.setTranslateX(c.getTranslateX()+300*elapsedtime);
       }
       if(c.getTranslateX()>=side_length && c.getTranslateY()<side_length){
           c.setTranslateY(c.getTranslateY()+300*elapsedtime);
       }
       if(c.getTranslateX()> 0 && c.getTranslateY() >=side_length){
           c.setTranslateX(c.getTranslateX()-300*elapsedtime);
       }

    }
    @Override
    public boolean check_collision(Player_ball p) {
        for(Shape s : this.shapes){
            if(Shape.intersect(p,s).getBoundsInLocal().getWidth()!=-1 && !s.getFill().equals(p.getFill()))
                return true;
        }
        return false;
    }
    private void populate_groups() {
        populate(gp1);
        this.complete_group.getChildren().addAll(gp1);
    }
    private void populate(Group gp){
        Color[] colors  = new Color[]{Color.web("0xFF0082"),Color.web("0x8D13FA"),Color.web("0x35E2F2"),Color.web("0xF5DF0D")};
        LinkedList<Circle>[] sides = new LinkedList[4];
        for(int i = 0; i < 4;i++ )sides[i] = populate_linked(colors[i]);
//        spreadX(sides[0],0);
//        spreadX(sides[2],1);
//        spreadY(sides[1],1);
//        spreadY(sides[3],0);//light blue
//        for(Circle c : sides[2])c.setTranslateY(side_length);
//        for(Circle c : sides[3])c.setTranslateX(side_length);
//
        for(LinkedList<Circle> circs :sides)
        shapes.addAll(circs);
        gp.getChildren().addAll(shapes);
    }

    private void spreadX(LinkedList<Circle> circles,int offset){
        int acc = 2*circle_radius;
        for(int i  = 0; i < circles.size();i++){
            circles.get(i).setTranslateX(i*(2*circle_radius+10) +offset*(2*circle_radius));
        }

    }
    private void spreadY(LinkedList<Circle> circles,int offset){
        int acc = 2*circle_radius;
        for(int i  = 0; i < circles.size();i++){
            circles.get(i).setTranslateY(i*(2*circle_radius+10) +offset*(2*circle_radius));
        }

    }
    private LinkedList<Circle>  populate_linked(Color c){
        int num = (int)side_length/(circle_radius*2)  - 1;
        LinkedList<Circle> return_val = new LinkedList<Circle>();
        Circle circ;
        for(int i =0; i < num ; i++){
            circ= this.unit_circles(circle_radius,c);
            return_val.add(circ);
        }
        return return_val;
    }
    @Override
    protected void init() {
        this.rect_bound();
     boundbox.setTranslateY(-side_length/4);
    boundbox.setTranslateX(-20);
//    this.boundbox.setFill(Color.BEIGE);
     this.boundbox.setHeight(2*side_length);

        this.boundbox.setWidth(side_length + side_length/2);
//        boundbox.setTranslateY(50);

        complete_group.setTranslateX(110);
        populate_groups();
        addStar();
        star.translateX(side_length/2);
        star.translateY(side_length/2);
        render_collectibles();
    }
}
