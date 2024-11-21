package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisIceTwoScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("*Otis shivers...the ice must be making him cold*");
            addText("*I wonder if live slimes can freeze?*");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredIceTwo", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

