package sample;

import java.awt.*;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public  class Obstacle extends Group {

    Obstacle(){
        super();

        Arc ar1 = create_arc(0,90, Color.BLUE);
        Arc ar2 = create_arc(90,90,Color.YELLOW);
        Arc ar3 = create_arc(180,90, Color.GREEN);
        Arc ar4 = create_arc(270,90, Color.RED);

//        this.getChildren().add(drawSemiRing(350, 350, 200, 30, Color.LIGHTSKYBLUE, Color.DARKBLUE));

        this.getChildren().addAll(ar1,ar2,ar3,ar4);

    }
    private Arc create_arc(int offset,int len,Color cl){
        Arc arc = new Arc(500,30,150,150,offset,len);

        //Setting the properties of the arc

        arc.setStrokeWidth(25);
        arc.setStrokeType(StrokeType.CENTERED);
        arc.setFill(javafx.scene.paint.Color.TRANSPARENT);
        arc.setStroke(cl);
        arc.setType(ArcType.OPEN);

        return arc;

    }
    private Path drawSemiRing(double centerX, double centerY, double radius, double innerRadius, Color bgColor, Color strkColor) {
        Path path = new Path();
        path.setFill(bgColor);
        path.setStroke(strkColor);
        path.setFillRule(FillRule.EVEN_ODD);

        MoveTo moveTo = new MoveTo();
        moveTo.setX(centerX + innerRadius);
        moveTo.setY(centerY);

        ArcTo arcToInner = new ArcTo();
        arcToInner.setX(centerX - innerRadius);
        arcToInner.setY(centerY);
        arcToInner.setRadiusX(innerRadius);
        arcToInner.setRadiusY(innerRadius);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX + innerRadius);
        moveTo2.setY(centerY);

        HLineTo hLineToRightLeg = new HLineTo();
        hLineToRightLeg.setX(centerX + radius);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radius);
        arcTo.setY(centerY);
        arcTo.setRadiusX(radius);
        arcTo.setRadiusY(radius);

        HLineTo hLineToLeftLeg = new HLineTo();
        hLineToLeftLeg.setX(centerX - innerRadius);

        path.getElements().add(moveTo);
        path.getElements().add(arcToInner);
        path.getElements().add(moveTo2);
        path.getElements().add(hLineToRightLeg);
        path.getElements().add(arcTo);
        path.getElements().add(hLineToLeftLeg);

        return path;
    }



}
