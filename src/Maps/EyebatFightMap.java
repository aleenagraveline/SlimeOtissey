package Maps;

import Level.Map;
import Level.NPC;
//import NPCs.Bat;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.ImageEffect;
import GameObject.Sprite;

public class EyebatFightMap extends Map {

    private Sprite bat;
    private Point batLocation;

    public EyebatFightMap() {
        super("grass_fight_map.txt", new CommonTileset());
        batLocation = getMapTile(7, 6).getLocation().subtractX(20);
        bat = new Sprite(ImageLoader.loadSubImage("eyebat.png", Colors.MAGENTA, 0, 0, 24, 15));
        bat.setScale(5);
        //bat.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
        bat.setLocation(batLocation.x, batLocation.y);
    }

    public void shakeBat() {
        bat.setLocation(batLocation.x + (float) (Math.random() * 60 - 30), batLocation.y + (float) (Math.random() * 60 - 30));
    }

    public void unshakeBat() {
        bat.setLocation(batLocation.x, batLocation.y);
    }
    
    // TODO In the future we should have moving enemies via npcs and not sprites
    // @Override
    // public ArrayList<NPC> loadNPCs() {
    //     ArrayList<NPC> npcs = new ArrayList<>();
        
    //     bat bat = new bat(3, getMapTile(8, 8).getLocation().subtractX(20));
    //     npcs.add(bat);

    //     return npcs;
    // }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        bat.draw(graphicsHandler);
    }
}