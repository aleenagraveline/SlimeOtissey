package Scripts.TestMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.CustomRequirement;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.FlagRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

// script for talking to tree with hole in it
// checkout the documentation website for a detailed guide on how this script works
public class TreeScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToDinosaur", true));
                addRequirement(new FlagRequirement("hasFoundKey", false));
                addRequirement(new FlagRequirement("keyIsInTree", true));
                addRequirement(new CustomRequirement() {

                    @Override
                    public boolean isRequirementMet() {
                        // ensures player is directly underneath tree trunk tile
                        // this prevents the script from working if the player tries to interact with it from the side

                        // if player is not below tree trunk tile, player location is not valid and this conditional script will not be reached
                        if (player.getBounds().getY1() <= entity.getBounds().getY2()) {
                            return false;
                        }

                        // if code gets here, it means player is below tree trunk tile and player location is valid, so this conditional script will continue
                        return true;
                    }
                });
                addScriptAction(new TextboxScriptAction() {{
                    addText("There's something inside the tree!\nLet me try and get it...");
                    addText("Uh oh. It seems I activated some dangerous device!");
                    addText("It seems there's something etched into the walls,\nbut it makes no sense.");
                    addText("And a countdown just started!");
                    addText("*Otis backs away and starts to tremble in fear*");
                    addText("Alex: Don't worry Otis, we'll be okay!");
                    addText("*Otis looks at you skeptically...better figure \nthis out!*");
                }});
                addScriptAction(new ChangeFlagScriptAction("isInWaitingPuzzle", true));
                addScriptAction(new TextboxScriptAction() {{
                    addText("...");
                    addText("I found a key inside of the box!\nYippee!");
                    addText("Maybe I can use the key to break into Dino's Mansion! \n(Press I to check your inventory.)");
                    addText("*Otis jumps on the key*");
                    addText("Alex: Otis! Give me the key back!");
                    addText("*Otis seems to...laugh? He's enjoying this!*");
                    addText("Alex: Otis...we can break into a house with this!");
                    addText("*Otis seems to hesitate...but he drops the key. \nThen he sticks his tongue out at you.*");

                }});

                addScriptAction(new ChangeFlagScriptAction("hasFoundKey", true));
                addScriptAction(new ChangeFlagScriptAction("keyIsInTree", false));
            }});


        }});
       
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

