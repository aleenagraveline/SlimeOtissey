package Screens;

import Engine.*;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Player;
import Maps.TestMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the win level screen
public class WinScreen extends Screen {
    protected SpriteFont winMessage;
    protected KeyLocker keyLocker = new KeyLocker();
    protected ScreenCoordinator screenCoordinator;

    public WinScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public Map getMap() {
        return new TestMap();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("You win!", 350, 239, "Arial", 30, Color.white);
        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
        if (screenCoordinator.getEnding().equals("victor")) {
            if (Player.getFriendshipPoints() >= 150) { // best ending
                winMessage.setText("best ending [placeholder]");
            } else { // good ending
                winMessage.setText("good ending [placeholder]");
            }
        } else if (screenCoordinator.getEnding().equals("loser")) {
            if (Player.getFriendshipPoints() >= 150) { // okay ending
                winMessage.setText("okay ending [placeholder]");
            } else { // worst ending
                winMessage.setText("worst ending [placeholder]");
            }
        } else if (screenCoordinator.getEnding().equals("coward")) {
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
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        winMessage.draw(graphicsHandler);
    }
}
