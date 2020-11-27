package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public class Circle_ob extends Obstacle{
Circle_ob(){
    shapes.add(add_arc(150,125,true,true, Color.web("0xFF0082")));
    shapes.add(add_arc(150,125,false,true,Color.web("0x8D13FA")));
    shapes.add(add_arc(150,125,true,false,Color.web("0x35E2F2")));
    shapes.add(add_arc(150,125,false,false,Color.web("0xF5DF0D")));
    //add_arc(this,-1,1,Color.CYAN);
    // add_arc(this,-1,-1,Color.YELLOW)
    // ;
    star = new Star();

    shape_group.getChildren().addAll(shapes);
    complete_group.getChildren().add(shape_group);

    star.addto(complete_group);
    complete_group.setTranslateX(105);
    complete_group.setTranslateY(-200);
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
