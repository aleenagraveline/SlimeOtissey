package Level;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

//import Maps.ForestOneMap;

public class SoundPlayer extends JFrame {
  Long currentFrame;
  public String status;
  String soundPath;
  public Clip clip;
  public static boolean musicPlaying = false;
  private FloatControl volumeControl;

  private AudioInputStream audioInputStream;

  public SoundPlayer(JFrame jFrame, String soundFilePath, int startVolume) {
    try {
      soundPath = soundFilePath;
      audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath).getAbsoluteFile());
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      System.out.println("clip opened");
      volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); // set the volume for the the audio
      setVolume(startVolume);                                                                              // clip
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (Exception e) {
      System.out.println("Error with creating sound player");
      e.printStackTrace();
    }
  }



  // public SoundPlayer(JFrame frame, String soundFilePath, int startVolume) {
  //   this(frame, soundFilePath);
  //   setVolume(startVolume);
  // }

  // volume from 0-100;
  public void setVolume(int volume) {
    // clamp and adjust volume to usable value
    float vol = 1.0f;  // 100% volume corresponds to 1.0
    float dB = (float) (Math.log10(vol) * 20);
    if (volumeControl != null)
      volumeControl.setValue(dB);

    //   public void setVolume() {
    //     // Hardcode the volume to 100

    //     if (volumeControl != null) {
    //         volumeControl.setValue(dB);  // Set to maximum volume (100%)
    //     }
    // }
    
  }

  // public FloatControl getVolume() {
  //   return volumeControl;
  // }

  public void play() {
    clip.start();
    status = "play";
  }

  public void pause() {
    this.currentFrame = this.clip.getMicrosecondPosition();
    clip.stop();
    status = "paused";
  }

  public void resume() {
    try {
      if (status.equals("play")) {
        return;
      }
      clip.close();
      resetAudioStream();
      clip.setMicrosecondPosition(currentFrame);
      this.play();
    } catch (Exception e) {
      System.out.println("resume error");
    }
  }

  public void resetAudioStream() {
    try {
      audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());
      clip.open(audioInputStream);
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (Exception e) {
      System.out.println("reset error");
    }
  }

}