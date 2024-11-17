package Scripts.CrystalScripts;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import GameObject.Frame;
import Level.MapTile;
import Level.Script;
import Level.ScriptState;
import Level.TileType;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.ScriptAction;
import Utils.Point;

public class Crystal2Script extends Script{

    public static boolean crystal2Script = false;

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
                
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        // change pink shards to the blue shards
                        Frame blueShardsFrame = new FrameBuilder(map.getTileset().getSubImage(1, 1), 0)
                            .withScale(map.getTileset().getTileScale())
                            .build();

                        Point location = map.getMapTile(12, 14).getLocation();

                        MapTile mapTile = new MapTileBuilder(blueShardsFrame)
                            .withTileType(TileType.NOT_PASSABLE)
                            .build(location.x, location.y);

                        map.setMapTile(12, 14, mapTile);
                        crystal2Script = true;
                        return ScriptState.COMPLETED;
                    }
                });

            }});
        }});
                
        return scriptActions;
    }
    
}

