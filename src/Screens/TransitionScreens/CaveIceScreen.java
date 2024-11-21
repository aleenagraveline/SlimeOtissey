package Screens.TransitionScreens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Maps.TransitionMaps.CaveIceMap;
import Players.Cat;
import Utils.Direction;

public class CaveIceScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected CaveIceScreenState caveIceScreenState;
    protected FlagManager flagManager;

    public CaveIceScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("moveToCaveThree", false);
        flagManager.addFlag("moveToIceOne", false);
        flagManager.addFlag("hasEnteredCaveIce", false);

        // define/setup map
        map = new CaveIceMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        caveIceScreenState = CaveIceScreenState.RUNNING;
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
        switch (caveIceScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
        }

        if (map.getFlagManager().isFlagSet("moveToCaveThree")) {
            screenCoordinator.setGameState(GameState.CAVE_THREE);
            map.getFlagManager().unsetFlag("moveToCaveThree");
        }
        else if (map.getFlagManager().isFlagSet("moveToIceOne")) {
            screenCoordinator.setGameState(GameState.ICE_ONE);
            map.getFlagManager().unsetFlag("moveToIceOne");
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (caveIceScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
        }
    }

    public CaveIceScreenState getCaveIceScreenState() {
        return caveIceScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    // This enum represents the different states this screen can be in
    private enum CaveIceScreenState {
        RUNNING
    }

    @Override
    public Map getMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMap'");
    }
}