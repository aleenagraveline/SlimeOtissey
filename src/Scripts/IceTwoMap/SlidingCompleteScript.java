package Scripts.IceTwoMap;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class SlidingCompleteScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("*Otis hops in delight, having enjoyed sliding around\non the ice.*");
            addText("Thought it seems he had a little trouble keeping up");
            addText("(Friendship with Otis increased!)");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasCompletedSlidingPuzzle", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

