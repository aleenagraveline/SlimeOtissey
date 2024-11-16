package Scripts.IceTwoMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ScriptAction;

public class onIceScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new ChangeFlagScriptAction("playerIsOnIce", true));
        scriptActions.add(new ChangeFlagScriptAction("playerIsOffIce", false));
        return scriptActions;
    }
}