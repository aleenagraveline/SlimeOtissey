package Screens;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.ScreenCoordinator;
import Level.*;
import Maps.BugFightMap;
import Puzzles.MemoryPuzzle.*;
import Scripts.SimpleTextScript;

import java.awt.*;

// This is the class for the memory ouzzle screen
public class MemoryPuzzleScreen extends Screen {
    // Hold playLevelScreen to return to same level screen
    protected PlayLevelScreen playLevelScreen;

    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0;
    protected int menuItemSelected = -1;

    protected GameButton redButton;
    protected GameButton yellowButton;
    protected GameButton greenButton;
    protected GameButton blueButton;

    protected ColorSequence sequence;
    protected int sequenceTimer;

    protected Map background;
    protected int keyPressTimer;
    protected KeyLocker keyLocker = new KeyLocker();

    public static final int RED = 0;
	public static final int YLW = 1;
	public static final int GRN = 2;
	public static final int BLU = 3;
    private static final int CORRECT_SEQUENCE[] = {RED, RED, GRN, YLW, BLU, GRN};

    private int correctGuesses;

    public MemoryPuzzleScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        this.initialize();
    }

    public void initialize() {
        // set up 
        

        // setup puzzle
        correctGuesses = 0;

        sequence = new ColorSequence();
        sequenceTimer = 100;

        redButton = new GameButton(125, 400, 100, Color.red, RED);
		yellowButton = new GameButton(275, 400, 100, Color.yellow, YLW);
		greenButton = new GameButton(425, 400, 100, Color.green, GRN);
		blueButton = new GameButton(575, 400, 100, Color.cyan, BLU);

        // define/setup map
        background = new BugFightMap();
        background.setAdjustCamera(false);
        
        // setup key/menu interactions
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        redButton.resetColor();
        yellowButton.resetColor();
        greenButton.resetColor();
        blueButton.resetColor();

        // if left or right is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.D) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered++;
        } else if (Keyboard.isKeyDown(Key.A) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered--;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        // if right is pressed on last menu item or left is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 3) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 3;
        }

        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == RED) {
            redButton.setBorderColor(Color.BLACK);
        } else if (currentMenuItemHovered == YLW) {
            yellowButton.setBorderColor(Color.BLACK);
        } else if (currentMenuItemHovered == GRN) {
            greenButton.setBorderColor(Color.BLACK);
        } else if (currentMenuItemHovered == BLU) {
            blueButton.setBorderColor(Color.BLACK);
        }

        // if space is pressed on menu item, perform action depending on which button was pressed
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            menuItemSelected = currentMenuItemHovered;
            keyLocker.lockKey(Key.SPACE);
            if (menuItemSelected == RED) {
                redButton.resetColor();
                redButton.setFillColor(Color.LIGHT_GRAY);
                checkSequence(RED);
            } else if (menuItemSelected == YLW) {
                yellowButton.resetColor();
                yellowButton.setFillColor(Color.LIGHT_GRAY);
                checkSequence(YLW);
            } else if (menuItemSelected == GRN) {
                greenButton.resetColor();
                greenButton.setFillColor(Color.LIGHT_GRAY);
                checkSequence(GRN);
            } else if (menuItemSelected == BLU) {
                blueButton.resetColor();
                blueButton.setFillColor(Color.LIGHT_GRAY);
                checkSequence(BLU);
            } else {
                System.out.println("How did you get here?");
            }
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        redButton.draw(graphicsHandler);
        yellowButton.draw(graphicsHandler);
        greenButton.draw(graphicsHandler);
        blueButton.draw(graphicsHandler);

        if(sequenceTimer > 0) {
            sequence.drawPuzzle(graphicsHandler);
            sequenceTimer--;
            System.out.println("" + sequenceTimer);
        } else {
            sequence.drawHidden(graphicsHandler);
        }
    }

    public void checkSequence(int memColor) {
		if (CORRECT_SEQUENCE[correctGuesses] != memColor) {
			correctGuesses = 0;
            sequenceTimer = 100;
			System.out.println("Wrong Guess :(");
		} else {
			correctGuesses++;
            sequenceTimer = -1;
			System.out.println("Right Guess :)");

			if (correctGuesses == 6) {
				System.out.println("You did it!!!!");
				this.playLevelScreen.exitMemPuzzle();
                this.playLevelScreen.map.setActiveScript(new SimpleTextScript(new String[] {
                    "You did the puzzle!", 
                    "This is when you would get another key.", 
                    "But there's only enough metal in this world for one key."}));
                    Player.gainFriendshipPoints(1);
			}
		}
	}
    
}
