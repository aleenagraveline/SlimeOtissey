package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisCaveThreeScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("*Otis seems particularly interested in the crystals \nin this section of the cave*");
            addText("*Seems like he really wants to check them out.*");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredCaveThree", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}