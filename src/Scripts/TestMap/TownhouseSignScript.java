package Scripts.TestMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class TownhouseSignScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction() {{
            addText("Kings Monthly October 2024\nInterior Design Contest Winner: Dino!");
            addText("Judge's Comments: \"The artistic minimalism is truly \ninspiring and I love the grass patches\"");
        }});
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
    
}
