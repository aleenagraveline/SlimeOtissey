package Level;

import Engine.GraphicsHandler;
import GameObject.GameObject;
import GameObject.IntersectableRectangle;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Utils.Point;

// Represents a map tile in a Map's tile map
public class MapTile extends MapEntity {
    // this determines a tile's properties, like if it's passable or not
    protected TileType tileType;

    // bottom layer of tile
    protected GameObject bottomLayer;

    // midBottomLayer of tile
    protected GameObject midBottomLayer;

    // midTopLayer of tile
    protected GameObject midTopLayer;

    // top layer of tile ("pasted on top of" bottom layer, covers player)
    protected GameObject topLayer;

    private int tileIndex;

    // "full" constructor
    public MapTile(float x, float y, GameObject bottomLayer, GameObject midBottomLayer, GameObject midTopLayer, GameObject topLayer, TileType tileType, int tileIndex) {
        super(x,y);
        this.bottomLayer = bottomLayer;
        this.midBottomLayer = midBottomLayer;
        this.midTopLayer = midTopLayer;
        this.topLayer = topLayer;
        this.tileType = tileType;
        this.tileIndex = tileIndex;
    }

    public MapTile(float x, float y, GameObject bottomLayer, GameObject topLayer, TileType tileType, int tileIndex) {
        super(x, y);
        this.bottomLayer = bottomLayer;
        this.topLayer = topLayer;
        this.tileType = tileType;
        this.tileIndex = tileIndex;
    }

    public MapTile(float x, float y, GameObject bottomLayer, GameObject topLayer, TileType tileType) {
        super(x, y);
        this.bottomLayer = bottomLayer;
        this.topLayer = topLayer;
        this.tileType = tileType;
    }

