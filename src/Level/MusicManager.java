package Level;

import javax.sound.sampled.*;
import java.io.File;
import Engine.Config;
import Screens.PlayLevelScreen;

public class MusicManager {
    //iniitialize static variables
    private static Map currentMap = null;
    private static Clip currentClip = null;
    private static PlayLevelScreen playLevelScreenInstance = new PlayLevelScreen(null);

    public static void playMusic (String musicFilePath) {
        
        if (currentMap == null || currentMap != playLevelScreenInstance.getMap()) {
            try {
                if (currentClip != null)
                    currentClip.stop();
                
                currentClip = AudioSystem.getClip();
            } catch (Exception e) { 
                System.out.println(e.toString());
            }
        }
            
        try {
            File song = new File(Config.RESOURCES_PATH + musicFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(song);
                
            //create and play clip
            currentClip.open(audioInputStream);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            currentClip.start();
                
            //store currentMap when song played
            currentMap = playLevelScreenInstance.getMap();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}