package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Direction;
import Utils.Point;

// This class is for the sliding ice in the map that forces the player to move
// when the player walks into it, the player continues moving until it hits a non-passable tile
public class SlidingIce extends EnhancedMapTile {
    private static boolean sliding;

    public SlidingIce(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("IceTileset.png").getSubimage(51, 0, 16, 16), 16, 16), TileType.PASSABLE);
        sliding = false;
    }

    @Override
    public void update(Player player) {
        super.update(player);
        // TODO Fix double speed (maybe) (optional)
        // Sliding logic
        if (player.touching(this)) {
            player.lock();
            if (player.getCurrentWalkingXDirection() == Direction.RIGHT) {
                sliding = player.handleCollisionX(2.3f) != 0;
            }
            else if (player.getCurrentWalkingXDirection() == Direction.LEFT) {
                sliding = player.handleCollisionX(-2.3f) != 0;
            }
            else if (player.getCurrentWalkingYDirection() == Direction.UP) {
                sliding = player.handleCollisionY(-2.3f) != 0;
            }
            else if (player.getCurrentWalkingYDirection() == Direction.DOWN) {
                sliding = player.handleCollisionY(2.3f) != 0;
            }
        }
        if (player.isLocked() && !sliding) {
            player.unlock();
            sliding = true; // Handle weird edge case
        }
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(3)
                .build();
        return new GameObject(x, y, frame);
    }
}
