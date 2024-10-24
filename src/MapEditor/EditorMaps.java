package MapEditor;

import Level.Map;
import Maps.*;

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
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
