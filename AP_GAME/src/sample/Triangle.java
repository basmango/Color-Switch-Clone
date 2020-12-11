package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public class Triangle extends Obstacle{
    Triangle(float difficulty){
        setDifficulty_float(difficulty);
        setTimeOfCreation();
        angular_velocity = getDifficulity_float() *angular_velocity;
        init();

    }
    protected void init(){
        this.rect_bound();
        boundbox.setHeight(650);
        boundbox.setWidth(500);
        boundbox.setTranslateX(-200);
        boundbox.setTranslateY(-250);
//        this.boundbox.setFill(Color.BEIGE);
         Color[] colors  = new Color[]{Color.web("0xFF0082"),Color.web("0x8D13FA"),Color.web("0xF5DF0D")};
        for (Color c : colors){
            shapes.add(add_rounded_rect(30,300,c));
        }
        this.rotate_node(shapes.get(0),330);
        this.rotate_node(shapes.get(2),30);
        this.rotate_node(shapes.get(1),90);

        shapes.get(0).setTranslateY(-156);
        shapes.get(0).setTranslateX(-20);
        shapes.get(1).setTranslateX(150);
        shapes.get(1).setTranslateY(68);
        shapes.get(2).setTranslateY(-172);
        shapes.get(2).setTranslateX(-8);

        shape_group.getChildren().addAll(shapes);
     this.addStar();
        add_color_switcher(colors);
//        cs.translateY(200);
        if(hasswitch)cs.translateY(-10);

        complete_group.getChildren().add(shape_group);

        complete_group.setTranslateX(50);
//    complete_group.setTranslateY(-200);
        render_collectibles();
    }
    public void motion(double elapsedtime){
        shape_group.getTransforms().add(new Rotate(shape_group.getRotate()+angular_velocity*elapsedtime, 0, 0,0, Rotate.Z_AXIS));
    }
    public boolean check_collision(Player_ball p){
        for(Shape s : this.shapes){
            if(Shape.intersect(p,s).getBoundsInLocal().getWidth()!=-1 && !s.getFill().equals(p.getFill()))
                return true;



        }
        return false;
    }
}

