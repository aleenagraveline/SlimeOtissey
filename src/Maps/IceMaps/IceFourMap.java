package Maps.IceMaps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.IceTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.IceFiveTransitionScript;
import Scripts.MapTransitions.IceThreeTransitionScript;
import Scripts.OtisInteractionScripts.OtisIceFourScript;
import Scripts.OtisInteractionScripts.OtisIceOneScript;
import Scripts.TestMap.FoodScript;

import java.util.ArrayList;

import EnhancedMapTiles.Food;

public class IceFourMap extends Map {

    public IceFourMap() {
        super("ice_four_map.txt", new IceTileset());
        this.playerStartPosition = getMapTile(21, 0).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(21,1).getLocation().subtractX(12)); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        // TODO
        // IceThree transition
        triggers.add(new Trigger(1008, 0, 96, 12, new IceThreeTransitionScript()));
        // IceFive transition
        triggers.add(new Trigger(0, 336, 12, 96, new IceFiveTransitionScript()));

        triggers.add(new Trigger(1008, 48, 96, 10, new OtisIceFourScript(), "hasEnteredIceFour"));

        return triggers;

    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        Food apple = new Food(getMapTile(9, 2).getLocation());
        apple.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);   
        return enhancedMapTiles;
    }
    
}
