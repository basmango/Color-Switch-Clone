package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.Parent;
// Collect the Money Bags!

import javafx.scene.control.Button;

public class game_launcher extends Application
{
    private static DataBase currentd;

    private static void main(String[] args)
    {
        launch(args);
    }
    public void start(Stage theStage) throws IOException, ClassNotFoundException {
        theStage.setMaxHeight(800);
        theStage.setMaxWidth(512);
        theStage.setMinHeight(800);
        theStage.setMinWidth(512);
        deserialize();
        try{
            show_main_menu(theStage);
        }
        catch(Exception e ){
            System.out.println(e.getMessage());

        }
    }

    public void show_main_menu(Stage stage) throws Exception{
        MediaPlayer player = new MediaPlayer(new Media(new File("yay2.mp4").toURI().toString()));
        player.setOnEndOfMedia(new Runnable() {
            public void run() {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                Parent root = null;
                try {
                    root = (Parent)loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MainMenu controller = (MainMenu) loader.getController();
                controller.setStage(stage);
                Scene s = new Scene(root, 512, 800);
                stage.setTitle("Main Menu");
                stage.setScene(s);
                stage.show();
            }
        });
        MediaView mediaView = new MediaView(player);
        player.setAutoPlay(true);
        Group roo = new Group();
        roo.getChildren().add(mediaView);
        Scene scene = new Scene(roo,512,800);
        stage.setTitle("splash");
        stage.setScene(scene);
        stage.show();
    }

    public static DataBase getDatabase(){
        return currentd;
    }
    public static void newDatabase() throws IOException {
        currentd = DataBase.getInstance();
        serialize();
    }
    public static void serialize() throws IOException {
        ObjectOutputStream out=null;
        try {
            out = new ObjectOutputStream (new FileOutputStream("database.txt"));
            out.writeObject(currentd);
        }
        finally {
            out.close();
            //System.out.println("Saved!");
        }
    }
    public static void deserialize() throws ClassNotFoundException, FileNotFoundException, IOException{
//        System.out.println("deserialized");
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream (new FileInputStream("database.txt"));
            currentd = (DataBase) in. readObject();
            in.close();
        }
        catch (FileNotFoundException e){
            currentd = DataBase.getInstance();
        }
        catch (NullPointerException e) {
            currentd = DataBase.getInstance();
            //System.out.println("This user does not exist in the database");
        }
    }
}
