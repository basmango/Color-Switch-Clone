package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorSwitcher extends Collectible {
    private Color color;
    ColorSwitcher(){
        this.init_canvas();
        this.setImage_primary("switcher.png",50,50);
        List<Color> colorList = new ArrayList<Color>();
        colorList.add(Color.DODGERBLUE);
        colorList.add(Color.PURPLE);
        colorList.add(Color.DARKCYAN);
        colorList.add(Color.ORANGE);
        Random rand = new Random();
        color  = colorList.get(rand.nextInt(colorList.size()));
    }
    public Color getColor(){
        return color;
    }
    public  void action (Player_ball pb,Score_board sc){
        pb.setFill(this.getColor());
    }
}
