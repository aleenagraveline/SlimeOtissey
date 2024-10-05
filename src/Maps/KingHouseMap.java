package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Utils.Point;

public class KingHouseMap extends Map{

    public KingHouseMap() {
        super("king_house_map.txt", new CommonTileset());
        this.playerStartPosition = new Point(12, 2); // position below door in house
    }

}
