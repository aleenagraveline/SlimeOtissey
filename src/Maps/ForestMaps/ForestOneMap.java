package Maps.ForestMaps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.SoundPlayer;
import Level.Trigger;

import NPCs.Otis;
import Scripts.MapTransitions.ForestTwoTransitionScript;
import Scripts.MapTransitions.SpawnTransitionScript;
import Scripts.TestMap.FoodScript;
import Tilesets.ForestTileset;

import java.util.ArrayList;
import javax.swing.JFrame;

import EnhancedMapTiles.Food;

public class ForestOneMap extends Map {

    private SoundPlayer soundPlayer;

    public ForestOneMap() {
        
        super("forest_one_map.txt", new ForestTileset());
        this.playerStartPosition = getMapTile(0, 8).getLocation().addY(24); // middle of path on left
        addMusic("Resources/WhimsyWoods (online-audio-converter.com).wav");
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        Food apple = new Food(getMapTile(22, 6).getLocation());
        apple.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);   
        return enhancedMapTiles;
     }

     public void addMusic(String soundFilePath) {
        soundPlayer = new SoundPlayer(new JFrame(), soundFilePath, 100); // Create SoundPlayer with volume 100
        soundPlayer.play(); // Start playing the music
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(-1, getMapTile(0, 8).getLocation()); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // Spawn transition trigger
        triggers.add(new Trigger(0, 288, 12, 288, new SpawnTransitionScript()));
        // ForestTwo transition trigger
        triggers.add(new Trigger(1428, 288, 12, 288, new ForestTwoTransitionScript()));
        
        return triggers;

    }

}
