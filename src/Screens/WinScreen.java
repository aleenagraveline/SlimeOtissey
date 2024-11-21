package Screens;

import Engine.*;
import Level.Map;
import Level.Player;
import Maps.TestMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the win level screen
public class WinScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;

    public WinScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    public Map getMap() {
        return new TestMap();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("You win!", 350, 239, "Arial", 30, Color.white);
        instructions = new SpriteFont("Press Space to play again or Escape to go back to the main menu", 120, 500,"Arial", 20, Color.white);
        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
        if (playLevelScreen.getEnding().equals("victor")) {
            if (Player.getFriendshipPoints() >= 150) { // best ending
                winMessage.setText("best ending [placeholder]");
            } else { // good ending
                winMessage.setText("good ending [placeholder]");
            }
        } else if (playLevelScreen.getEnding().equals("loser")) {
            if (Player.getFriendshipPoints() >= 150) { // okay ending
                winMessage.setText("okay ending [placeholder]");
            } else { // worst ending
                winMessage.setText("worst ending [placeholder]");
            }
        } else if (playLevelScreen.getEnding().equals("coward")) {
            if (Player.getFriendshipPoints() >= 150) { // best ending
                winMessage.setText("meh ending [placeholder]");
            } else { // bad ending
                winMessage.setText("bad ending [placeholder]");
            }
        }

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            playLevelScreen.resetLevel();
        } else if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC)) {
            playLevelScreen.goBackToMenu();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        winMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
