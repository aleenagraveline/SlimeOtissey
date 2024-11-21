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

public class Crystal7Script extends Script{

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
                
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        // change blue crystal to the pink crystal
                        Frame pinkCrystalFrame = new FrameBuilder(map.getTileset().getSubImage(1, 2), 0)
                            .withScale(map.getTileset().getTileScale())
                            .build();

                        Point location = map.getMapTile(10, 4).getLocation();

                        MapTile mapTile = new MapTileBuilder(pinkCrystalFrame)
                            .withTileType(TileType.NOT_PASSABLE)
                            .build(location.x, location.y);

                        map.setMapTile(10, 4, mapTile);
                        CaveThreeScreen.changeCounter += 1;
                        //scriptActions.add(new ChangeFlagScriptAction("crystal7Flag", true));
                        return ScriptState.COMPLETED;
                    }
                });

            }});
        }});
                
        return scriptActions;
    }
    
}
