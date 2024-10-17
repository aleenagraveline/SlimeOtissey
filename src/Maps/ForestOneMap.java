package Maps;

import Level.Map;
import Level.SoundPlayer;
import Level.Trigger;
import Scripts.MapTransitions.SpawnTransitionScript;
import Tilesets.CommonTileset;

import java.util.ArrayList;
import javax.swing.JFrame;

public class ForestOneMap extends Map {

    private SoundPlayer soundPlayer;

    public ForestOneMap() {
        super("forest_one_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 8).getLocation().addY(24); // middle of path on left
        addMusic("Resources/Whimsy Woods (online-audio-converter.com).wav");
    }

     public void addMusic(String soundFilePath) {
        soundPlayer = new SoundPlayer(new JFrame(), soundFilePath, 100); // Create SoundPlayer with volume 100
        soundPlayer.play(); // Start playing the music
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // Spawn transition trigger
        triggers.add(new Trigger(0, 384, 12, 144, new SpawnTransitionScript()));
        return triggers;
    }

}
