package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import java.util.Random;

public class XWheel extends Obstacle{
    XWheel(){
        init();

    }
    protected void init(){
        this.rect_bound();
        boundbox.setHeight(450);
        boundbox.setWidth(500);
        boundbox.setTranslateX(-200);
        boundbox.setTranslateY(-200);
//        this.boundbox.setFill(Color.BEIGE);
        Color[] colors  = new Color[]{Color.web("0xFF0082"),Color.web("0x8D13FA"),Color.web("0xF5DF0D"),Color.web("0x35E2F2")};
        for (Color c : colors){
            shapes.add(add_rounded_rect(30,125,c));
        }
        this.rotate_node(shapes.get(0),90);
        this.rotate_node(shapes.get(1),90);
        shapes.get(0).setTranslateX(-5);
        shapes.get(1).setTranslateX(130);
        shapes.get(2).setTranslateY(-120);
        shapes.get(2).setTranslateX(-15);
        shapes.get(3).setTranslateX(-15);
        shapes.get(3).setTranslateY(25);

//        shapes.get(2).setTranslateX(130);
//        shapes.get(2).setTranslateY(100);
//        shapes.get(0).setTranslateX(125);
//        shapes.get(0).setTranslateY(-125);
//        shapes.get(1).setTranslateX(100);
//        shapes.get(1).setTranslateY(-125);
//        shapes.get(3).setTranslateX(-135);
//        shapes.get(3).setTranslateY(-122);
        shape_group.getChildren().addAll(shapes);
        this.addStar();
        chance_add_switcher();
//        cs.translateY(200);
        if(hasswitch) {
            cs.translateY(-10);
            cs.translateX(50);
        }
        complete_group.getChildren().add(shape_group);
//Random random = new Random();
//        int side_bool = random.nextInt(2);
            shape_group.setTranslateX(120);
//       else shape_group.setTranslateX(-50);

        star.translateX(50);
        //    complete_group.setTranslateY(-200);
        render_collectibles();
    }
    public void motion(double elapsedtime){
        shape_group.getTransforms().add(new Rotate(shape_group.getRotate()+100*elapsedtime, 0, 0,0, Rotate.Z_AXIS));
    }
    public boolean check_collision(Player_ball p){
        for(Shape s : this.shapes){
            if(Shape.intersect(p,s).getBoundsInLocal().getWidth()!=-1 && !s.getFill().equals(p.getFill()))
                return true;



        }
        return false;
    }
}

