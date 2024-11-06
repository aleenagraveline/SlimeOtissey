package Scripts.RockPuzzleScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class RockPuzzleStartScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("It appears those rocks ahead are blocking off a cave...");
            addText("I wonder if I can get past the rocks.");
            addText("*Otis appears to perk up at the suggestion*");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasStartedRockPuzzle", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
