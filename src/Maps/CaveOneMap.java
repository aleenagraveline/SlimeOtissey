package Maps;

import Level.Map;
import Tilesets.CaveTileset;
import Level.Trigger;
import Scripts.MapTransitions.ForestCaveTransitionScript;

import java.util.ArrayList;

public class CaveOneMap extends Map {

    public CaveOneMap() {
        super("cave_one_map.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(0, 7).getLocation(); // middle of path on left
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // ForestCave transition trigger
        triggers.add(new Trigger(0, 336, 12, 96, new ForestCaveTransitionScript()));
        return triggers;

    }
    
}
