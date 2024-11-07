package Screens.IceScreens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Maps.IceMaps.IceThreeMap;
import Players.Cat;
import Utils.Direction;

public class IceThreeScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected IceThreeScreenState iceThreeScreenState;
    protected FlagManager flagManager;

    public IceThreeScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("moveToIceTwo", false);
        flagManager.addFlag("moveToIceFour", false);

        // define/setup map
        map = new IceThreeMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        iceThreeScreenState = IceThreeScreenState.RUNNING;
        player.setFacingDirection(Direction.RIGHT); //TODO

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (iceThreeScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
        }

        if (map.getFlagManager().isFlagSet("moveToIceTwo")) {
            screenCoordinator.setGameState(GameState.ICE_TWO);
            map.getFlagManager().unsetFlag("moveToIceTwo");
        }
        else if (map.getFlagManager().isFlagSet("moveToIceFour")) {
            screenCoordinator.setGameState(GameState.ICE_FOUR);
            map.getFlagManager().unsetFlag("moveToIceFour");
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (iceThreeScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
        }
    }

    public IceThreeScreenState getIceThreeScreenState() {
        return iceThreeScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    // This enum represents the different states this screen can be in
    private enum IceThreeScreenState {
        RUNNING
    }
}