package Puzzles.MemoryPuzzle;

import Engine.GraphicsHandler;

import java.awt.*;

public class ColorSequence{
    
	ColorDisplaySquare[] memoryPuzzleColors = new ColorDisplaySquare[6];
    
	public ColorSequence(){
		memoryPuzzleColors[0] = new ColorDisplaySquare(100, 50, 50, Color.RED);
		memoryPuzzleColors[1] = new ColorDisplaySquare(200, 50, 50, Color.RED);
		memoryPuzzleColors[2] = new ColorDisplaySquare(300, 50, 50, Color.GREEN);
		memoryPuzzleColors[3] = new ColorDisplaySquare(400, 50, 50, Color.YELLOW);
		memoryPuzzleColors[4] = new ColorDisplaySquare(500, 50, 50, Color.CYAN);
		memoryPuzzleColors[5] = new ColorDisplaySquare(600, 50, 50, Color.GREEN);
	}

    public void drawHidden(GraphicsHandler graphicsHandler) {
		for(ColorDisplaySquare square : memoryPuzzleColors) {
			square.drawHidden(graphicsHandler);
		}
	}

	public void drawPuzzle(GraphicsHandler graphicsHandler) {
		for(ColorDisplaySquare square : memoryPuzzleColors) {
			square.drawPuzzle(graphicsHandler);
		}
	}
}
