package Scripts;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

// Script to start counting steps for random battles
public class ActivateRandomStepCounterScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new ChangeFlagScriptAction("isCountingSteps", true));
        scriptActions.add(new ChangeFlagScriptAction("isNotCountingSteps", false));
        scriptActions.add(new SetShouldIncreaseRandomStepCounterScriptAction(true));
        return scriptActions;
    }
}
