package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisForestCaveScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Alex has never been this far out of the Village");
            addText("But Otis seems to know where he's going");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredForestCave", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
