package sample;

import java.awt.image.RasterOp;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;


import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public  abstract class Obstacle {
    protected float angular_velocity = 100;
    public Group complete_group = new Group();
    public Group shape_group = new Group();
    protected LinkedList<Shape> shapes = new LinkedList<Shape>();
    protected Star star;
    double time_of_creation = 0;
    boolean hasStar = false, hasswitch = false;
    private float difficulty_float = 1.0f;
    protected  ColorSwitcher cs;
    protected Rectangle boundbox;
    public void assign_group (Pane x){
        x.getChildren().add(0,shape_group);
    }

    protected void setTimeOfCreation(){
        Date date = new Date();
        time_of_creation = date.getTime();
    }
    protected void setDifficulty_float(float val){
        difficulty_float =val;
    }
    protected float getDifficulity_float(){
        return difficulty_float;
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
    protected  Rectangle add_rounded_rect(int width,int height,Color c) {
       Rectangle  shape = new Rectangle(width,height,c);
        shape.setArcHeight(width);
        shape.setArcWidth(width);

        return shape;

    }
    protected void rotate_node(Node gp, double deg){
        gp.getTransforms().add(new Rotate(gp.getRotate()+deg, 0, 0,0, Rotate.Z_AXIS));
    }
    protected  Rectangle add__rect(int width,int height,Color c) {
        Rectangle  shape = new Rectangle(width,height,c);
        return shape;
    }
    protected Circle unit_circles(int radius, Color c){
        return new Circle(radius,c);

    }
    protected Circle getStandardCircle(Color c){
        return unit_circles(30,c);
    }
    public boolean check_collectible_collision(Player_ball pb, Scene sc,Score_board score_board){
        boolean star_collision  = false;
        if(this.hasStar && this.star.check_collision(pb,sc)){
            this.star.action(pb,score_board);
            this.star.setDisabled();
            this.hasStar=false;
            star_collision = true;
        }
        if(this.hasswitch && this.cs.check_collision(pb,sc)){
            this.cs.action(pb,score_board);
            this.cs.setDisabled();
            this.hasswitch = false;
        }
        return star_collision;
    }
    protected void addStar(){
        star = new Star();
        this.hasStar =true;
        star.addto(complete_group);
//        star.render();
    }
    public abstract  void motion(double elapsedtime);

    protected void add_color_switcher(){
        cs = new ColorSwitcher();
        cs.addto(complete_group);
        cs.translateY(232);
        this.hasswitch = true;
    }
    protected void add_color_switcher(Color[] colors){
        cs = new ColorSwitcher(colors);
        cs.addto(complete_group);
        cs.translateY(300);
        this.hasswitch = true;
    }
    protected void chance_add_switcher(){
        Random rand = new Random();
        if(rand.nextInt(2)==1) {
            add_color_switcher();
        }

    }
    protected void render_collectibles(){
     if(hasStar)star.render();
     if(hasswitch)cs.render();
    }
    protected void rect_bound(){
        Rectangle sp  = new Rectangle(200,500,Color.TRANSPARENT);
        sp.setTranslateY(-250);
        complete_group.getChildren().add(sp);
        boundbox = sp;
    }
    public void check_bound_collision(Player_ball pb){
        while(Shape.intersect(pb,boundbox).getBoundsInLocal().getWidth()!=-1){
            pb.setTranslateY(pb.getTranslateY()-10);
        }

//        System.out.println("test");
        }


    public abstract boolean check_collision(Player_ball p);
    protected abstract  void init();


}
