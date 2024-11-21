package Maps.ForestMaps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.MusicManager;
import Level.Trigger;

import NPCs.Otis;
import Screens.ForestScreens.ForestOneScreen;
import Scripts.OtisInteractionScripts.ScaredOtisScript;
import Scripts.ActivateRandomStepCounterScript;
import Scripts.DeactivateRandomStepCounterScript;
import Scripts.SimpleTextScript;
import Scripts.MapTransitions.ForestTwoTransitionScript;
import Scripts.MapTransitions.SpawnTransitionScript;
import Scripts.OtisInteractionScripts.ScaredOtisScript;
import Scripts.TestMap.FoodScript;
import Tilesets.ForestTileset;

import java.util.ArrayList;
import javax.swing.JFrame;

import EnhancedMapTiles.Food;
import Game.ScreenCoordinator;


public class ForestOneMap extends Map {



    public ForestOneMap() {
        
        super("forest_one_map.txt", new ForestTileset());
        this.playerStartPosition = getMapTile(0, 8).getLocation().addY(24); // middle of path on left
    }


    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        Food apple = new Food(getMapTile(22, 6).getLocation());
        apple.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);   
        return enhancedMapTiles;
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

        triggers.add(new Trigger(100, 288, 10, 288, new ScaredOtisScript(),"scaredOtis"));

        // Triggers to stop counting for random battles
        triggers.add(new Trigger(36, 288, 12, 288, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(1392, 288, 12, 288, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(48, 398, 1344, 12, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(48, 454, 1344, 12, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        // Triggers to start counting for random battles
        triggers.add(new Trigger(96, 366, 1248, 12, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(96, 486, 1248, 12, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(96, 288, 12, 78, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(1332, 288, 12, 78, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(96, 498, 12, 78, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(1332, 498, 12, 78, new ActivateRandomStepCounterScript(), "isCountingSteps"));

        return triggers;

    }

    @Override
    public void loadScripts() {
        getMapTile(3, 7).setInteractScript(new SimpleTextScript("Whimsy Woods \nDANGER: Stay on the path"));
    }

}
