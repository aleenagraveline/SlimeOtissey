package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Utils.Point;

public class ForestOneMap extends Map{

    public ForestOneMap() {
        super("forest_one_map.txt", new CommonTileset());
        this.playerStartPosition = new Point(0, 9); // middle of path on left
    }

}
