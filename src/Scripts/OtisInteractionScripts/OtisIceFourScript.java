package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisIceFourScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Alex: Hey Otis?");
            addText("Alex: How many slimes has the king experimented on?");
            addText("*Otis looks sad... That answers my question*");
            addText("Alex: I see...");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredIceFour", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
