package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisForestThreeScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("*Otis begins to look a little tired...*");
            addText("*I wonder if he's hungry? Maybe I'll \n try to find him food*");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredForestThree", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
