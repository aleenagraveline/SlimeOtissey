package Scripts.RockPuzzleScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class RockPuzzleFirstRockScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Ugh...");
            addText("These rocks are so heavy!");
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasPushedSecondRock", true));
                
                addScriptAction(new TextboxScriptAction() {{
                    addText("A few moments later...");
                }});
                addScriptAction(new WaitScriptAction(70));

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        map.getEnhancedMapTiles().get(2).setIsHidden(true);
                        map.getEnhancedMapTiles().get(3).setIsHidden(true);
                        map.getEnhancedMapTiles().get(4).setIsHidden(true);
                        map.getEnhancedMapTiles().get(5).setIsHidden(true);
                        return ScriptState.COMPLETED;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("All the rocks appear to be gone...");
                    addText("Time to see what they were blocking off!");
                    addText("*Otis does a little spin, he's clearly excited*");
                }});
            }});
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasPushedFirstRock", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

