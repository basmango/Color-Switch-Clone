package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorSwitcher extends Collectible {
    private Color color;
    ColorSwitcher(){
        this.init_canvas();
        List<Color> colorList = new ArrayList<Color>();
        this.setImage();
        colorList.add(Color.web("0xFF0082"));
        colorList.add(Color.web("0x8D13FA"));
        colorList.add(Color.web("0x35E2F2"));
        colorList.add(Color.web("0xF5DF0D"));
        Random rand = new Random();
        color  = colorList.get(rand.nextInt(colorList.size()));
    }
    ColorSwitcher(Color[] colors){
        this.init_canvas();
        this.setImage();
        Random rand = new Random();
        this.color = colors[(rand.nextInt(colors.length))];

    }
    private void setImage(){
        this.setImage_primary("switcher.png",50,50);
    }

    public Color getColor(){
        return color;

    }


    public  void action (Player_ball pb,Score_board sc){
        if (!this.getDisabled())pb.setFill(this.getColor());
    }
}
