package ScriptActions;

import Level.Player;
import Level.ScriptState;

public class SetShouldIncreaseRandomStepCounterScriptAction extends ScriptAction {
    private boolean newValue;

    public SetShouldIncreaseRandomStepCounterScriptAction(boolean newValue) {
        this.newValue = newValue;
    }

    @Override
    public ScriptState execute() {
        Player.shouldIncreaseRandomStepCounter = newValue;
        return ScriptState.COMPLETED;
    } 
}
