package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.MapTile;
import Level.TileType;
import Utils.Point;


public class Food extends EnhancedMapTile {
    private MapTile tile;

    public Food(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Apple.png"), 16, 16), TileType.NOT_PASSABLE);
       // this.tile = map.getTileByPosition(location.x, location.y);
    }

    public MapTile getTile() {
        return tile;
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(3)
                .build();
        return new GameObject(x, y, frame);
    }
}
