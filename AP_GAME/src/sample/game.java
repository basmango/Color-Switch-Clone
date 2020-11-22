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
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class game {
    game(){
    }
    LinkedList<Obstacle> obs;
    Stage theStage;
    private Scene theScene;
    private ArrayList<String> input;
    private Score_board score_board;
    private Group gp;
    private LinkedList<Screen_art> immobile_gui = new LinkedList<Screen_art>();

    private LinkedList<ColorSwitcher> colorSwitchers = new LinkedList<ColorSwitcher>();
    private LinkedList<Screen_art> mobile_gui = new LinkedList<Screen_art>();
    private Start_art st_art;
    private VBox ObstaclePanel;
    private Player_ball pb;
    private double obs_vel = 0;

    public void start_game(Stage theStage){
        this.theStage = theStage;
        init_gui(theStage);
        input = new ArrayList<String>();
        new AnimationTimer()
        {   long lastNanoTime =System.nanoTime();
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
                }
                else{
                    // acceleration below
                    pb.addVelocity(2000*elapsedTime);
                }

                if (input.contains("SPACE")){
                    pb.setVelocity(-650);
                }

                check_collisions();
                update_obs();
                update_and_refresh(elapsedTime);
                if(at_0percent(pb)){
                    try {
                        exit_menu();
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

    private void exit_menu() throws IOException {
//        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExitMenu.fxml"));
        Parent root = (Parent)loader.load();
        ExitMenu controller = (ExitMenu) loader.getController();
        controller.setStage(theStage);
        Scene s = new Scene(root, 512, 800);
        theStage.setTitle("Resume Games");
        theStage.setScene(s);
        theStage.show();
//        System.out.println("test exec");
    }

    private void animate_obs(double elapsed_time){
        for(Obstacle ob: obs ){
            ob.motion(elapsed_time);
        }
    }
    private void check_collisions(){
        for(Obstacle ob : obs){
            ob.check_collision(pb);
            ob.check_star_collision(pb,theScene,score_board);
        }
        for(ColorSwitcher cs : colorSwitchers){
            if(cs.check_collision(pb,theScene)){
                cs.action(pb,score_board);
                cs.setDisabled();
                colorSwitchers.remove(cs);
                break;
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
        if(obs.size()<3){
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
        Pause pause_button = new Pause();
        this.mobile_gui.add(st_art);
        this.immobile_gui.add(pause_button);

        ObstaclePanel = new VBox();
        ObstaclePanel.setSpacing(100);
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
        addobs();
        for (Node nm: ObstaclePanel.getChildren()){
            nm.setLayoutX(256);
            nm.setTranslateY(-600);
        }
        theScene =new Scene(gp,512,800,Color.web("#242020"));

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
    private void addcolorswitcher(){
        ColorSwitcher cs = new ColorSwitcher();
        colorSwitchers.add(cs);
        addtoVbox(cs.gc.getCanvas());
        cs.translateX(232);
        cs.render();

    }
    private void addobs(){
        Obstacle ob = new Obstacle();
        obs.add(ob);
        addtoVbox(ob.complete_group);
        addcolorswitcher();
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