package Screens;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.ScreenCoordinator;
import Level.*;
import Maps.BugFightMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This is the class for the memory ouzzle screen
public class WaitingPuzzleScreen extends Screen {
    // Hold playLevelScreen to return to same level screen
    protected PlayLevelScreen playLevelScreen;

    protected ScreenCoordinator screenCoordinator;

    protected SpriteFont instructions;

    protected SpriteFont countdown;
    protected int countdownTimer;

    protected SpriteFont resetButton;

    protected Map background;
    protected int keyPressTimer;
    protected KeyLocker keyLocker = new KeyLocker();


    public WaitingPuzzleScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        this.initialize();
    }

    public void initialize() {
        // set up 
        
        // setup timer
        countdownTimer = 600;

        // set up countdown displayer
        instructions = new SpriteFont("TMB BPM BQUMZ NQVQAP                 1, 1, 2, 3, 5, ?", 20, 100, "Arial", 30, Color.WHITE);
        countdown = new SpriteFont("TEN.", 300, 200, "Arial", 60, new Color(0, 255, 0));
        resetButton = new SpriteFont("RESET COUNTDOWN", 80, 300, "Arial", 60, new Color(255, 0, 0));

        instructions.setOutlineColor(Color.BLACK);
        countdown.setOutlineColor(Color.BLACK);
        resetButton.setOutlineColor(Color.WHITE);

        instructions.setOutlineThickness(2);
        countdown.setOutlineThickness(3);
        resetButton.setOutlineThickness(3);

        // define/setup map
        background = new BugFightMap();
        background.setAdjustCamera(false);
        
        // setup key/menu interactions
        keyPressTimer = 0;
        keyLocker.lockKey(Key.SPACE);
    }

    public Map getMap() {
        return background;
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        // if space is pressed on menu item, perform action depending on which button was pressed
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
            resetButton.setOutlineColor(Color.WHITE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE) && countdownTimer >= 1) {
            countdownTimer = 600;
            countdown.setText("TEN.");
            countdown.setColor(new Color(0, 255, 0));

            resetButton.setOutlineColor(new Color(255, 215, 0));
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);

        if(countdownTimer >= -120) {
            countdownTimer--;
            System.out.println("" + countdownTimer);
            if (countdownTimer == 540) {
                countdown.setText("NINE.");
                countdown.setColor(new Color(28, 227, 0));
            } else if (countdownTimer == 480) {
                countdown.setText("EIGHT.");
                countdown.setColor(new Color(57, 198, 0));
            } else if (countdownTimer == 420) {
                countdown.setText("SEVEN.");
                countdown.setColor(new Color(85, 170, 0));
            } else if (countdownTimer == 360) {
                countdown.setText("SIX.");
                countdown.setColor(new Color(113, 142, 0));
            } else if (countdownTimer == 300) {
                countdown.setText("FIVE.");
                countdown.setColor(new Color(142, 113, 0));
            } else if (countdownTimer == 240) {
                countdown.setText("FOUR.");
                countdown.setColor(new Color(170, 85, 0));
            } else if (countdownTimer == 180) {
                countdown.setText("THREE.");
                countdown.setColor(new Color(198, 57, 0));
            } else if (countdownTimer == 120) {
                countdown.setText("TWO.");
                countdown.setColor(new Color(227, 28, 0));
            } else if (countdownTimer == 60) {
                countdown.setText("ONE.");
                countdown.setColor(new Color(255, 0, 0));
            } else if (countdownTimer <= 0) {
                countdown.setX(175);
                countdown.setText("Passed the test!");
                resetButton.setText("");
                countdown.setColor(new Color(0, 255, 0));
            }

            if(countdownTimer <= -120) {
                this.playLevelScreen.exitWaitingPuzzle();
            }

            instructions.draw(graphicsHandler);
            countdown.draw(graphicsHandler);
            resetButton.draw(graphicsHandler);
        }
    }
    
}
