package sample;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Sound {
        private static Clip clip_star;
        private static Clip clip_color;
        private static Clip jump;
        private static Clip death;
        private static void init_sounds(){
                       clip_star = get_sound("star");
                       clip_color = get_sound("colorswitch");
                       jump = get_sound("jump");
                       death = get_sound("dead");
        }
        public static  void play_sound(String s){

                if(clip_star==null)init_sounds();
                Clip pl = clip_selector(s);
                if(pl!=null) {
                        pl.setFramePosition(0);
                        pl.start();


                }
        }
        private static Clip clip_selector(String s){
                if(s.equals("star"))return clip_star;
                if(s.equals("colorswitch"))return clip_color;
                if(s.equals("jump"))return jump;
                if(s.equals("dead"))return death;
                return  null;
        }
        private static  Clip get_sound(String s){

                AudioInputStream audioInputStream = null;
                try {
                audioInputStream = AudioSystem.getAudioInputStream(new File("Sound_Effects/"+s+".wav").getAbsoluteFile());
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio");
        } catch (IOException e) {
                System.out.println("Invalid audio String");
                System.out.println(System.getProperty("user.dir"));
                return null;
        }
                Clip clip = null;
        try {
                clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
                e.printStackTrace();
        }
        try {
                clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return clip;

}

}
//class Sound_Thread extends Thread{
//        public void run(){
//
//
//        }
//
//}