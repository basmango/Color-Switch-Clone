package sample;

public class Star extends Collectible {
    Star(){
        this.init_canvas();
        this.setImage_primary("star.png",50,50);

        this.render();
    }
    public  void action (Player_ball pb,Score_board sc){
        if(!this.getDisabled())sc.increment();
    }
}
