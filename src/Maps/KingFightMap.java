package Maps;

import Level.Map;
import Level.NPC;
//import NPCs.King;
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

public class KingFightMap extends Map {

    private Sprite king;
    private SpriteSheet kingAnimations;
    private int currentResistance;

    private Point KingLocation;

    public KingFightMap() {
        super("grass_fight_map.txt", new ForestTileset());
        KingLocation = getMapTile(7, 6).getLocation().subtractX(20);
        kingAnimations = new SpriteSheet(ImageLoader.load("King Lodeon.png", Colors.MAGENTA), 24, 15);
        currentResistance = 0;
        king = new Sprite (kingAnimations.getSprite(1, currentResistance));
        king.setScale(5);
        //King.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
        king.setLocation(KingLocation.x, KingLocation.y);
    }

    public void shakeKing() {
        king.setLocation(KingLocation.x + (float) (Math.random() * 60 - 30), KingLocation.y + (float) (Math.random() * 60 - 30));
    }

    public void unshakeKing() {
        king.setLocation(KingLocation.x, KingLocation.y);
    }

    public int cycleKingResistance() {
        currentResistance = (int) (Math.random() * 3);

        king.setImage(kingAnimations.getSprite(0, currentResistance));

        return currentResistance;
    }
    
    // TODO In the future we should have moving enemies via npcs and not sprites
    // @Override
    // public ArrayList<NPC> loadNPCs() {
    //     ArrayList<NPC> npcs = new ArrayList<>();
        
    //     King King = new King(3, getMapTile(8, 8).getLocation().subtractX(20));
    //     npcs.add(King);

    //     return npcs;
    // }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        king.draw(graphicsHandler);
    }
}