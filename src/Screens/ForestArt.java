package Screens;

import Engine.*;
import Level.Map;
import Screens.ForestScreens.ForestOneScreen;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the win level screen
public class ForestArt extends Screen {
    //protected SpriteFont winMessage;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected ForestOneScreen forestOneScreen;

    public ForestArt(ForestOneScreen forestOneScreen) {
        this.forestOneScreen = forestOneScreen;
        initialize();
    }

    @Override
    public void initialize() {
        //winMessage = new SpriteFont("You win!", 350, 239, "Arial", 30, Color.white);
        instructions = new SpriteFont("Press Space to continue", 120, 279,"Arial", 20, Color.white);
        keyLocker.lockKey(Key.SPACE);
        //keyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        // if (Keyboard.isKeyUp(Key.ESC)) {
        //     keyLocker.unlockKey(Key.ESC);
        // }

        // if space is pressed continue in the forest
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            forestOneScreen.update();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        //winMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }

    @Override
    public Map getMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMap'");
    }
}
