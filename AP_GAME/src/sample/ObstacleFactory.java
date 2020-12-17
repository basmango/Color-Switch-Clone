package sample;

public class ObstacleFactory {
    public Obstacle getObstacle(int i, float difficulty){
        Obstacle ob;
        switch(i){
            case 0: ob = new Horizontal_Bars(difficulty);
                break;
            case 1: ob = new XWheel(difficulty);
                break;
            case 2: ob = new Square(difficulty);
                break;
            case 3: ob = new Triangle(difficulty);
                break;
            case 4: ob = new Circle_ob(difficulty);
                break;
            case 5: ob= new small2circs(difficulty);
                break;
            case 6: ob= new concurrent_circles(difficulty);
                break;
            case 7: ob  = new Vertical_bars(difficulty);
                break;
            case 8:
            default: ob  = new circle_sq(difficulty);
                break;
        }
        return ob;
    }
}
