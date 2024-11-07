package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Utils.Direction;
import Utils.Point;

// This class is for the sliding ice in the map that forces the player to move
// when the player walks into it, the player continues moving until it hits a non-passable tile
public class SlidingIce extends EnhancedMapTile {
    public SlidingIce(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("IceTileset.png").getSubimage(51, 0, 16, 16), 16, 16), TileType.PASSABLE);
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (player.touching(this)) {
            player.lock();
            player.walk(player.getCurrentWalkingXDirection(), 2.3f);
            if (player.getCurrentWalkingXDirection() == Direction.RIGHT) {
                if (canMoveRight(player)) {
                    player.walk(Direction.RIGHT, 2.3f);
                }
            }
            else if (player.getCurrentWalkingXDirection() == Direction.LEFT) {    
                if (canMoveLeft(player)) {
                    player.walk(Direction.LEFT, 2.3f);
                }
            }
            else if (player.getCurrentWalkingYDirection() == Direction.UP) {    
                if (canMoveUp(player)) {
                    player.walk(Direction.UP, 2.3f);
                }
            }
            else if (player.getCurrentWalkingYDirection() == Direction.DOWN) {
                if (canMoveDown(player)) {
                    player.walk(Direction.DOWN, 2.3f);
                }
            }
            player.setWalkingXDirection(player.getLastWalkingXDirection());
            player.setWalkingYDirection(player.getLastWalkingYDirection());

            player.unlock();
        }


        // if (player.getCurrentWalkingXDirection() == Direction.RIGHT) {
        //     if (canMoveRight(player)) {
        //         player.walk(Direction.RIGHT, 0.1f);
        //     }
        // }
        // else if (player.getCurrentWalkingXDirection() == Direction.LEFT) {    
        //     if (canMoveLeft(player)) {
        //         player.walk(Direction.LEFT, 0.1f);
        //     }
        // }
        // else if (player.getCurrentWalkingYDirection() == Direction.UP) {    
        //     if (canMoveUp(player)) {
        //         player.walk(Direction.UP, 0.1f);
        //     }
        // }
        // else if (player.getCurrentWalkingYDirection() == Direction.DOWN) {
        //     if (canMoveDown(player)) {
        //         player.walk(Direction.DOWN, 0.1f);
        //     }
        // }
    }


    private boolean canMoveLeft(Player player) {
        return player.getBounds().getX1() <= getBounds().getX2() + 1 && player.getBounds().getX2() > getBounds().getX2() && canMoveX(player);
    }

    private boolean canMoveRight(Player player) {
        return player.getBounds().getX2() + 1 >= getBounds().getX1() && player.getBounds().getX1() < getBounds().getX1() && canMoveX(player);
    }

    private boolean canMoveX(Player player) {
        return (player.getBounds().getY1() <= getBounds().getY2() && player.getBounds().getY2() >= getBounds().getY2()) ||
                (player.getBounds().getY2() >= getBounds().getY1() && player.getBounds().getY1() <= getBounds().getY1()) ||
                (player.getBounds().getY2() <= getBounds().getY2() && player.getBounds().getY1() >= getBounds().getY1());
    }

    private boolean canMoveUp(Player player) {
        return player.getBounds().getY1() <= getBounds().getY2() + 1 && player.getBounds().getY2() > getBounds().getY2() && canMoveY(player);
    }

    private boolean canMoveDown(Player player) {
        return player.getBounds().getY2() + 1 >= getBounds().getY1() && player.getBounds().getY1() < getBounds().getY1() && canMoveY(player);
    }

    private boolean canMoveY(Player player) {
        return (player.getBounds().getX1() <= getBounds().getX2() && player.getBounds().getX2() >= getBounds().getX2()) ||
                (player.getBounds().getX2() >= getBounds().getX1() && player.getBounds().getX1() <= getBounds().getX1()) ||
                (player.getBounds().getX2() <= getBounds().getX2() && player.getBounds().getX1() >= getBounds().getX1());
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(3)
                .build();
        return new GameObject(x, y, frame);
    }
}
