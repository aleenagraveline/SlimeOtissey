package Scripts;

import java.util.ArrayList;

import Game.GameState;
import Game.ScreenCoordinator;
import Level.Script;
import ScriptActions.*;

// Script to stop counting steps for random battles
public class EnterRandomBattleScript extends Script {
    private ScreenCoordinator screenCoordinator;

    public EnterRandomBattleScript (ScreenCoordinator screenCoordinator) {
        super();
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction("!"));
        scriptActions.add(new SetGameStateScriptAction(this.screenCoordinator, GameState.RANDOM_BATTLE));
        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}