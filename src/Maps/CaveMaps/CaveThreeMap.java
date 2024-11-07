package Maps.CaveMaps;

import Level.Map;
import Level.NPC;
import Tilesets.CaveTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.CaveIceTransitionScript;
import Scripts.MapTransitions.CaveTwoTransitionScript;

import java.util.ArrayList;

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

        return triggers;

    }
    
}
