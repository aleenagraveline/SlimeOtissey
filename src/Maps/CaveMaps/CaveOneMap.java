package Maps.CaveMaps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.CaveTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.SimpleTextScript;
import Scripts.MapTransitions.CaveTwoTransitionScript;
import Scripts.MapTransitions.ForestCaveTransitionScript;
import Scripts.TestMap.FoodScript;

import java.util.ArrayList;

import EnhancedMapTiles.Food;

public class CaveOneMap extends Map {

    public CaveOneMap() {
        super("cave_one_map.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(0, 7).getLocation(); // middle of path on left
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(0, 7).getLocation().subtractX(24)); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // ForestCave transition trigger
        triggers.add(new Trigger(0, 336, 12, 96, new ForestCaveTransitionScript()));
        // CaveTwo transition
        triggers.add(new Trigger(2148, 336, 12, 96, new CaveTwoTransitionScript()));

        return triggers;

    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        Food apple = new Food(getMapTile(27, 4).getLocation());
        apple.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);   
        return enhancedMapTiles;
    }
    
    @Override
    public void loadScripts() {
        getMapTile(4, 6).setInteractScript(new SimpleTextScript("*No one understands what this sign says, \nit seems to be written in a different language*"));
    }
}
