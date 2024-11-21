package Screens.IceScreens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Maps.IceMaps.IceOneMap;
import Players.Cat;
import Utils.Direction;

public class IceOneScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected IceOneScreenState iceOneScreenState;
    protected FlagManager flagManager;

    public IceOneScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("moveToCaveIce", false);
        flagManager.addFlag("moveToIceTwo", false);
        flagManager.addFlag("hasEnteredIceOne", false);

        // define/setup map
        map = new IceOneMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        iceOneScreenState = IceOneScreenState.RUNNING;
        player.setFacingDirection(Direction.UP);

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (iceOneScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
        }

        if (map.getFlagManager().isFlagSet("moveToCaveIce")) {
            screenCoordinator.setGameState(GameState.CAVE_ICE);
            map.getFlagManager().unsetFlag("moveToCaveIce");
        }
        else if (map.getFlagManager().isFlagSet("moveToIceTwo")) {
            screenCoordinator.setGameState(GameState.ICE_TWO);
            map.getFlagManager().unsetFlag("moveToIceTwo");
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (iceOneScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
        }
    }

    public IceOneScreenState getIceOneScreenState() {
        return iceOneScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    // This enum represents the different states this screen can be in
    private enum IceOneScreenState {
        RUNNING
    }

    @Override
    public Map getMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMap'");
    }
}