package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Maps.ForestOneMap;
import Players.Cat;
import Utils.Direction;

public class ForestOneScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected ForestOneScreenState forestOneScreenState;
    protected FlagManager flagManager;

    public ForestOneScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("moveToSpawn", false);

        // define/setup map
        map = new ForestOneMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        forestOneScreenState = ForestOneScreenState.RUNNING;
        player.setFacingDirection(Direction.RIGHT);

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (forestOneScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                this.screenCoordinator.increaseRandomBattleStepCounter();
                break;
        }

        if (map.getFlagManager().isFlagSet("moveToSpawn")) {
            screenCoordinator.setGameState(GameState.SPAWN);
            map.getFlagManager().unsetFlag("moveToSpawn");
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (forestOneScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
        }
    }

    public ForestOneScreenState getForestOneScreenState() {
        return forestOneScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    // This enum represents the different states this screen can be in
    private enum ForestOneScreenState {
        RUNNING
    }
}