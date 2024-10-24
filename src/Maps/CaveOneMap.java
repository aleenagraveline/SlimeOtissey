package Maps;

import Level.Map;
import Level.NPC;
import Tilesets.CaveTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.ForestCaveTransitionScript;

import java.util.ArrayList;

public class CaveOneMap extends Map {

    public CaveOneMap() {
        super("cave_one_map.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(2, 0).getLocation().addX(24); // middle of path on left
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(2, 0).getLocation()); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // ForestCave transition trigger
        triggers.add(new Trigger(96, 0, 96, 12, new ForestCaveTransitionScript()));
        return triggers;

    }
    
}
