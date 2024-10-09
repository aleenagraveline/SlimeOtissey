package Maps;

import Level.Map;
import Scripts.SimpleTextScript;
import Scripts.MapTransitions.SpawnTransitionScript;
import Tilesets.CommonTileset;

public class TownhouseMap extends Map{

    public TownhouseMap() {
        super("townhouse_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(12, 2).getLocation().subtractX(12).subtractY(24); // Position below door in house
    }

    @Override
    public void loadScripts() {
        // door script
        getMapTile(12, 1).setInteractScript(new SpawnTransitionScript());

        // sign script
        getMapTile(5,2).setInteractScript(new SimpleTextScript("What a beautifully tiled home\nand carpeted entrance..."));
    }

}
