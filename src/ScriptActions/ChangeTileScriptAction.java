package ScriptActions;

import Level.ScriptState;
import Level.MapTile;

public class ChangeTileScriptAction extends ScriptAction {
    protected int x;
    protected int y;
    protected MapTile newTile;

    public ChangeTileScriptAction(int x, int y, MapTile newTile) {
        this.x = x;
        this.y = y;
        this.newTile = newTile;
    }

    @Override
    public ScriptState execute() {
        this.map.setMapTile(x, y, newTile);
        return ScriptState.COMPLETED;
    }
}
