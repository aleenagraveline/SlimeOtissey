package EnhancedMapTiles;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.MapTile;
import Level.Player;
import Level.TileType;
import Scripts.IceFiveMap.IceFallScript;
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
            map.setActiveScript(new IceFallScript());
        }
    }

    // Method to allow one-line passing in CrackingIce1
    public CrackingIce1 withExistenceFlag(String existenceFlag) {
        this.setExistenceFlag(existenceFlag);
        return this;
    }

    public void breakIce() {
        this.setIsHidden(true);

        Frame brokenIceFrame = new FrameBuilder(map.getTileset().getSubImage(1, 3))
                            .withScale(map.getTileset().getTileScale())
                            .build();
        MapTile brokenIceTile = new MapTileBuilder(brokenIceFrame)
            .withTileType(TileType.PASSABLE)
            .build(this.x, this.y);

        this.map.setMapTile((int) this.x / 48, (int) this.y / 48, brokenIceTile);
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(3)
                .build();
        return new GameObject(x, y, frame);
    }
}
