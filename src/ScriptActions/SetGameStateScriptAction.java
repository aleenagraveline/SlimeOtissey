package ScriptActions;

import Game.GameState;
import Game.ScreenCoordinator;
import Level.ScriptState;

public class SetGameStateScriptAction extends ScriptAction {
    private ScreenCoordinator screenCoordinator;
    private GameState gameState;

    public SetGameStateScriptAction(ScreenCoordinator screenCoordinator, GameState gameState) {
        this.screenCoordinator = screenCoordinator;
        this.gameState = gameState;
    }

    @Override
    public ScriptState execute() {
        this.screenCoordinator.setGameState(this.gameState);
        return ScriptState.COMPLETED;
    } 
}
