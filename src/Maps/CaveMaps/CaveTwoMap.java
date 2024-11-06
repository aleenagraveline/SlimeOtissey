package Maps.CaveMaps;

import Level.Map;
import Level.NPC;
import Tilesets.CaveTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.CaveThreeTransitionScript;
import Scripts.MapTransitions.CaveOneTransitionScript;

import java.util.ArrayList;

public class CaveTwoMap extends Map {

    public CaveTwoMap() {
        super("cave_two_map.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(0, 18).getLocation(); // Middle-ish of path on left
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
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // CaveOne transition
        triggers.add(new Trigger(0, 864, 12, 96, new CaveOneTransitionScript()));
        // CaveThree transition
        triggers.add(new Trigger(528, 0, 96, 12, new CaveThreeTransitionScript()));

        return triggers;

    }
    
}
