package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisIceOneScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("*Otis begins to trail behind slightly...*");
            addText("Alex: Come on Bud! We'll make it to the Kingdom \nin no time!");
            addText("Alex: It'll be okay, I'll keep you safe...");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredIceOne", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

