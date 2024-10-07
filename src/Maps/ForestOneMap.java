package Maps;

import Level.Map;
import Level.Trigger;
import Scripts.MapTransitions.SpawnTransitionScript;
import Tilesets.CommonTileset;

import java.util.ArrayList;

public class ForestOneMap extends Map {

    public ForestOneMap() {
        super("forest_one_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(0, 9).getLocation(); // middle of path on left
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // Spawn transition trigger
        triggers.add(new Trigger(0, 384, 12, 144, new SpawnTransitionScript()));
        return triggers;
    }

}
