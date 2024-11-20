package Maps.CaveMaps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.CaveTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.SimpleTextScript;
import Scripts.SimpleTileChangeScript;
import Scripts.CrystalScripts.Crystal1Script;
import Scripts.CrystalScripts.Crystal2Script;
import Scripts.CrystalScripts.Crystal3Script;
import Scripts.CrystalScripts.Crystal4Script;
import Scripts.CrystalScripts.Crystal5Script;
import Scripts.CrystalScripts.Crystal6Script;
import Scripts.CrystalScripts.Crystal7Script;
import Scripts.CrystalScripts.Crystal8Script;
import Scripts.CrystalScripts.Crystal9Script;
import Scripts.CrystalScripts.CrystalPuzzleCompleteScript;
import Scripts.MapTransitions.CaveIceTransitionScript;
import Scripts.MapTransitions.CaveTwoTransitionScript;

import java.util.ArrayList;

import EnhancedMapTiles.EnhancedRock;
import EnhancedMapTiles.PushableRock;

public class CaveThreeMap extends Map {

    public CaveThreeMap() {
        super("cave_three_map.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(11, 29).getLocation(); // Middle of path on bottom
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(11, 28).getLocation()); 
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // CaveTwo transition
        triggers.add(new Trigger(528, 1428, 96, 12, new CaveTwoTransitionScript()));
        // CaveIce transition
        triggers.add(new Trigger(804, 528, 12, 96, new CaveIceTransitionScript()));

        //triggers.add(new Trigger(750, 528, 10, 96, new CrystalPuzzleCompleteScript(), "hasCompletedCrystalPuzzle"));

        return triggers;

    }
    
        @Override
    public void loadScripts() {
        getMapTile(6, 6).setInteractScript(new SimpleTextScript("Pink"));

        getMapTile(6, 16).setInteractScript(new SimpleTextScript("Blue"));

        getMapTile(8, 14).setInteractScript(new Crystal1Script());
        getMapTile(12, 14).setInteractScript(new Crystal2Script());
        getMapTile(10, 16).setInteractScript(new Crystal3Script());
        getMapTile(8, 18).setInteractScript(new Crystal4Script());
        getMapTile(12,18).setInteractScript(new Crystal5Script());
        getMapTile(8, 6).setInteractScript(new Crystal6Script());
        getMapTile(10, 4).setInteractScript(new Crystal7Script());
        getMapTile(10, 8).setInteractScript(new Crystal8Script());
        getMapTile(12,6).setInteractScript(new Crystal9Script());

    }

    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     EnhancedRock enhancedRock = new EnhancedRock(getMapTile(15, 11).getLocation());
    //     EnhancedRock enhancedRock2 = new EnhancedRock(getMapTile(15, 12).getLocation());
    //     enhancedMapTiles.add(enhancedRock);
    //     enhancedMapTiles.add(enhancedRock2);

    //     return enhancedMapTiles;
    // }
}

