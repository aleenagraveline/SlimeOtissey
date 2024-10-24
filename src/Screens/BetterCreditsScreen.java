package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;

// This class is for the credits screen
public class BetterCreditsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected KeyLocker keyLocker = new KeyLocker();
    protected Sprite bigAlex;
    protected Sprite backgroundArt;

    public BetterCreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (big Alex)
        bigAlex = new Sprite(ImageLoader.load("BigAlex.png"));
        bigAlex.setLocation(225, 175);
        backgroundArt = new Sprite(ImageLoader.load("TransitionArt.png"));
        
    }

    public void update() {

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // if space is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        backgroundArt.draw(graphicsHandler);
        bigAlex.draw(graphicsHandler);
    }
}
