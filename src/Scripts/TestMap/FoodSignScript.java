package Scripts.TestMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class FoodSignScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction() {{
            addText("Game Tip: Food");
            addText("Here lies an apple. Maybe Otis will enjoy it?");
            addText("Food is a good way to increase your friendship with\nOtis");
            addText("The amount of friendship points you gain is random\n(1-3 possible)");
            addText("I've heard food is also a great way to regenerate \nyour health!");
            addText("You can regerate 1-5 health from eating an apple.");
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
    
}
