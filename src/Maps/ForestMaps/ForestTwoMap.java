package Maps.ForestMaps;

import java.util.ArrayList;

import EnhancedMapTiles.Food;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Otis;
import Scripts.ActivateRandomStepCounterScript;
import Scripts.DeactivateRandomStepCounterScript;
import Scripts.SimpleTextScript;
import Scripts.MapTransitions.ForestOneTransitionScript;
import Scripts.MapTransitions.ForestThreeTransitionScript;
import Scripts.OtisInteractionScripts.OtisForestCaveScript;
import Scripts.OtisInteractionScripts.OtisForestTwoScript;
import Scripts.TestMap.FoodScript;
import Tilesets.ForestTileset;

public class ForestTwoMap extends Map {

    public ForestTwoMap() {
        super("forest_two_map.txt", new ForestTileset());
        this.playerStartPosition = getMapTile(0, 8).getLocation().addY(24); // middle of path at top
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(0, 8).getLocation()); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        Food apple = new Food(getMapTile(5, 16).getLocation());
        apple.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);      
        return enhancedMapTiles;
     }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // ForestOne transition trigger
        triggers.add(new Trigger(0, 288, 12, 288, new ForestOneTransitionScript()));
        // ForestThree transition trigger
        triggers.add(new Trigger(0,1200,12,192, new ForestThreeTransitionScript()));
        triggers.add(new Trigger(48, 288, 10, 288, new OtisForestTwoScript(), "hasEnteredForestTwo"));

        // Triggers to stop counting for random battles
        triggers.add(new Trigger(36, 288, 12, 288, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(1392, 288, 12, 288, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(48, 398, 706, 12, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(48, 454, 638, 12, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(686, 454, 12, 532, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(494, 974, 192, 12, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(494, 986, 12, 288, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(36, 1200, 12, 192, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(48, 1262, 446, 12, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(742, 410, 12, 632, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(550, 1030, 192, 12, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(550, 1042, 12, 288, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        triggers.add(new Trigger(48, 1318, 502, 12, new DeactivateRandomStepCounterScript(), "isNotCountingSteps"));
        // Triggers to start counting for random battles
        triggers.add(new Trigger(96, 366, 706, 12, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(96, 486, 542, 12, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(96, 288, 12, 78, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(96, 498, 12, 98, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(626, 498, 12, 444, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(432, 942, 206, 12, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(48, 596, 60, 12, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(802, 372, 12, 684, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(610, 1062, 192, 12, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(610, 1074, 12, 192, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(96, 1200, 12, 42, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(96, 1350, 12, 42, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(108, 1230, 338, 12, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(108, 1350, 434, 12, new ActivateRandomStepCounterScript(), "isCountingSteps"));
        triggers.add(new Trigger(434, 1152, 12, 78, new ActivateRandomStepCounterScript(), "isCountingSteps"));

        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(17, 8).setInteractScript(new SimpleTextScript("There isn't mush-room over here you should try \nlooking somewhere else"));
    }
    
}
