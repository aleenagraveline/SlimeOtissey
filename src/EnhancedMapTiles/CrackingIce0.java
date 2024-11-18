package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;

// This class is for the cracking ice in the map that cracks after the player has walked on the tile
public class CrackingIce0 extends EnhancedMapTile {
    private boolean shouldCrack;

    public CrackingIce0(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("IceTileset.png").getSubimage(51, 0, 16, 16), 16, 16), TileType.PASSABLE);
        shouldCrack = false;
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (player.touching(this)) {
            shouldCrack = true;
        }
        else if (shouldCrack) {
            this.crack();
        }
    }

    public void crack() {
        this.setIsHidden(true);
        this.map.addEnhancedMapTile(new CrackingIce1(this.getLocation()));
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(3)
                .build();
        return new GameObject(x, y, frame);
    }
}
