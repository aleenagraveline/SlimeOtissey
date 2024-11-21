package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisForestCaveScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("*Otis seems to hesitate entering this section of the woods...*");
            addText("*Maybe he senses something different ahead?*");
            addText("Alex: Come on, buddy!");
            addText("*Otis looks skeptical...but he follows.*");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredForestCave", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
