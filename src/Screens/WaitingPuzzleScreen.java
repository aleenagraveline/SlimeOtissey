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

    protected SpriteFont countdown;
    protected int countdownTimer;

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
        countdownTimer = 1000;

        // set up countdown displayer
        countdown = new SpriteFont("TEN.", 50, 50, "Arial", 60, new Color(0, 255, 0));
        countdown.setOutlineColor(Color.black);
        countdown.setOutlineThickness(3);

        // define/setup map
        background = new BugFightMap();
        background.setAdjustCamera(false);
        
        // setup key/menu interactions
        keyPressTimer = 0;
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        // if space is pressed on menu item, perform action depending on which button was pressed
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            countdownTimer = 1000;
            countdown.setText("TEN.");
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);

        if(countdownTimer >= -200) {
            countdownTimer--;
            System.out.println("" + countdownTimer);
            if (countdownTimer == 900) {
                countdown.setText("NINE.");
            } else if (countdownTimer == 800) {
                countdown.setText("EIGHT.");
            } else if (countdownTimer == 700) {
                countdown.setText("SEVEN.");
            } else if (countdownTimer == 600) {
                countdown.setText("SIX.");
            } else if (countdownTimer == 500) {
                countdown.setText("FIVE.");
            } else if (countdownTimer == 400) {
                countdown.setText("FOUR.");
            } else if (countdownTimer == 300) {
                countdown.setText("THREE.");
            } else if (countdownTimer == 200) {
                countdown.setText("TWO.");
            } else if (countdownTimer == 100) {
                countdown.setText("ONE.");
            } else if (countdownTimer <= 0) {
                countdown.setText("yipee!");
            }

            if(countdownTimer <= -200) {
                this.playLevelScreen.exitWaitingPuzzle();
            }

            countdown.draw(graphicsHandler);
        }
    }
    
}
