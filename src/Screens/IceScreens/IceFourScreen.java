package Screens.IceScreens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Maps.IceMaps.IceFourMap;
import Players.Cat;
import Utils.Direction;

public class IceFourScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected IceFourScreenState iceFourScreenState;
    protected FlagManager flagManager;

    public IceFourScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("moveToIceThree", false);
        flagManager.addFlag("moveToIceFive", false);

        // define/setup map
        map = new IceFourMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        iceFourScreenState = IceFourScreenState.RUNNING;
        player.setFacingDirection(Direction.DOWN); //TODO

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (iceFourScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
        }

        if (map.getFlagManager().isFlagSet("moveToIceThree")) {
            screenCoordinator.setGameState(GameState.ICE_THREE);
            map.getFlagManager().unsetFlag("moveToIceThree");
        }
        else if (map.getFlagManager().isFlagSet("moveToIceFive")) {
            screenCoordinator.setGameState(GameState.ICE_FIVE);
            map.getFlagManager().unsetFlag("moveToIceFive");
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (iceFourScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
        }
    }

    public IceFourScreenState getIceFourScreenState() {
        return iceFourScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    // This enum represents the different states this screen can be in
    private enum IceFourScreenState {
        RUNNING
    }

    @Override
    public Map getMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMap'");
    }
}