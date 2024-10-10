package Scripts.TestMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

// script for talking to walrus npc
// checkout the documentation website for a detailed guide on how this script works
public class WalrusScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToWalrus", false));
                addScriptAction(new TextboxScriptAction() {{
                    addText("Hi Alex!");
                }});
                addScriptAction(new ChangeFlagScriptAction("hasTalkedToWalrus", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToWalrus", true));
                addRequirement(new FlagRequirement("playedMemPuzzle", false));
                addScriptAction(new TextboxScriptAction("Oh hello again, Alex!"));
                addScriptAction(new TextboxScriptAction("I just found this memory puzzle,\nwhy don't you give it a try!"));
                addScriptAction(new TextboxScriptAction("You see a note on the puzzle: \"Use A and D to change\nselection, then space to confirm!\""));
                addScriptAction(new ChangeFlagScriptAction("isInMemPuzzle", true));
                addScriptAction(new ChangeFlagScriptAction("playedMemPuzzle", true));
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
