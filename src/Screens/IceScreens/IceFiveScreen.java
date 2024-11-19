package Screens.IceScreens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Maps.IceMaps.IceFiveMap;
import Players.Cat;
import Utils.Direction;

public class IceFiveScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected IceFiveScreenState iceFiveScreenState;
    protected FlagManager flagManager;
    protected FellThroughIceScreen fellThroughIceScreen;

    public IceFiveScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("moveToIceFour", false);
        flagManager.addFlag("fellThroughIce", false);

        // define/setup map
        map = new IceFiveMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        iceFiveScreenState = IceFiveScreenState.RUNNING;
        player.setFacingDirection(Direction.LEFT);

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();

        this.fellThroughIceScreen = new FellThroughIceScreen(this);
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (iceFiveScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
            case FELL_THROUGH_ICE:
                fellThroughIceScreen.update();
                break;
        }

        if (map.getFlagManager().isFlagSet("moveToIceFour")) {
            screenCoordinator.setGameState(GameState.ICE_FOUR);
            map.getFlagManager().unsetFlag("moveToIceFour");
        }
        if (map.getFlagManager().isFlagSet("fellThroughIce")) {
            this.iceFiveScreenState = IceFiveScreenState.FELL_THROUGH_ICE;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (iceFiveScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
            case FELL_THROUGH_ICE:
                fellThroughIceScreen.draw(graphicsHandler);
                break;
        }
    }

    public IceFiveScreenState getIceFiveScreenState() {
        return iceFiveScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    // This enum represents the different states this screen can be in
    private enum IceFiveScreenState {
        RUNNING, FELL_THROUGH_ICE
    }

    @Override
    public Map getMap() {
        return this.map;
    }
}