package Screens.CaveScreens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Maps.CaveMaps.CaveTwoMap;
import Players.Cat;
import Utils.Direction;

public class CaveTwoScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected CaveTwoScreenState caveTwoScreenState;
    protected FlagManager flagManager;

    public CaveTwoScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("moveToCaveOne", false);
        flagManager.addFlag("moveToCaveThree", false);
        flagManager.addFlag("hasEnteredCaveTwo", false);

        // define/setup map
        map = new CaveTwoMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        caveTwoScreenState = CaveTwoScreenState.RUNNING;
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
        switch (caveTwoScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
        }

        if (map.getFlagManager().isFlagSet("moveToCaveOne")) {
            screenCoordinator.setGameState(GameState.CAVE_ONE);
            map.getFlagManager().unsetFlag("moveToCaveOne");
        }
        else if (map.getFlagManager().isFlagSet("moveToCaveThree")) {
            screenCoordinator.setGameState(GameState.CAVE_THREE);
            map.getFlagManager().unsetFlag("moveToCaveThree");
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (caveTwoScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
        }
    }

    public CaveTwoScreenState getCaveTwoScreenState() {
        return caveTwoScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    // This enum represents the different states this screen can be in
    private enum CaveTwoScreenState {
        RUNNING
    }
}