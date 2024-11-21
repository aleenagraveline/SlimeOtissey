package Scripts;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

// Script to stop counting steps for random battles
public class DeactivateRandomStepCounterScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new ChangeFlagScriptAction("isCountingSteps", false));
        scriptActions.add(new ChangeFlagScriptAction("isNotCountingSteps", true));
        scriptActions.add(new SetShouldIncreaseRandomStepCounterScriptAction(false));
        return scriptActions;
    }
}