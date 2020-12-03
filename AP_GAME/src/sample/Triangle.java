package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public class Triangle extends Obstacle{
    Triangle(){
        init();

    }
    protected void init(){
        this.rect_bound();
        boundbox.setHeight(600);
        boundbox.setWidth(500);
        boundbox.setTranslateX(-200);
        boundbox.setTranslateY(-200);
//        this.boundbox.setFill(Color.BEIGE);
        Color[] colors  = new Color[]{Color.web("0xFF0082"),Color.web("0x8D13FA"),Color.web("0xF5DF0D")};
        for (Color c : colors){
            shapes.add(add_rounded_rect(30,300,c));
        }
        this.rotate_node(shapes.get(0),330);
        this.rotate_node(shapes.get(1),30);
        this.rotate_node(shapes.get(2),90);

        shapes.get(0).setTranslateY(-156);
        shapes.get(0).setTranslateX(-10);
        shapes.get(2).setTranslateX(160);
        shapes.get(2).setTranslateY(70);
        shapes.get(1).setTranslateY(-170);
        shapes.get(1).setTranslateX(3);

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

