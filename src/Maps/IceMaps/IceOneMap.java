package Maps.IceMaps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.IceTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.IceTwoTransitionScript;
import Scripts.TestMap.FoodScript;
import Scripts.SimpleTextScript;
import Scripts.MapTransitions.CaveIceTransitionScript;

import java.util.ArrayList;

import EnhancedMapTiles.Food;

public class IceOneMap extends Map {

    public IceOneMap() {
        super("ice_one_map.txt", new IceTileset());
        this.playerStartPosition = getMapTile(3, 17).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(3,17).getLocation().subtractX(12)); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        Food apple = new Food(getMapTile(21, 16).getLocation());
        apple.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);   
        return enhancedMapTiles;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // CaveIce transition
        triggers.add(new Trigger(144, 852, 96, 12, new CaveIceTransitionScript()));
        // IceTwo transition
        triggers.add(new Trigger(1140, 336, 12, 96, new IceTwoTransitionScript()));

        return triggers;

    }

    @Override
    public void loadScripts() {
        getMapTile(10, 11).setInteractScript(new SimpleTextScript("I see you've got a friend with you... remember me \n\"Otis\"?"));
    }
    
}
