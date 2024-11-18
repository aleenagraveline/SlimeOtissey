package Scripts.TestMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

public class WalrusSmartScript extends Script {
    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCLockScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Wow, good job solving that puzzle!");
            addText("You must be really smart...\nSo then why don't you have friends?");
            addText("*Otis seems slightly annoyed by the mocking \nquestion*");
            addText("*It almost seems like he wants to defend you?*");
            addText("*Maybe Otis is starting to like you after \nall...*");
            addText("Alex: Well, I've got my little slime friend here.");
            addText("*Otis is now glaring at you*");
            addText("*Okay...maybe a little too fast...noted for next time.*");
        }});

        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
