package Maps.ForestMaps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Otis;
import Scripts.SimpleTextScript;
import Scripts.MapTransitions.ForestCaveTransitionScript;
import Scripts.MapTransitions.ForestTwoTransitionScript;
import Tilesets.ForestTileset;

public class ForestThreeMap extends Map {

    public ForestThreeMap() {
        super("forest_three_map.txt", new ForestTileset());
        this.playerStartPosition = getMapTile(24, 3).getLocation().subtractX(24).subtractY(24); // middle of path at top
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(24, 3).getLocation()); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        
        // ForestTwo transition trigger
        triggers.add(new Trigger(1188, 96, 12, 336, new ForestTwoTransitionScript()));

        // ForestCave transition trigger
        triggers.add(new Trigger(576, 1428, 192, 12, new ForestCaveTransitionScript()));
        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(11, 4).setInteractScript(new SimpleTextScript("Testing"));
        getMapTile(15,16).setInteractScript(new SimpleTextScript("I love my apples :) \nI hope no one eats them!"));
    }
    
}
