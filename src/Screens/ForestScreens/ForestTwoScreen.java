package Screens.ForestScreens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Maps.ForestMaps.ForestTwoMap;
import Players.Cat;
import Utils.Direction;

public class ForestTwoScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected ForestTwoScreenState forestTwoScreenState;
    protected FlagManager flagManager;

    public ForestTwoScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("moveToForestOne", false);
        flagManager.addFlag("moveToForestThree", false);

        // define/setup map
        map = new ForestTwoMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        forestTwoScreenState = ForestTwoScreenState.RUNNING;
        player.setFacingDirection(Direction.RIGHT);

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();
    }

    public Map getMap() {
        return map;
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (forestTwoScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                this.screenCoordinator.increaseRandomBattleStepCounter();
                break;
        }

        if (map.getFlagManager().isFlagSet("moveToForestOne")) {
            screenCoordinator.setGameState(GameState.FOREST_ONE);
            map.getFlagManager().unsetFlag("moveToForestOne");
        }
        else if (map.getFlagManager().isFlagSet("moveToForestThree")) {
            screenCoordinator.setGameState(GameState.FOREST_THREE);
            map.getFlagManager().unsetFlag("moveToForestThree");
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (forestTwoScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
        }
    }

    public ForestTwoScreenState getForestTwoScreenState() {
        return forestTwoScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    // This enum represents the different states this screen can be in
    private enum ForestTwoScreenState {
        RUNNING
    }
}