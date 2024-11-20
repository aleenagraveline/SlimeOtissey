package Screens.CaveScreens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Maps.CaveMaps.CaveThreeMap;
import Players.Cat;
import ScriptActions.ChangeFlagScriptAction;
import Scripts.CrystalScripts.CrystalPuzzleCompleteScript;
import Utils.Direction;

public class CaveThreeScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected CaveThreeScreenState caveThreeScreenState;
    protected FlagManager flagManager;
    public static int changeCounter = 0;

    public CaveThreeScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("moveToCaveTwo", false);
        flagManager.addFlag("moveToCaveIce", false);
        flagManager.addFlag("hasGainedFriendship", false);
        flagManager.addFlag("hasCompletedCrystalPuzzle", false);
        // flagManager.addFlag("crystal1Flag", false);
        // flagManager.addFlag("crystal2Flag", false);
        // flagManager.addFlag("crystal3Flag", false);
        // flagManager.addFlag("crystal4Flag", false);
        // flagManager.addFlag("crystal5Flag", false);
        // flagManager.addFlag("crystal6Flag", false);
        // flagManager.addFlag("crystal7Flag", false);
        // flagManager.addFlag("crystal8Flag", false);
        // flagManager.addFlag("crystal9Flag", false);

        // define/setup map
        map = new CaveThreeMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        caveThreeScreenState = CaveThreeScreenState.RUNNING;
        player.setFacingDirection(Direction.UP);

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
        switch (caveThreeScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
        }
        
        if(!map.getFlagManager().isFlagSet("hasGainedFriendship") && map.getFlagManager().isFlagSet("hasCompletedCrystalPuzzle")) {
            Player.gainFriendshipPoints(4,6);
            map.getFlagManager().setFlag("hasGainedFriendship");
            System.out.println("yay friends");
        }

        if((changeCounter == 9)) {
            map.setActiveScript(new CrystalPuzzleCompleteScript());
            changeCounter = 0;
        }

        if (map.getFlagManager().isFlagSet("moveToCaveTwo")) {

            screenCoordinator.setGameState(GameState.CAVE_TWO);
            map.getFlagManager().unsetFlag("moveToCaveTwo");
        }
        else if (map.getFlagManager().isFlagSet("moveToCaveIce")) {
            screenCoordinator.setGameState(GameState.CAVE_ICE);
            map.getFlagManager().unsetFlag("moveToCaveIce");
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (caveThreeScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
        }
    }

    public CaveThreeScreenState getCaveThreeScreenState() {
        return caveThreeScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    // This enum represents the different states this screen can be in
    private enum CaveThreeScreenState {
        RUNNING
    }
}