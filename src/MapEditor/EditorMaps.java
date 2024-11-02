package MapEditor;

import Level.Map;
import Maps.*;
import Maps.CaveMaps.CaveOneMap;
import Maps.CaveMaps.CaveThreeMap;
import Maps.CaveMaps.CaveTwoMap;
import Maps.ForestMaps.ForestOneMap;
import Maps.ForestMaps.ForestThreeMap;
import Maps.ForestMaps.ForestTwoMap;
import Maps.TransitionMaps.ForestCaveMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("BugFightMap");
            add("TownhouseMap");
            add("ForestOneMap");
            add("ForestTwoMap");
            add("ForestThreeMap");
            add("ForestCaveMap");
            add("CaveOneMap");
            add("CaveTwoMap");
            add("CaveThreeMap");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "BugFightMap":
                return new BugFightMap();
            case "TownhouseMap":
                return new TownhouseMap();
            case "ForestOneMap":
                return new ForestOneMap();
            case "ForestTwoMap":
                return new ForestTwoMap();
            case "ForestThreeMap":
                return new ForestThreeMap();
            case "ForestCaveMap":
                return new ForestCaveMap();
            case "CaveOneMap":
                return new CaveOneMap();
            case "CaveTwoMap":
                return new CaveTwoMap();
            case "CaveThreeMap":
                return new CaveThreeMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
