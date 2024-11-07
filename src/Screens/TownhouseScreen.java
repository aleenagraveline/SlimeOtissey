package Screens;

import java.util.Random;

import Engine.*;
import Level.*;
import Players.Cat;
import Utils.Direction;
import Maps.TownhouseMap;

public class TownhouseScreen extends Screen {
    protected PlayLevelScreen playLevelScreen;
    protected FlagManager flagManager;
    protected Map map;
    protected TownhouseMap townhouse;
    protected Player player;

    public TownhouseScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        this.initialize();
    }

    @Override
    public void initialize() {
        // Setup flagManager
        flagManager = new FlagManager();
        flagManager.addFlag("moveToSpawn", false);
        flagManager.addFlag("hasEatenFood", false);

        // Setup map
        map = new TownhouseMap();
        map.setFlagManager(flagManager);

        // Setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        player.setFacingDirection(Direction.DOWN);

        map.setPlayer(player);

        // Set interact key for map
        map.getTextbox().setInteractKey(player.getInteractKey());

        // Preload scripts
        map.preloadScripts();
    }

    @Override
    public void update() {
        // Switch screens if we need, else keep updating this one
        if (map.getFlagManager().isFlagSet("moveToSpawn")) {
            flagManager.unsetFlag("moveToSpawn");
            this.playLevelScreen.exitTownhouse();
        } else if(map.getFlagManager().isFlagSet("hasEatenFood")) {
            map.getEnhancedMapTiles().get(0).setIsHidden(true);
            eat();
            map.getFlagManager().unsetFlag("hasEatenFood");
        }
        else {
            player.update();
            map.update(player);
            GamePanel.enableDrawFriendshipPoints(true);
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        this.map.draw(player, graphicsHandler);
    }

    public void eat() {
        Random random = new Random();
        Player.gainFriendshipPoints(random.nextInt(3) + 1);
        System.out.println("eating method running");
    }
    
}
