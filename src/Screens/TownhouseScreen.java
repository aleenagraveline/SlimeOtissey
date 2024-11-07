package Screens;

import Engine.*;
import Level.*;
import Players.Cat;
import Utils.Direction;
import Maps.TownhouseMap;

public class TownhouseScreen extends Screen {
    protected PlayLevelScreen playLevelScreen;
    protected FlagManager flagManager;
    protected Map map;
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

    public Map getMap() {
        return map;
    }

    @Override
    public void update() {
        // Switch screens if we need, else keep updating this one
        if (map.getFlagManager().isFlagSet("moveToSpawn")) {
            flagManager.unsetFlag("moveToSpawn");
            this.playLevelScreen.exitTownhouse();
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
    
}
