package Scripts.CrystalScripts;

import java.util.ArrayList;

import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class CrystalPuzzleCompleteScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("*Otis seems very happy that all of the \ncrystals are organized by color*");
            addText("I wonder what his favorite color is?");
            addText("But hey he seems happy *Otis jumps around*");
        }});
        
        scriptActions.add(new ChangeFlagScriptAction("hasCompletedCrystalPuzzle", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

