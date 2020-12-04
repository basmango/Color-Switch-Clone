package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


public class game {
    private static game instance;
    public game(){
        instance = this;
    }
    public static game getInstance() {
        return instance;
    }
    LinkedList<Obstacle> obs;
    Stage theStage;
    private Scene theScene;
    private ArrayList<String> input;
    private Score_board score_board;
    private Group gp;
    private LinkedList<Screen_art> immobile_gui = new LinkedList<Screen_art>();
    private  Pause pause_button;
    private LinkedList<ColorSwitcher> colorSwitchers = new LinkedList<ColorSwitcher>();
    private LinkedList<Screen_art> mobile_gui = new LinkedList<Screen_art>();
    private Start_art st_art;
    private VBox ObstaclePanel;
    private Player_ball pb;
    private double obs_vel = 0;
    private float difficulty_increment = 0.05f;
    private float difficulty  = 1f;
    private boolean isPaused;
    public Score_board getScore_board() {
        return score_board;
    }

    public void start_game(Stage theStage){
        this.theStage = theStage;
        init_gui(theStage);
        gameloop();
    }
    private void gameloop(){
        input = new ArrayList<String>();

        new AnimationTimer()
        {   long lastNanoTime = System.nanoTime();
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;

                //Rotation
                animate_obs(elapsedTime);
                while (at_60percent()){
                    simulate_climb();
                }
                if(pb.getLayoutY()>=st_art.getPositionY()-50){
                    pb.setVelocity(0);
                    pb.setLayoutY(st_art.getPositionY()-50);
                }else if(isPaused){
                    pb.setVelocity(0);
                    isPaused = false;
                }
                else{
                    // acceleration below
                    pb.addVelocity(3000*elapsedTime);
                }


                if (input.contains("SPACE")){
                    pb.unfreeze();
                    pb.setVelocity(-650);
                }

                update_obs();
                update_and_refresh(elapsedTime);
                if(check_collisions()){
                    try {
                        exit_menu();
                        isPaused = true;
//                        set_resume_point();
                        this.stop();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(at_0percent(pb)){
                    try {
                        exit_menu();
                        pb.freeze();
                        pb.setTranslateY(pb.getTranslateY()-50);
                        this.stop();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(pause_button.isClicked()){
                    pause_button.Clickcheckdone();
                    try {
                        pause_menu();
                        isPaused = true;
                        this.stop();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
//        System.out.println("testend");
        theStage.show();
    }
//    private void set_resume_point(){
//        obs.
//    }
    private void pause_menu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseMenu.fxml"));
        Parent root = (Parent)loader.load();
        PauseMenu controller = (PauseMenu) loader.getController();
        controller.setStage(theStage);
        Scene s = new Scene(root, 512, 800);
        theStage.setTitle("Pause Game");
        theStage.setScene(s);
        theStage.show();


    }
    public void resume_game(){
        this.theStage.setScene(theScene);
        pb.freeze();
        gameloop();


    }
    private void exit_menu() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExitMenu.fxml"));
        Parent root = (Parent)loader.load();
        ExitMenu controller = (ExitMenu) loader.getController();
        controller.setStage(theStage);
        Scene s = new Scene(root, 512, 800);
        theStage.setTitle("Exit Menu");
        theStage.setScene(s);
        theStage.show();
    }

    private void animate_obs(double elapsed_time){
        for(Obstacle ob: obs ){
            ob.motion(elapsed_time);
        }
    }
    private boolean check_collisions(){
        for(Obstacle ob : obs){
            if(ob.check_collision(pb)){
                translate_to_safe();
                return true;
            }
             if(ob.check_collectible_collision(pb,theScene,score_board)){
                 difficulty+=difficulty_increment;
             }
        }

        return false;
    }
    private void translate_to_safe(){
        boolean nostarscollected = true;
        for(Obstacle ob : obs){
            if(!ob.hasStar){
                nostarscollected = false;
                ob.check_bound_collision(pb);
            }
            if(nostarscollected){
                pb.setVelocity(0);
                pb.setTranslateY(pb.getTranslateY()+100);
            }
        }
    }

    private void update_obs(){
        Obstacle ob;
        ob = obs.getFirst();
        if(at_0percent_obs(ob)){
            obs.remove(ob);
            ObstaclePanel.getChildren().remove(ob);
        }

        while(obs.size()<3){
            addobs();
        }
    }
    private void update_and_refresh(double elapsedTime){
        input.clear();
        pb.update(elapsedTime);
        score_board.render();
    }

    private boolean at_0percent_obs(Obstacle ob){

        return (ob.complete_group.localToScene(ob.complete_group.getBoundsInLocal()).getMinY()>theScene.getHeight());
    }
    private boolean at_0percent(Player_ball pb){

        return (pb.getBoundsInParent().getMaxY()>theScene.getHeight());
    }
    private boolean at_60percent(){
        return (pb.getBoundsInParent().getMaxY()<theScene.getHeight()/2-60);
    }
    private void init_gui(Stage theStage){
        theStage.setTitle( "Color Switch" );
        gp = new Group();
        st_art = new Start_art();
        score_board = new Score_board();
       pause_button = new Pause();
        this.mobile_gui.add(st_art);
        this.immobile_gui.add(pause_button);

        ObstaclePanel = new VBox();
        ObstaclePanel.setSpacing(0);
//        ObstaclePanel.setTranslateY(-300);
        obs= new LinkedList<Obstacle>();
        //ObstaclePanel.getChildren().add(art);
        pb = new Player_ball();
        pb.setLayoutX(256);
        pb.setLayoutY(500);
        gp.getChildren().addAll(ObstaclePanel,pb);
        pause_button.addto(gp);
        score_board.addto(gp);
        st_art.addto(gp);
        addobs();
        if (obs.getFirst().cs!=null) {
            obs.getFirst().cs.action(pb,score_board);
            obs.getFirst().cs.setDisabled();
        }
        addobs();
        for (Node nm: ObstaclePanel.getChildren()){
//            nm.setLayoutX(256);
            nm.setTranslateY(-800);
        }
        theScene =new Scene(gp,512,800,Color.web("292929"));

        theStage.setScene(theScene);

        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
//                    input.remove( code );
                        input.add( code );
                    }
                });

        for(Screen_art sc : immobile_gui) sc.render();
        for(Screen_art sc: mobile_gui) sc.render();
    }

    private void addobs(){
        Random random = new Random();
        Obstacle ob;
        switch(random.nextInt(9 )){
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

        obs.add(ob);
        addtoVbox(ob.complete_group);


    }
    private void addtoVbox(Node addition){
        double max = 0;
        for(Node x: ObstaclePanel.getChildren()){
            x.setTranslateY(x.getTranslateY()-addition.getBoundsInLocal().getHeight()-ObstaclePanel.getSpacing());
            max = x.getTranslateY();
        }
        ObstaclePanel.getChildren().add(0,addition);
        addition.setTranslateY(max);
    }

    private void simulate_climb(){
        for(Node x: ObstaclePanel.getChildren()){
            x.setTranslateY(x.getTranslateY()+2);
        }

        pb.setTranslateY(pb.getTranslateY()+2);
        for(Screen_art sc: mobile_gui){
            sc.translateY(2);
            sc.render();
        }

    }
}