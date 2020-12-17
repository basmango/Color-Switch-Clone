package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.w3c.dom.css.Rect;
import sample.Obstacle;
import sample.Player_ball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Horizontal_Bars extends Obstacle {
    public int getId(){
        return 0;
    }
    Group gp1 = new Group();

    LinkedList<Rectangle> shapes= new LinkedList<Rectangle>();
    private double accumulated_time;
    private  double time_period = 6;
    private double velocity = 150;
    private int rec_length = 150;
    private static int id = 0;
    Horizontal_Bars(float difficulty){
        setDifficulty_float(difficulty);
        setTimeOfCreation();
        velocity = getDifficulity_float()*velocity;
        time_period = (8*rec_length)/velocity;
        init();
    }

    @Override
    public void motion(double elapsedtime) {
    accumulated_time +=elapsedtime;
    accumulated_time = accumulated_time % time_period;
    double offset  = 0;
    for(Rectangle rec : shapes){
        apply_periodic(rec,accumulated_time + offset);
    offset +=rec_length/velocity;
    }

    }
    private void apply_periodic(Rectangle rc, double time){
        time  = time%time_period;
        rc.setTranslateX(time*velocity);
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
        int max_size = 250;
        int diff = 25;
        Rectangle s1,s2,s3,s4;
        Color[] colors  = new Color[]{Color.web("0xFF0082"),Color.web("0x8D13FA"),Color.web("0x35E2F2"),Color.web("0xF5DF0D")};
        ArrayList<Color> col= new ArrayList<>();
        for(Color c : colors)col.add(c);
        Collections.shuffle(col);
        for(int i =0; i < 2;i++)
        for(Color c: col){
            shapes.add(new Rectangle(rec_length,40,c));
        }
        gp.getChildren().addAll(shapes);
    }

    @Override
    protected void init() {
        this.rect_bound();
//    this.boundbox.setFill(Color.BEIGE);
        this.boundbox.setHeight(350);
        this.boundbox.setWidth(600);
        boundbox.setTranslateY(-150);
        complete_group.setTranslateX(-200);
        addStar();
        populate_groups();

        chance_add_switcher();
        if(hasswitch) {
            cs.translateX(450);
            cs.translateY(-70);
        }
        star.translateX(450);
        star.translateY(-100);

        render_collectibles();
    }
}
