package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisIceFiveScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Alex: I think our walk is almost over.");
            addText("*Otis moves slightly closer, like he wants comfort*");
            addText("*I wonder how much friendship we've gained since the \nvillage*");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredIceFive", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

