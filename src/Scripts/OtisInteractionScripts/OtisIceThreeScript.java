package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisIceThreeScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("*Otis is on edge about these signs that are placed \naround*");
            addText("*Does King Lodeon know we're coming?*");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredIceThree", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
