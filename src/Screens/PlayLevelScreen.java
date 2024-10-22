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
import Scripts.TestMap.WalrusSmartScript;
import Utils.Direction;
import Utils.Point;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected BugFightScreen bugFightScreen; // BugFightScreen as a subscreen of PlayLevelScreen
    protected MemoryPuzzleScreen memoryPuzzleScreen;
    protected WinScreen winScreen;
    protected FlagManager flagManager;
    protected TownhouseScreen townhouseScreen; // TownHouseScreen as a subscreen of PlayLevelSCreen
    private static double currentVolume;

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
        flagManager.addFlag("isInMemPuzzle", false);
        flagManager.addFlag("playedMemPuzzle", false);
        flagManager.addFlag("moveToForestOne", false);
        flagManager.addFlag("moveToTownhouse", false);
        flagManager.addFlag("usingKey", false); // These two used in conditionalScript logic
        flagManager.addFlag("usedKey", false); // ^^^


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
        memoryPuzzleScreen = new MemoryPuzzleScreen(this);
        townhouseScreen = new TownhouseScreen(this);
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                GamePanel.enableDrawFriendshipPoints(true);
                break;
            // if in the bug battle, bring up battle screen
            case IN_BUG_BATTLE:
                bugFightScreen.update();
                break;
            // if in the memory puzzle, bring up the puzzle interface
            case IN_MEM_PUZZLE:
                memoryPuzzleScreen.update();
                break;
            case IN_TOWNHOUSE:
                townhouseScreen.update();
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }

        // If flag is set, move into house
        if (map.getFlagManager().isFlagSet("moveToTownhouse")) {
            playLevelScreenState = PlayLevelScreenState.IN_TOWNHOUSE;
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

        // if flag is set, memory puzzle starts
        if (map.getFlagManager().isFlagSet("isInMemPuzzle")) {
            playLevelScreenState = PlayLevelScreenState.IN_MEM_PUZZLE;
        }

        // if flag is set, change bug npc script and change flag back to avoid unnecessary running of this method
        if (map.getFlagManager().isFlagSet("playedMemPuzzle")) {
            map.getNPCById(1).setInteractScript(new WalrusSmartScript());
            map.getFlagManager().unsetFlag("playedMemPuzzle");
        }

        // if flag is set at any point during gameplay, game is "won"
        if (map.getFlagManager().isFlagSet("hasFoundKey")) {
            //playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
            GamePanel.addToInventory("Key");
            Player.gainFriendshipPoints(1);
            map.getFlagManager().unsetFlag("hasFoundKey");
        }

        // If flag is set, move to next screen and unset flag
        if (map.getFlagManager().isFlagSet("moveToForestOne")) {
            screenCoordinator.setGameState(GameState.FOREST_ONE);
            map.getFlagManager().unsetFlag("moveToForestOne");
        }

        // Wrapped in if statement to avoid running every update
        // If key is in inventory and is being used, set flag
        if (!map.getFlagManager().isFlagSet("keyIsInTree") && !map.getFlagManager().isFlagSet("usedKey")) {
            if (GamePanel.getIsUsingItem() && GamePanel.getItemInUse().equals("Key")) {
                map.getFlagManager().setFlag("usingKey");
            }
            else {
                map.getFlagManager().unsetFlag("usingKey");
            }
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
            case IN_MEM_PUZZLE:
                memoryPuzzleScreen.draw(graphicsHandler);
                break;
            case IN_TOWNHOUSE:
                townhouseScreen.draw(graphicsHandler);
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

    public void exitMemPuzzle() {
        map.getFlagManager().unsetFlag("isInMemPuzzle");
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        this.update();
    }

    public void exitTownhouse() {
        map.getFlagManager().unsetFlag("moveToTownhouse");
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        this.update();
    }

    public void setCurrentVolume(float volume) {
        this.currentVolume = currentVolume;
      }
    
      public static double getCurrentVolume() {
        return currentVolume;
      }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, IN_BUG_BATTLE, IN_MEM_PUZZLE, IN_TOWNHOUSE, LEVEL_COMPLETED
    }

    /*public static Map getMap() {
        return map;
    }*/
}
