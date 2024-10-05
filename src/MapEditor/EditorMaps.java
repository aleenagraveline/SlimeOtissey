package MapEditor;

import Level.Map;
import Maps.TestMap;
import Maps.TitleScreenMap;
import Maps.BugFightMap;
import Maps.ForestOneMap;
import Maps.KingHouseMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("BugFightMap");
            add("KingHouseMap");
            add("ForestOneMap");
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
            case "KingHouseMap":
                return new KingHouseMap();
            case "ForestOneMap":
                return new ForestOneMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
