package Screens.IceScreens;

import Engine.*;
import Level.Map;
import SpriteFont.SpriteFont;

import Utils.Colors;

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
        winMessage = new SpriteFont("Alex fell through the ice! But, Otis pulled him out.", 80, 239, "Arial", 30, Colors.BLACK);
        instructions = new SpriteFont("Press SPACE to try again...", 80, 279,"Arial", 20, Colors.BLACK);
        keyLocker.lockKey(Key.SPACE);
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            this.iceFiveScreen.resetLevel();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Colors.ICE_BLUE);
        winMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