    public MapTile(float x, float y, SpriteSheet spriteSheet, TileType tileType) {
        super(x, y);
        this.bottomLayer = loadBottomLayer(spriteSheet);
        this.topLayer = loadTopLayer(spriteSheet);
        this.tileType = tileType;
    }

    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        return null;
    }

    protected GameObject loadMidBottomLayer(SpriteSheet spriteSheet) {
        return null;
    }

    protected GameObject loadMidTopLayer(SpriteSheet spriteSheet) {
        return null;
    }

    protected GameObject loadTopLayer(SpriteSheet spriteSheet) {
        return null;
    }

    public TileType getTileType() {
        return tileType;
    }

    public int getTileIndex() {
        return tileIndex;
    }

    public GameObject getBottomLayer() { return bottomLayer; }
    public void setBottomLayer(GameObject bottomLayer) { this.bottomLayer = bottomLayer; }

    public GameObject getMidBottomLayer() { return midBottomLayer; }
    public void setMidBottomLayer(GameObject midBottomLayer) { this.midBottomLayer = midBottomLayer; }

    public GameObject getMidTopLayer() { return midTopLayer; }
    public void setMidTopLayer(GameObject midTopLayer) { this.midTopLayer = midTopLayer; }

    public GameObject getTopLayer() { return topLayer; }
    public void setTopLayer(GameObject topLayer) { this.topLayer = topLayer; }

    // determines if tile is animated or not
    public boolean isAnimated() {
        return (bottomLayer.getCurrentAnimation().length > 1) ||
                (midBottomLayer != null && midBottomLayer.getCurrentAnimation().length > 1) ||
                (midTopLayer != null && midTopLayer.getCurrentAnimation().length > 1) ||
                (topLayer != null && topLayer.getCurrentAnimation().length > 1);
    }

    // set this game object's map to make it a "part of" the map, allowing calibrated positions and collision handling logic to work
    // note that both the bottom layer and the top layer need the map reference
    public void setMap(Map map) {
        this.map = map;
        this.bottomLayer.setMap(map);
        if (midBottomLayer != null) {
            this.midBottomLayer.setMap(map);
        }
        if (midTopLayer != null) {
            this.midTopLayer.setMap(map);
        }
        if (topLayer != null) {
            this.topLayer.setMap(map);
        }
    }

    @Override
    public void update() {
        bottomLayer.update();
        if (midBottomLayer != null) {
            midBottomLayer.update();
        }
        if (midTopLayer != null) {
            midTopLayer.update();
        }
        if (topLayer != null) {
            topLayer.update();
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        bottomLayer.draw(graphicsHandler);
        if (midBottomLayer != null) {
            midBottomLayer.draw(graphicsHandler);
        }
        if (midTopLayer != null) {
            midTopLayer.draw(graphicsHandler);
        }
        if (topLayer != null) {
            topLayer.draw(graphicsHandler);
        }
    }

    public void drawBottomLayer(GraphicsHandler graphicsHandler) {
        bottomLayer.draw(graphicsHandler);

        // uncomment this to draw bounds of all non passable tiles (useful for debugging)
        /*
        if (tileType == TileType.NOT_PASSABLE) {
            drawBounds(graphicsHandler, new Color(0, 0, 255, 100));
        }
        */
    }

    public void drawMidBottomLayer(GraphicsHandler graphicsHandler) {
        midBottomLayer.draw(graphicsHandler);

        // uncomment this to draw bounds of all non passable tiles (useful for debugging)
        /*
        if (tileType == TileType.NOT_PASSABLE) {
            drawBounds(graphicsHandler, new Color(0, 0, 255, 100));
        }
        */
    }

    public void drawMidTopLayer(GraphicsHandler graphicsHandler) {
        midTopLayer.draw(graphicsHandler);

        // uncomment this to draw bounds of all non passable tiles (useful for debugging)
        /*
        if (tileType == TileType.NOT_PASSABLE) {
            drawBounds(graphicsHandler, new Color(0, 0, 255, 100));
        }
        */
    }

    public void drawTopLayer(GraphicsHandler graphicsHandler) {
        topLayer.draw(graphicsHandler);

        // uncomment this to draw bounds of all non passable tiles (useful for debugging)
        /*
        if (tileType == TileType.NOT_PASSABLE) {
            drawBounds(graphicsHandler, new Color(0, 0, 255, 100));
        }
        */
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getX1() { return bottomLayer.getX(); }

    @Override
    public float getY1() { return bottomLayer.getY(); }

    @Override
    public float getX2() { return bottomLayer.getX2(); }

    @Override
    public float getY2() { return bottomLayer.getY2(); }

    @Override
    public Point getLocation() {
        return new Point(x, y);
    }

    @Override
    public Rectangle getIntersectRectangle() {
        return bottomLayer.getIntersectRectangle();
    }

    @Override
    public int getWidth() {
        return bottomLayer.getWidth();
    }

    @Override
    public int getHeight() {
        return bottomLayer.getHeight();
    }

    @Override
    public Rectangle getBounds() {
        return bottomLayer.getBounds();
    }

    @Override
    public void setX(float x) {
        this.x = x;
        bottomLayer.setX(x);
        if (midBottomLayer != null) {
            midBottomLayer.setX(x);
        }
        if (midTopLayer != null) {
            midTopLayer.setX(x);
        }
        if (topLayer != null) {
            topLayer.setX(x);
        }
    }

    @Override
    public void setY(float y) {
        this.y = y;
        bottomLayer.setY(y);
        if (midBottomLayer != null) {
            midBottomLayer.setY(y);
        }
        if (midTopLayer != null) {
            midTopLayer.setY(y);
        }
        if (topLayer != null) {
            topLayer.setY(y);
        }
    }

    @Override
    public void setLocation(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void moveX(float dx) {
        this.x += dx;
        bottomLayer.moveX(dx);
        if (midBottomLayer != null) {
            midBottomLayer.moveX(dx);
        }
        if (midTopLayer != null) {
            midTopLayer.moveX(dx);
        }
        if (topLayer != null) {
            topLayer.moveX(dx);
        }
    }

    @Override
    public void moveRight(float dx) {
        this.x += dx;
        bottomLayer.moveRight(dx);
        if (midBottomLayer != null) {
            midBottomLayer.moveRight(dx);
        }
        if (midTopLayer != null) {
            midTopLayer.moveRight(dx);
        }
        if (topLayer != null) {
            topLayer.moveRight(dx);
        }
    }

    @Override
    public void moveLeft(float dx) {
        this.x -= dx;
        bottomLayer.moveLeft(dx);
        if (midBottomLayer != null) {
            midBottomLayer.moveLeft(dx);
        }
        if (midTopLayer != null) {
            midTopLayer.moveLeft(dx);
        }
        if (topLayer != null) {
            topLayer.moveLeft(dx);
        }
    }

    @Override
    public void moveY(float dy) {
        this.y += dy;
        bottomLayer.moveY(dy);
        if (midBottomLayer != null) {
            midBottomLayer.moveY(dy);
        }
        if (midTopLayer != null) {
            midTopLayer.moveY(dy);
        }
        if (topLayer != null) {
            topLayer.moveY(dy);
        }
    }

    @Override
    public void moveDown(float dy) {
        this.y += dy;
        bottomLayer.moveDown(dy);
        if (midBottomLayer != null) {
            midBottomLayer.moveDown(dy);
        }
        if (midTopLayer != null) {
            midTopLayer.moveDown(dy);
        }
        if (topLayer != null) {
            topLayer.moveDown(dy);
        }
    }

    @Override
    public void moveUp(float dy) {
        this.y -= dy;
        bottomLayer.moveUp(dy);
        if (midBottomLayer != null) {
            midBottomLayer.moveUp(dy);
        }
        if (midTopLayer != null) {
            midTopLayer.moveUp(dy);
        }
        if (topLayer != null) {
            topLayer.moveUp(dy);
        }
    }

    @Override
    public boolean intersects(IntersectableRectangle other) {
        return bottomLayer.intersects(other);
    }

    @Override
    public boolean touching(IntersectableRectangle other) { return bottomLayer.touching(other); }

    @Override
    public float getAreaOverlapped(IntersectableRectangle other) { return bottomLayer.getAreaOverlapped(other); }
}
