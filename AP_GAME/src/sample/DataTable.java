package sample;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class DataTable implements Serializable {
    private static final long serialVersionUID=23L;
    private static int id = 0;
    private int gameId;
    private ArrayList<Integer> obsid;
    private ArrayList<Boolean> star;
    private ArrayList<Boolean> colorSwitcher;
    private ArrayList<Float> difficulty;
    private double timeElapsed;
    private String date;
    private String time;
    private long stars_collected;

    public long getStars_collected() {
        return stars_collected;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<Integer> getObsid() {
        return obsid;
    }

    public ArrayList<Boolean> getStar() {
        return star;
    }

    public ArrayList<Boolean> getColorSwitcher() {
        return colorSwitcher;
    }

    public ArrayList<Float> getDifficulty() {
        return difficulty;
    }

    public double getTimeElapsed() {
        return this.timeElapsed;
    }

    public DataTable(double timeElapsed){
        LocalDate obj1 = LocalDate.now();
        LocalTime obj2 = LocalTime.now();
        DateTimeFormatter FormatObj1 = DateTimeFormatter.ofPattern("dd-MMM-yy");
        DateTimeFormatter FormatObj2 = DateTimeFormatter.ofPattern("hh:mm a");
        date = obj1.format(FormatObj1);
        time = obj2.format(FormatObj2);
        id++;
        this.gameId=id;
        this.obsid = new ArrayList<>();
        this.star = new ArrayList<>();
        this.colorSwitcher = new ArrayList<>();
        this.difficulty = new ArrayList<>();
        this.timeElapsed = timeElapsed;
    }

    public void update(double timeElapsed, LinkedList<Obstacle> obs, Score_board score, Player_ball pb) {

        this.timeElapsed = timeElapsed;
        for(int i=0;i<obs.size();i++){
            obsid.add(obs.get(i).getId());
            star.add(obs.get(i).isHasStar());
            colorSwitcher.add(obs.get(i).isHasswitch());
            difficulty.add(obs.get(i).getDifficulity_float());
        }
        this.stars_collected = score.getScore();
    }

    public void saveGame(){
        game_launcher.getDatabase().getDatabaseFiles().add(this);
        try{
            game_launcher.serialize();
        }
        catch (IOException e){
            System.out.println("Cant close stream");
        }
    }
}
