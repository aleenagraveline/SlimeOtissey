package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.MapTransitions.ForestCaveTransitionScript;
import Scripts.MapTransitions.ForestTwoTransitionScript;
import Tilesets.ForestTileset;

public class ForestThreeMap extends Map {

    public ForestThreeMap() {
        super("forest_three_map.txt", new ForestTileset());
        this.playerStartPosition = getMapTile(24, 3).getLocation().subtractX(24).subtractY(24); // middle of path at top
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        
        // ForestTwo transition trigger
        triggers.add(new Trigger(1188, 96, 12, 336, new ForestTwoTransitionScript()));

        // ForestCave transition trigger
        triggers.add(new Trigger(576, 1428, 192, 12, new ForestCaveTransitionScript()));
        return triggers;
    }
    
}
