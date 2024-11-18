package Maps.IceMaps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.IceTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.SimpleTextScript;
import Scripts.IceTwoMap.*;
import Scripts.MapTransitions.IceOneTransitionScript;
import Scripts.MapTransitions.IceThreeTransitionScript;
import Scripts.TestMap.FoodScript;

import java.util.ArrayList;

import EnhancedMapTiles.Food;
import EnhancedMapTiles.SlidingIce;

public class IceTwoMap extends Map {

    public IceTwoMap() {
        super("ice_two_map.txt", new IceTileset());
        this.playerStartPosition = getMapTile(0, 14).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(0, 15).getLocation()); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        for (int row = 5; row < 16; row++) {
            for (int column = 15; column < 32; column++) {
        
                boolean doNotPlace = (row == 5 && column == 17) ||
                (row == 5 && column == 31) || (row == 7 && column == 26) ||
                (row == 8 && column == 31) || (row == 9 && column == 24) ||
                (row == 10 && column == 31) || (row == 11 && column == 16) ||
                (row == 15 && column == 25);
                
                if (!doNotPlace){
                    SlidingIce slidingIce = new SlidingIce(getMapTile(column, row).getLocation());
                    enhancedMapTiles.add(slidingIce);
                }
            }
        }

        Food apple = new Food(getMapTile(14, 8).getLocation());
        apple.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        
        // IceOne transition
        triggers.add(new Trigger(0, 672, 12, 96, new IceOneTransitionScript()));
        // IceThree transition
        triggers.add(new Trigger(2148, 240, 12, 96, new IceThreeTransitionScript()));

        // Triggers to unlock player after leaving ice
        triggers.add(new Trigger(672, 672, 12, 96, new OffIceScript(), "playerIsOffIce"));
        triggers.add(new Trigger(1572, 288, 12, 96, new OffIceScript(), "playerIsOffIce"));

        // Triggers to switch above triggers back to active when on ice
        triggers.add(new Trigger(720, 672, 12, 96, new OnIceScript(), "playerIsOnIce"));
        triggers.add(new Trigger(1524, 288, 12, 96, new OnIceScript(), "playerIsOnIce"));

        // Trigger to gain friendship
        triggers.add(new Trigger(1632, 240, 12, 96, new SlidingCompleteScript(), "hasCompletedSlidingPuzzle"));

        return triggers;

    }

    @Override
    public void loadScripts() {
        getMapTile(9, 13).setInteractScript(new SimpleTextScript("Caution \nIce is Slippery when wet!"));
    }
    
}
