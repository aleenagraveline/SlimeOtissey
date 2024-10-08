package Screens;

import Engine.GamePanel;
import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
import Maps.TestMap;
import Players.Cat;
import Scripts.TestMap.BugHaterScript;
import Utils.Direction;
import Utils.Point;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected BugFightScreen bugFightScreen; // BugFightScreen as a subscreen of PlayLevelScreen
    protected WinScreen winScreen;
    protected FlagManager flagManager;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundKey", false);
        flagManager.addFlag("keyIsInTree", true);
        flagManager.addFlag("isInBugBattle", false);
        flagManager.addFlag("hatesBugs", false);

        // define/setup map
        map = new TestMap();
        map.setFlagManager(flagManager);

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        player.setFacingDirection(Direction.LEFT);

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();

        winScreen = new WinScreen(this);
        bugFightScreen = new BugFightScreen(this);
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
            // if in the bug battle, bring up battle screen
            case IN_BUG_BATTLE:
                bugFightScreen.update();
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }

        // if flag is set, bug battle starts
        if (map.getFlagManager().isFlagSet("isInBugBattle")) {
            playLevelScreenState = PlayLevelScreenState.IN_BUG_BATTLE;
        }

        // if flag is set, change bug npc script and change flag back to avoid unnecessary running of this method
        if (map.getFlagManager().isFlagSet("hatesBugs")) {
            map.getNPCById(3).setInteractScript(new BugHaterScript());
            map.getFlagManager().unsetFlag("hatesBugs");
        }

        // if flag is set at any point during gameplay, game is "won"
        if (map.getFlagManager().isFlagSet("hasFoundKey")) {
            //playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
            GamePanel.addToInventory("Key");
            map.getFlagManager().unsetFlag("hasFoundKey");
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
            case IN_BUG_BATTLE:
                bugFightScreen.draw(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                winScreen.draw(graphicsHandler);
                break;
        }
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    public void exitBugBattle() {
        map.getFlagManager().unsetFlag("isInBugBattle");
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        this.update();
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, IN_BUG_BATTLE, LEVEL_COMPLETED
    }

    /*public static Map getMap() {
        return map;
    }*/
}
