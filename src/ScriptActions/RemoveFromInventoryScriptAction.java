package ScriptActions;

import Engine.GamePanel;
import Level.ScriptState;

public class RemoveFromInventoryScriptAction extends ScriptAction {
    protected String item;

    public RemoveFromInventoryScriptAction() {};

    public RemoveFromInventoryScriptAction(String item) {
        this.item = item;
    }

    @Override
    public ScriptState execute() {
        GamePanel.removeInventoryItem(item);
        return ScriptState.COMPLETED;
    }
    
}
