package Screens;

import Engine.GamePanel;
import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Maps.ForestCaveMap;
import Players.Cat;
import Utils.Direction;

public class ForestCaveScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected ForestCaveScreenState forestCaveScreenState;
    protected FlagManager flagManager;

    public ForestCaveScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("moveToForestThree", false);
        flagManager.addFlag("moveToCaveOne", false);
        flagManager.addFlag("hasPushedFirstRock", false);
        flagManager.addFlag("hasPushedSecondRock", false);
        flagManager.addFlag("hasStartedRockPuzzle", false);
        flagManager.addFlag("hasCompletedRockPuzzle", false);
        flagManager.addFlag("hasCollectedKey", false);

        // define/setup map
        map = new ForestCaveMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        forestCaveScreenState = ForestCaveScreenState.RUNNING;
        player.setFacingDirection(Direction.DOWN);

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (forestCaveScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
        }

        if(map.getFlagManager().isFlagSet("hasCompletedRockPuzzle") && !(map.getFlagManager().isFlagSet("hasCollectedKey"))) {
            GamePanel.addToInventory("Key");
            Player.gainFriendshipPoints(1);
            map.getFlagManager().setFlag("hasCollectedKey");
        }

        if (map.getFlagManager().isFlagSet("moveToForestThree")) {
            screenCoordinator.setGameState(GameState.FOREST_THREE);
            map.getFlagManager().unsetFlag("moveToForestThree");
        }
        else if (map.getFlagManager().isFlagSet("moveToCaveOne")) {
            screenCoordinator.setGameState(GameState.CAVE_ONE);
            map.getFlagManager().unsetFlag("moveToCaveOne");
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (forestCaveScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
        }
    }

    public ForestCaveScreenState getForestCaveScreenState() {
        return forestCaveScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    // This enum represents the different states this screen can be in
    private enum ForestCaveScreenState {
        RUNNING
    }
}