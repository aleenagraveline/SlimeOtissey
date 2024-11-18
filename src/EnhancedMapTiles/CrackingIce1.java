package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.MapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;

// This class is for the cracking ice in the map that fully breaks after the player has walked on the tile
public class CrackingIce1 extends EnhancedMapTile {

    public CrackingIce1(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("IceTileset.png").getSubimage(68, 0, 16, 16), 16, 16), TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (player.touching(this)) {
            this.breakIce();
        }
    }

    public void breakIce() {
        System.out.println("Breaking " + x + " " + y);
        MapTile crackedIceTile = new MapTile(this.x, this.y, new SpriteSheet(ImageLoader.load("IceTileset.png").getSubimage(51,17,16,16), 16, 16), TileType.PASSABLE); 
        this.map.setMapTile((int) this.x, (int) this.y, crackedIceTile);
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(3)
                .build();
        return new GameObject(x, y, frame);
    }
}
