package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

public class Otis extends NPC {

    public Otis(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Small Otis.png"), 24, 24), "STAND_LEFT");
        this.setIsUncollidable(true);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withScale(3)
                        .withBounds(4, 5, 5, 10)
                        .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withScale(3)
                        .withBounds(4, 5, 5, 10)
                        .build()
            });

            put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                        .withScale(3)
                        .withBounds(4, 5, 5, 10)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                        .withScale(3)
                        .withBounds(4, 5, 5, 10)
                        .build()
            });

            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                        .withScale(3)
                        .withBounds(4, 5, 5, 10)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                        .withScale(3)
                        .withBounds(4, 5, 5, 10)
                        .build()
            });
        }};
    }

    @Override
    protected void performAction(Player player) {
        // Get the player's current position
        float playerX = player.getX();
        float playerY = player.getY();

        // Get Otis' current position
        float otisX = this.getX();
        float otisY = this.getY();

        // Set Otis' movement speed
        float speed = player.getSpeed();

        // Get tile locations of both Otis and player
        Point otisTile = getTileLocation();
        Point playerTile = player.getTileLocation();

        float deltaX = Math.abs(otisTile.x - playerTile.x);
        float deltaY = Math.abs(otisTile.y - playerTile.y);

        // Only move Otis if not on the same tile as the player
        if (!otisTile.equals(playerTile)) {
            if (deltaX > 2 || deltaY > 2) {
                if (otisX < playerX) {
                    this.walk(Direction.RIGHT, speed);  // Move right
                } else if (otisX > playerX) {
                    this.walk(Direction.LEFT, speed);   // Move left
                }

                if (otisY < playerY) {
                    this.walk(Direction.DOWN, speed);   // Move down
                } else if (otisY > playerY) {
                    this.walk(Direction.UP, speed);     // Move up
                }
            }
        }

        // Make Otis face the player
        this.facePlayer(player);
    }

    // Method to get the tile location of Otis
    public Point getTileLocation() {
        return new Point(Math.round(getX() / 24), Math.round(getY() / 24));
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
