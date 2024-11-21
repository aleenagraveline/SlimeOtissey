package Scripts;

import java.util.ArrayList;

import Level.MapTile;
import Level.Script;
import ScriptActions.*;


// simple reusable script that replaces a tile in the map
public class SimpleTileChangeScript extends Script {
    private int x;
    private int y;
    private MapTile newTile;

    public SimpleTileChangeScript(int x, int y, MapTile newTile) {
        super();
        this.x = x;
        this.y = y;
        this.newTile = newTile;
    }
    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new ChangeTileScriptAction(x, y, newTile));
        return scriptActions;
    }
}
