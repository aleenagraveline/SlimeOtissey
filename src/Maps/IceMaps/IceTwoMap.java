package Maps.IceMaps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.IceTileset;
import Utils.Point;
import Level.Trigger;
import NPCs.Otis;
import Scripts.IceTwoMap.*;
import Scripts.MapTransitions.IceOneTransitionScript;
import Scripts.MapTransitions.IceThreeTransitionScript;

import java.util.ArrayList;

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
        // Otis otis = new Otis(1, getMapTile(0,18).getLocation().subtractX(12)); // Change coordinates as needed to place Otis next to the player
        // npcs.add(otis);

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
        triggers.add(new Trigger(672, 672, 12, 96, new offIceScript(), "playerIsOffIce"));
        triggers.add(new Trigger(1572, 288, 12, 96, new offIceScript(), "playerIsOffIce"));

        // Triggers to switch above triggers back to active when on ice
        triggers.add(new Trigger(720, 672, 12, 96, new onIceScript(), "playerIsOnIce"));
        triggers.add(new Trigger(1524, 288, 12, 96, new onIceScript(), "playerIsOnIce"));

        return triggers;

    }
    
}
