package Puzzles.MemoryPuzzle;

import Engine.GraphicsHandler;
import java.awt.*;

public class ColorDisplaySquare {
    protected int x, y, size;
    protected Color color;

    public ColorDisplaySquare(int x, int y, int size, Color newColor){
		this.x = x;
		this.y = y;
        this.size = size;
        this.color = newColor;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return(x);
    }

    public int getY() {
        return(y);
    }

    public void setSize(int newSize) {
        this.size = newSize;
    }

    public int getSize() {
        return(size);
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }

    public Color getColor() {
        return color;
    }

    public void drawPuzzle(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(x, y, size, size, color);
	}

    public void drawHidden(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(x, y, size, size, Color.BLACK);
	}
}
