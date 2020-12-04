package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Vertical_bars extends Obstacle {
    Group gp1 = new Group();
    Group gp2 = new Group();
    LinkedList<Rectangle> shapes= new LinkedList<Rectangle>();
    double time_period = 350;
    double accumulated;
    double amplitude = 300;
    double offset = 240;
    Vertical_bars(float difficulty){
        setDifficulty_float(difficulty);
        setTimeOfCreation();

        init();
    }

    @Override
    public void motion(double elapsedtime) {
        accumulated+=elapsedtime;
        if(accumulated > time_period)accumulated -= time_period;
        gp1.setTranslateX(amplitude*Math.sin((360/time_period)*getDifficulity_float()*accumulated));
        gp2.setTranslateX(-amplitude*Math.sin((360/time_period)*getDifficulity_float()*accumulated-180));

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
        populate(gp2);
        this.complete_group.getChildren().addAll(gp1,gp2);
    }
    private void populate(Group gp){
        int max_size = 250;
        int diff = 25;
        Rectangle s1,s2,s3,s4;
        Color[] colors  = new Color[]{Color.web("0xFF0082"),Color.web("0x8D13FA"),Color.web("0x35E2F2"),Color.web("0xF5DF0D")};
        ArrayList<Color> col= new ArrayList<>();
        for(Color c : colors)col.add(c);
        Collections.shuffle(col);
        s1 = this.add_rounded_rect(22,max_size - diff*1,col.get(0));
        s2 = this.add_rounded_rect(20,max_size - diff*2,col.get(1));
        s3 = this.add_rounded_rect(18,max_size - diff*3,col.get(2));
        s4 = this.add_rounded_rect(15,max_size - diff*4,col.get(3));
//        s2.set
        s2.setTranslateY(+diff/2);
        s3.setTranslateY(+diff);
        s4.setTranslateY(+diff*1.5);
//        s1.setTranslateY(diff*);
        gp.getChildren().addAll(s3,s4,s1,s2);
        s1.setTranslateX(offset+500);
        s2.setTranslateX(offset+700);
        s3.setTranslateX(offset+100);
        s4.setTranslateX(offset+300);
        shapes.add(s1);
        shapes.add(s2);
        shapes.add(s3);
        shapes.add(s4);
    }

    @Override
    protected void init() {
    this.rect_bound();
//    this.boundbox.setFill(Color.BEIGE);
    this.boundbox.setHeight(450);

    this.boundbox.setWidth(1200);
    boundbox.setTranslateY(-20);
    complete_group.setTranslateX(-500);
    populate_groups();
    addStar();
    star.translateX(756);
    star.translateY(100);
    render_collectibles();
    }
}
