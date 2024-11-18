package Maps.ForestMaps;

import java.util.ArrayList;

import EnhancedMapTiles.Food;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Otis;
import Scripts.SimpleTextScript;
import Scripts.MapTransitions.ForestOneTransitionScript;
import Scripts.MapTransitions.ForestThreeTransitionScript;
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

        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(17, 8).setInteractScript(new SimpleTextScript("There isn't mush-room over here you should try \nlooking somewhere else"));
    }
    
}
