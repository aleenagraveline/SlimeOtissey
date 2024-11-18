package Scripts.TestMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;


// trigger script at beginning of game to set that heavy emotional plot
// checkout the documentation website for a detailed guide on how this script works
public class OtisHouseScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Alex?!");
            addText("How did you get into my castle?!");
            addText("Why is a slime following you??!!");
            addText("The slimes are mine!");
            addText("*Otis scurries slides behind your feet, trying to \nhide himself...Aww...");
            addText("Alex: Well this one is mine, back off!");
            addText("Lodeon: You better watch what you say to me...");
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
