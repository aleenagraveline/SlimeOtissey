package Scripts.MapTransitions;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ScriptAction;

public class ForestCaveTransitionScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new ChangeFlagScriptAction("moveToForestCave", true));
        return scriptActions;
    }
    
}