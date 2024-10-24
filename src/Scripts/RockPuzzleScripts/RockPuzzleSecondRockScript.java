package Scripts.RockPuzzleScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class RockPuzzleSecondRockScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction("I hate rocks..."));

        scriptActions.add(new ChangeFlagScriptAction("hasPushedSecondRock", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
