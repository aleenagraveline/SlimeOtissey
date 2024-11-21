package Maps.IceMaps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.IceTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.SimpleTextScript;
import Scripts.MapTransitions.IceFourTransitionScript;
import Scripts.MapTransitions.IceTwoTransitionScript;
import Scripts.OtisInteractionScripts.OtisIceOneScript;
import Scripts.OtisInteractionScripts.OtisIceThreeScript;
import Scripts.TestMap.FoodScript;

import java.util.ArrayList;

import EnhancedMapTiles.Food;

public class IceThreeMap extends Map {

    public IceThreeMap() {
        super("ice_three_map.txt", new IceTileset());
        this.playerStartPosition = getMapTile(0, 4).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(0,5).getLocation().subtractX(12)); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        
        // IceTwo transition
        triggers.add(new Trigger(0, 192, 12, 96, new IceTwoTransitionScript()));
        // IceFour transition
        triggers.add(new Trigger(432, 1428, 96, 12, new IceFourTransitionScript()));

        triggers.add(new Trigger(48, 192, 10, 96, new OtisIceThreeScript(), "hasEnteredIceThree"));

        return triggers;

    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        Food apple = new Food(getMapTile(2, 14).getLocation());
        apple.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);   
        return enhancedMapTiles;
    }

    @Override
    public void loadScripts() {
        getMapTile(8, 6).setInteractScript(new SimpleTextScript("Don't you miss your brothers and sisters? \nI wonder how they're doing."));
    }
    
}
