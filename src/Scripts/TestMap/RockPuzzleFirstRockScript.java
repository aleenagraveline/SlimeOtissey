package Scripts.TestMap;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class RockPuzzleFirstRockScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction() {{
            addText("Ugh...");
            addText("These rocks are so heavy!");
        }});

        scriptActions.add(new ChangeFlagScriptAction("hasPushedFirstRock", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

