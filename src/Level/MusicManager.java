package Level;

import javax.swing.JFrame;
import java.util.HashMap;
import java.util.Map;

public class MusicManager {
    private static SoundPlayer currentSoundPlayer;
    private static Map<String, String> mapToSongFile = new HashMap<>();

    static {
        // Set paths for each map's music
        mapToSongFile.put("Village", "Resources/VillageSong (online-audio-converter.com).wav");
        mapToSongFile.put("ForestOneMap", "Resources/WhimsyWoods (online-audio-converter.com).wav");
        mapToSongFile.put("CaveOneMap", "Resources/Cave.wav");
    }

    public static void playMapMusic(JFrame jFrame, String mapName) {
        // Get song file for the map
        String soundFilePath = mapToSongFile.get(mapName);

        if (soundFilePath != null) {
            // Stop current song if already playing
            if (currentSoundPlayer != null && currentSoundPlayer.clip.isRunning()) {
                currentSoundPlayer.clip.stop();
                currentSoundPlayer.clip.close();
            }

            // Start new song for the new map
            currentSoundPlayer = new SoundPlayer(jFrame, soundFilePath, 100); // 100 for full volume
            currentSoundPlayer.play();
        } else {
            System.out.println("No song mapped for " + mapName);
        }
    }
}
