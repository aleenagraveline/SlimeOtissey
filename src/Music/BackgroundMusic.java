package Music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class BackgroundMusic {

    private Clip clip; // This will be used to play the audio clip

    public void playBackgroundMusic(String filePath) {
        try {
            // Load the audio file
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Loop the sound indefinitely
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start(); // Start playing the audio
        } catch (UnsupportedAudioFileException e) {
            System.err.println("The specified audio file is unsupported: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error playing the audio file: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Audio line for playing back is unavailable: " + e.getMessage());
        }
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Stop the audio playback
        }
    }
}

