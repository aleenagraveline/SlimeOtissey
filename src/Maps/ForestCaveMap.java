package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.CaveOneTransitionScript;
import Scripts.MapTransitions.ForestThreeTransitionScript;
import Tilesets.ForestTileset;

public class ForestCaveMap extends Map {

    public ForestCaveMap() {
        super("forest_cave_map.txt", new ForestTileset());
        this.playerStartPosition = getMapTile(6, 0).getLocation().subtractX(12); // middle of path at top
    }

    // @Override
    // public ArrayList<NPC> loadNPCs() {
    //     ArrayList<NPC> npcs = new ArrayList<>();

    //     // Initialize Otis right next to the player
    //     Otis otis = new Otis(1, getMapTile(6, 0).getLocation()); // Change coordinates as needed to place Otis next to the player
    //     npcs.add(otis);

    //     return npcs;
    // }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // ForestThree transition trigger
        triggers.add(new Trigger(240, 0, 144, 12, new ForestThreeTransitionScript()));
        // CaveOne transition trigger
        triggers.add(new Trigger(240, 708, 144, 12, new CaveOneTransitionScript()));
        return triggers;
    }
    
}
