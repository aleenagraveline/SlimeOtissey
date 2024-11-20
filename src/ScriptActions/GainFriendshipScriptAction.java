package ScriptActions;

import Level.Player;
import Level.ScriptState;

public class GainFriendshipScriptAction extends ScriptAction {
    // Store max and min for friendship to be gained
    private int minimum;
    private int maximum;

    public GainFriendshipScriptAction(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    @Override
    public ScriptState execute() {
        Player.gainFriendshipPoints(minimum, maximum);
        return ScriptState.COMPLETED;
    } 
}
