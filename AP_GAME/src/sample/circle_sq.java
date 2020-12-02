package sample;

public class circle_sq extends Obstacle {
    circle_sq(){
        init();
    }
    @Override
    public void motion(double elapsedtime) {

    }

    @Override
    public boolean check_collision(Player_ball p) {
        return false;
    }

    @Override
    protected void init() {

    }
}
