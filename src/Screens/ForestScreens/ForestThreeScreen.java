package Screens.ForestScreens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Maps.ForestMaps.ForestThreeMap;
import Players.Cat;
import Utils.Direction;

public class ForestThreeScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected ForestThreeScreenState forestThreeScreenState;
    protected FlagManager flagManager;

    public ForestThreeScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("moveToForestTwo", false);
        flagManager.addFlag("moveToForestCave", false);
        flagManager.addFlag("hasEnteredForestThree", false);
        flagManager.addFlag("isCountingSteps", false);
        flagManager.addFlag("isNotCountingSteps", true);

        // define/setup map
        map = new ForestThreeMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        forestThreeScreenState = ForestThreeScreenState.RUNNING;
        player.setFacingDirection(Direction.LEFT);

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
        switch (forestThreeScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
        }

        if (map.getFlagManager().isFlagSet("moveToForestTwo")) {
            screenCoordinator.setGameState(GameState.FOREST_TWO);
            map.getFlagManager().unsetFlag("moveToForestTwo");
        }
        else if (map.getFlagManager().isFlagSet("moveToForestCave")) {
            screenCoordinator.setGameState(GameState.FOREST_CAVE);
            map.getFlagManager().unsetFlag("moveToForestCave");
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (forestThreeScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
        }
    }

    public ForestThreeScreenState getForestThreeScreenState() {
        return forestThreeScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    // This enum represents the different states this screen can be in
    private enum ForestThreeScreenState {
        RUNNING
    }
}