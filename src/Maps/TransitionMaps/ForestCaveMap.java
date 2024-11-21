package Maps.TransitionMaps;

import java.util.ArrayList;

import EnhancedMapTiles.PushableRock;
import EnhancedMapTiles.EnhancedRock;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.CaveOneTransitionScript;
import Scripts.MapTransitions.ForestThreeTransitionScript;
import Scripts.RockPuzzleScripts.*;
import Tilesets.ForestCaveTileset;

public class ForestCaveMap extends Map {

    public ForestCaveMap() {
        super("forest_cave_map.txt", new ForestCaveTileset());
        this.playerStartPosition = getMapTile(28, 0).getLocation().addX(12); // middle of path at top
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(28, 0).getLocation().addX(12).subtractY(36)); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        //ROCK PUZZLE TRIGGERS
        triggers.add(new Trigger(192, 576, 192, 48, new RockPuzzleStartScript(), "hasStartedRockPuzzle"));
        triggers.add(new Trigger(192, 624, 5, 48, new RockPuzzleFirstRockScript(), "hasPushedFirstRock"));
        triggers.add(new Trigger(379, 624, 5, 48, new RockPuzzleSecondRockScript(), "hasPushedSecondRock"));
        triggers.add(new Trigger(192, 680, 192, 48, new RockPuzzleCompleteScript(), "hasCompletedRockPuzzle"));

        // ForestThree transition trigger
        triggers.add(new Trigger(1296, 0, 192, 12, new ForestThreeTransitionScript()));
        // CaveOne transition trigger
        triggers.add(new Trigger(1668, 2352, 12, 96, new CaveOneTransitionScript()));
        //rock puzzle complete trigger
        return triggers;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PushableRock pushableRock = new PushableRock(getMapTile(4, 13).getLocation());
        PushableRock pushableRock2 = new PushableRock(getMapTile(7, 13).getLocation());
        EnhancedRock enhancedRock = new EnhancedRock(getMapTile(5, 14).getLocation());
        EnhancedRock enhancedRock2 = new EnhancedRock(getMapTile(6, 14).getLocation());
        EnhancedRock enhancedRock3 = new EnhancedRock(getMapTile(7, 14).getLocation());
        EnhancedRock enhancedRock4 = new EnhancedRock(getMapTile(4, 14).getLocation());
        enhancedMapTiles.add(pushableRock);
        enhancedMapTiles.add(pushableRock2);
        enhancedMapTiles.add(enhancedRock);
        enhancedMapTiles.add(enhancedRock2);
        enhancedMapTiles.add(enhancedRock3);
        enhancedMapTiles.add(enhancedRock4);

        return enhancedMapTiles;
    }
    
}
