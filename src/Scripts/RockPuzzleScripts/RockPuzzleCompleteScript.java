package Scripts.RockPuzzleScripts;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import GameObject.Frame;
import Level.*;
import ScriptActions.*;
import Scripts.MapTransitions.TownhouseTransitionScript;
import Utils.Direction;
import Utils.Point;
import Utils.Visibility;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class RockPuzzleCompleteScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction("Rock Puzzle Complete!"));

        // scriptActions.add(new ConditionalScriptAction() {{
        //     addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
        //         addRequirement(new FlagRequirement("hasTalkedToWalrus", true));
        //         addRequirement(new FlagRequirement("hasTalkedToDinosaur", false));

        //         addScriptAction(new WaitScriptAction(70));
        //         addScriptAction(new NPCFacePlayerScriptAction());
        //         addScriptAction(new TextboxScriptAction () {{
        //             addText("Alex...Go away!");
        //         }});
        //         addScriptAction(new NPCStandScriptAction(Direction.RIGHT));

        //         addScriptAction(new NPCWalkScriptAction(Direction.DOWN, 36, 2));
        //         addScriptAction(new NPCWalkScriptAction(Direction.RIGHT, 196, 2));

        //         addScriptAction(new ScriptAction() {
        //             @Override
        //             public ScriptState execute() {
        //                 // change door to the open door map tile
        //                 Frame openDoorFrame = new FrameBuilder(map.getTileset().getSubImage(4, 4), 0)
        //                     .withScale(map.getTileset().getTileScale())
        //                     .build();

        //                 Point location = map.getMapTile(17, 4).getLocation();

        //                 MapTile mapTile = new MapTileBuilder(openDoorFrame)
        //                     .withTileType(TileType.NOT_PASSABLE)
        //                     .build(location.x, location.y);

        //                 map.setMapTile(17, 4, mapTile);
        //                 return ScriptState.COMPLETED;
        //             }
        //         });

        //         addScriptAction(new NPCWalkScriptAction(Direction.UP, 50, 2));
        //         addScriptAction(new NPCChangeVisibilityScriptAction(Visibility.HIDDEN));

        //         addScriptAction(new ScriptAction() {
        //             @Override
        //             public ScriptState execute() {
        //                 // change door back to the closed door map tile
        //                 Frame doorFrame = new FrameBuilder(map.getTileset().getSubImage(4, 3), 0)
        //                     .withScale(map.getTileset().getTileScale())
        //                     .build();

        //                 Point location = map.getMapTile(17, 4).getLocation();

        //                 MapTile mapTile = new MapTileBuilder(doorFrame)
        //                     .withTileType(TileType.NOT_PASSABLE)
        //                     .build(location.x, location.y);
        //                 mapTile.setInteractScript(new TownhouseTransitionScript()); // Enable door to be interacted with after Otis goes inside
        //                 map.setMapTile(17, 4, mapTile);
        //                 return ScriptState.COMPLETED;
        //             }
        //         });

        //         addScriptAction(new ChangeFlagScriptAction("hasTalkedToDinosaur", true));
        //     }});
        // }});


         scriptActions.add(new UnlockPlayerScriptAction());
         return scriptActions;
    }
}