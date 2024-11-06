package Scripts.RockPuzzleScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class RockPuzzleCompleteScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("*Otis stops, seeming to inspect something where one \n of the rocks was.*");
            addText("Looks like a key! (And is Otis more friendly now?)");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasCompletedRockPuzzle", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

