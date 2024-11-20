package Scripts.IceFiveMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ChangeTileScriptAction;
import ScriptActions.GainFriendshipScriptAction;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class IcePuzzleSolvedScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction("Phew, I think that's all of the ice."));
        scriptActions.add(new ChangeFlagScriptAction("icePuzzleSolved", true));

        for (int y = 3; y < 12; y++) {
            scriptActions.add(new ChangeTileScriptAction(3, y, map.getTileset().getTile(10).build(144, y*48)));
        }

        scriptActions.add(new TextboxScriptAction("Woah, the ice froze back over!\n(Friendship with Otis increased!)"));
        scriptActions.add(new GainFriendshipScriptAction(4, 6));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}