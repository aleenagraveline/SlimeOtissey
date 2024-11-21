package Scripts.OtisInteractionScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class OtisForestTwoScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("*Otis seems to be growing incredibly stressed...*");
            addText("*He must not like the forest very much...*");
            addText("Alex: At least I have my weapons to fight off \nthe bugs...");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasEnteredForestTwo", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}