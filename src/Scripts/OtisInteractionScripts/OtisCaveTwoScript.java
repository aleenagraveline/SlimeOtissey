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
            addText("Otis seems very nervous to leave the village...");
            addText("I don't think he wants to go back to where he \ncame from.");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("scaredOtis", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
