package Puzzles.MemoryPuzzle;

import Engine.GraphicsHandler;

import java.awt.*;

// This class represents a sprite font, which is graphic text (text drawn to the screen as if it were an image)
public class GameButton {
	protected int x;
	protected int y;
    protected Color trueColor;
	protected Color buttonColor;
	protected Color outlineColor;
	protected int outlineThickness = 2;
    protected int size, gameColor;

    public GameButton(int x, int y, int size, Color trueColor, int gameColor){
		this.x = x;
		this.y = y;
        this.trueColor = trueColor;
        this.buttonColor = trueColor;
        this.outlineColor = trueColor;
        this.size = size;
		this.gameColor = gameColor;
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

    public void setColor(int gameColor, Color newColor) {
        this.gameColor = gameColor;
        this.buttonColor = newColor;
        this.outlineColor = newColor;
    }
     
    public void setFillColor(Color newColor) {
        this.buttonColor = newColor;
    }

    public Color getButtonColor() {
        return(buttonColor);
    }

    public int getGameColor() {
        return gameColor;
    }

    public void setBorderColor(Color newColor) {
        this.outlineColor = newColor;
    }

    public void resetColor() {
        buttonColor = trueColor;
        outlineColor = trueColor;
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangleWithBorder(x, y, size, size, buttonColor, outlineColor, outlineThickness);
	}
}