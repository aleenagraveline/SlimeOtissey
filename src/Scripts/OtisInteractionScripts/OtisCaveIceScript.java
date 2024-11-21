package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisCaveIceScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("*It's getting hard to see Otis in here...*");
            addText("Alex: Otis, follow the sound of my footsteps");
            addText("*There's no response, but you're pretty sure that Otis \nheard you.");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredCaveIce", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
