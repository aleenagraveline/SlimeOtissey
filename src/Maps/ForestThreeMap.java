package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Tilesets.ForestTileset;

public class ForestThreeMap extends Map {

    public ForestThreeMap() {
        super("forest_three_map.txt", new ForestTileset());
        // this.playerStartPosition = getMapTile(0, 8).getLocation().addY(24); // middle of path at top
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // ForestOne transition trigger
        // triggers.add(new Trigger(0, 288, 12, 288, new ForestOneTransitionScript()));
        return triggers;
    }
    
}
