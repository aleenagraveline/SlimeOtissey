package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.MapTransitions.ForestOneTransitionScript;
import Scripts.MapTransitions.ForestThreeTransitionScript;
import Tilesets.ForestTileset;

public class ForestTwoMap extends Map {

    public ForestTwoMap() {
        super("forest_two_map.txt", new ForestTileset());
        this.playerStartPosition = getMapTile(0, 8).getLocation().addY(24); // middle of path at top
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // ForestOne transition trigger
        triggers.add(new Trigger(0, 288, 12, 288, new ForestOneTransitionScript()));
        // ForestThree transition trigger
        triggers.add(new Trigger(0,1200,12,192, new ForestThreeTransitionScript()));

        return triggers;
    }
    
}
