package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.PushableRock;
import Level.EnhancedMapTile;
import Level.Map;
import Level.Trigger;
import Scripts.MapTransitions.CaveOneTransitionScript;
import Scripts.MapTransitions.ForestThreeTransitionScript;
import Scripts.RockPuzzleScripts.*;
import Scripts.TestMap.RockPuzzleFirstRockScript;
import Tilesets.ForestTileset;

public class ForestCaveMap extends Map {

    public ForestCaveMap() {
        super("forest_cave_map.txt", new ForestTileset());
        this.playerStartPosition = getMapTile(6, 0).getLocation().subtractX(12); // middle of path at top
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        //ROCK PUZZLE TRIGGERS
        //triggers.add(new Trigger(240, 576, 144, 48, new RockPuzzleStartScript()));
        triggers.add(new Trigger(240, 624, 5, 48, new RockPuzzleFirstRockScript(), "hasPushedFirstRock"));
        triggers.add(new Trigger(379, 624, 5, 48, new RockPuzzleSecondRockScript(), "hasPushedSecondRock"));
        triggers.add(new Trigger(240, 680, 144, 48, new RockPuzzleCompleteScript()));

        // ForestThree transition trigger
        triggers.add(new Trigger(240, 0, 144, 12, new ForestThreeTransitionScript()));
        // CaveOne transition trigger
        triggers.add(new Trigger(240, 708, 144, 12, new CaveOneTransitionScript()));
        //rock puzzle complete trigger
        return triggers;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PushableRock pushableRock = new PushableRock(getMapTile(5, 13).getLocation());
        PushableRock pushableRock2 = new PushableRock(getMapTile(7, 13).getLocation());
        enhancedMapTiles.add(pushableRock);
        enhancedMapTiles.add(pushableRock2);

        return enhancedMapTiles;
    }
    
}
