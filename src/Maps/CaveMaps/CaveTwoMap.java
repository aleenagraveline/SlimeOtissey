package Maps.CaveMaps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.CaveTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.CaveThreeTransitionScript;
import Scripts.OtisInteractionScripts.OtisCaveTwoScript;
import Scripts.OtisInteractionScripts.OtisForestCaveScript;
import Scripts.TestMap.FoodScript;
import Scripts.SimpleTextScript;
import Scripts.MapTransitions.CaveOneTransitionScript;

import java.util.ArrayList;

import EnhancedMapTiles.Food;

public class CaveTwoMap extends Map {

    public CaveTwoMap() {
        super("cave_two_map.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(0, 18).getLocation(); // Middle-ish of path on left
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        //Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(0,18).getLocation().subtractX(12)); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        Food apple = new Food(getMapTile(2, 11).getLocation());
        apple.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);   
        return enhancedMapTiles;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // CaveOne transition
        triggers.add(new Trigger(0, 864, 12, 96, new CaveOneTransitionScript()));
        // CaveThree transition
        triggers.add(new Trigger(528, 0, 96, 12, new CaveThreeTransitionScript()));
        triggers.add(new Trigger(48, 864, 10, 96, new OtisCaveTwoScript(), "hasEnteredCaveTwo"));

        return triggers;

    }

    @Override
    public void loadScripts() {
        getMapTile(10, 2).setInteractScript(new SimpleTextScript("Do not trust anyone."));
    }
    
}
