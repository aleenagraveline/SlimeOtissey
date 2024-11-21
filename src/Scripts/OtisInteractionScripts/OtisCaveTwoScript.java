package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisCaveTwoScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Alex: I wonder how big this cave is...*");
            addText("*Otis seems to want to keep moving*");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredCaveTwo", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
