package Scripts.MapTransitions;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.FlagRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.RemoveFromInventoryScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import ScriptActions.WaitScriptAction;

public class TownhouseTransitionScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            // If player is not using the key and has not used the key, produce textbox hinting to key
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("usingKey", false));
                addRequirement(new FlagRequirement("usedKey", false));

                addScriptAction(new TextboxScriptAction() {{
                    addText("It's locked.");
                    addText("Maybe there's a key around here...");
                }});
            }});

            // If player is using the key, produce key use sequence
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("usingKey", true));

                // Text sequence
                addScriptAction(new TextboxScriptAction("Alex used the key!"));
                addScriptAction(new WaitScriptAction(70));
                addScriptAction(new TextboxScriptAction("(He's a little intimidated,\nso his hands are kinda slippery.)"));
                addScriptAction(new WaitScriptAction(70));
                addScriptAction(new TextboxScriptAction() {{
                    addText(".");
                    addText("..");
                    addText("...");
                }});
                addScriptAction(new WaitScriptAction(70));
                addScriptAction(new TextboxScriptAction("It worked!"));

                // Set up next conditions
                addScriptAction(new ChangeFlagScriptAction("usedKey", true));
                addScriptAction(new RemoveFromInventoryScriptAction("Key"));
                addScriptAction(new ChangeFlagScriptAction("usingKey", false));
            }});

            // If player has used the key already, enter the house
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("usedKey", true));

                addScriptAction(new ChangeFlagScriptAction("moveToTownhouse", true));
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
    
}
