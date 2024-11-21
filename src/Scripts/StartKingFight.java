package Scripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

// script for talking to bug npc
// checkout the documentation website for a detailed guide on how this script works
public class StartKingFight extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Well then... You've finally arrived.");
            addText("Are you prepared for this?", new String[] { "Yes", "No" });
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 0;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{ addText("Very well. Let us end this."); }});
                addScriptAction(new ChangeFlagScriptAction("isInKingBattle", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });
                
                addScriptAction(new TextboxScriptAction("Too bad."));
                addScriptAction(new ChangeFlagScriptAction("isInKingBattle", true));
            }});
        }});
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
