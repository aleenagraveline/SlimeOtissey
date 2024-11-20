package Scripts.CrystalScripts;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import GameObject.Frame;
import Level.MapTile;
import Level.Script;
import Level.ScriptState;
import Level.TileType;
import Screens.CaveScreens.CaveThreeScreen;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.ScriptAction;
import Utils.Point;

public class Crystal1Script extends Script{

    //public static boolean crystal1Script = false;

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

                        Point location = map.getMapTile(8, 14).getLocation();

                        MapTile mapTile = new MapTileBuilder(blueShardsFrame)
                            .withTileType(TileType.NOT_PASSABLE)
                            .build(location.x, location.y);

                        map.setMapTile(8, 14, mapTile);
                        CaveThreeScreen.changeCounter += 1;
                        //scriptActions.add(new ChangeFlagScriptAction("crystal1Flag", true));
                        return ScriptState.COMPLETED;
                    }
                });

            }});
        }});
                
        return scriptActions;
    }
    
}
