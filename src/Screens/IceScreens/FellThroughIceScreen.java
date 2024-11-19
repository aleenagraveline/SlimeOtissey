package Screens.IceScreens;

import Engine.*;
import Level.Map;
import Maps.TestMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the win level screen
public class FellThroughIceScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected IceFiveScreen iceFiveScreen;

    public FellThroughIceScreen(IceFiveScreen iceFiveScreen) {
        this.iceFiveScreen = iceFiveScreen;
        initialize();
    }

    public Map getMap() {
        return this.iceFiveScreen.getMap();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("You died!", 350, 239, "Arial", 30, Color.white);
        instructions = new SpriteFont("Press Space to try again", 120, 279,"Arial", 20, Color.white);
        keyLocker.lockKey(Key.SPACE);
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            this.iceFiveScreen.resetLevel();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        winMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
