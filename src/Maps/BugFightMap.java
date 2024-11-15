package Maps;

import Level.Map;
import Level.NPC;
import NPCs.Bug;
import Tilesets.ForestTileset;
import Utils.Colors;
import Utils.Point;

import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.ImageEffect;
import GameObject.Sprite;

public class BugFightMap extends Map {

    private Sprite bug;
    private Point bugLocation;

    public BugFightMap() {
        super("grass_fight_map.txt", new ForestTileset());
        bugLocation = getMapTile(7, 7).getLocation().subtractX(20);
        bug = new Sprite(ImageLoader.loadSubImage("Bug.png", Colors.MAGENTA, 0, 0, 24, 15));
        bug.setScale(5);
        bug.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
        bug.setLocation(bugLocation.x, bugLocation.y);
    }

    public void shakeBug() {
        bug.setLocation(bugLocation.x + (float) (Math.random() * 60 - 30), bugLocation.y + (float) (Math.random() * 60 - 30));
    }

    public void unshakeBug() {
        bug.setLocation(bugLocation.x, bugLocation.y);
    }
    
    // TODO In the future we should have moving enemies via npcs and not sprites
    // @Override
    // public ArrayList<NPC> loadNPCs() {
    //     ArrayList<NPC> npcs = new ArrayList<>();
        
    //     Bug bug = new Bug(3, getMapTile(8, 8).getLocation().subtractX(20));
    //     npcs.add(bug);

    //     return npcs;
    // }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        bug.draw(graphicsHandler);
    }

}
