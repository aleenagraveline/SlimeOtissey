package Scripts.TestMap;

import java.util.ArrayList;
import Level.*;
import ScriptActions.*;

public class FoodScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasEatenFood", false));
                scriptActions.add(new LockPlayerScriptAction());
                scriptActions.add(new TextboxScriptAction() {{
                    addText("*Otis curiously looks at the apple...*");
                    addText("*He grabs it and eats it!*");
                    addText("*It looks like he enjoyed it?*");
                }});
            }});
        }});

        scriptActions.add(new ChangeFlagScriptAction("hasEatenFood", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
    
}
