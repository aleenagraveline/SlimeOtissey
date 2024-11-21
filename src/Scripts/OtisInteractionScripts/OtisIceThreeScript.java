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
            addText("*Otis seems to have livened up his steps after \nthe sliding adventure*");
            addText("*It was pretty fun, maybe we can do it again soon?*");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredIceThree", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
