package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class FoodScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction() {{
            addText("*Otis curiously looks at the apple...*");
            addText("*He grabs it and eats it!*");
            addText("*It looks like he enjoyed it?*");
        }});

        scriptActions.add(new ChangeFlagScriptAction("hasEatenFood", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
    
}
