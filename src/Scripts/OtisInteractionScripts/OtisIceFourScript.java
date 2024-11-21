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
            addText("Alex: Now of course snowland has to be the largest \none...");
            addText("*Otis bounces...is he laughing? That's...unexpected...*");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredIceFour", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
