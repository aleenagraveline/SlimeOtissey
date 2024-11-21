package Scripts.IceTwoMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class OffIceScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new UnlockPlayerScriptAction());
        scriptActions.add(new ChangeFlagScriptAction("playerIsOffIce", true));
        scriptActions.add(new ChangeFlagScriptAction("playerIsOnIce", false));
        return scriptActions;
    }
}
