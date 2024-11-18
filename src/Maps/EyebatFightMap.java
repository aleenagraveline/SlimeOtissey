package Maps;

import Level.Map;
import Level.NPC;
//import NPCs.Bat;
import Tilesets.CommonTileset;
import Tilesets.ForestTileset;
import Utils.Colors;
import Utils.Point;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.ImageEffect;
import GameObject.Sprite;
import GameObject.SpriteSheet;

public class EyebatFightMap extends Map {

    private Sprite bat;
    private SpriteSheet batAnimations;
    private int currentAnim;
    private boolean flapUp;

    private Point batLocation;

    public EyebatFightMap() {
        super("grass_fight_map.txt", new ForestTileset());
        batLocation = getMapTile(7, 6).getLocation().subtractX(20);
        batAnimations = new SpriteSheet(ImageLoader.load("eyebat.png", Colors.MAGENTA), 24, 15);
        currentAnim = 0;
        flapUp = true;
        bat = new Sprite (batAnimations.getSprite(0,currentAnim));
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

    public void flapBat() {
        if (flapUp) {
            currentAnim++;
        } else {
            currentAnim--;
        }

        if (currentAnim > 3) {
            flapUp = false;
            currentAnim = 2;
        } else if (currentAnim < 0) {
            flapUp = true;
            currentAnim = 1;
        }

        bat.setImage(batAnimations.getSprite(0, currentAnim));
        bat.setLocation(batLocation.x, batLocation.y + currentAnim * 10);
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