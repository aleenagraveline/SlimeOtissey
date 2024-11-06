package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import SpriteFont.SpriteFont;
import java.awt.Color;

// This class is for the credits screen
public class BetterCreditsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected KeyLocker keyLocker = new KeyLocker();

    protected Sprite bigAlex;
    protected Sprite backgroundArt;

    protected SpriteFont creditsLabel;
    protected SpriteFont createdByLabel1;
    protected SpriteFont createdByLabel2;
    protected SpriteFont createdByLabel3;
    protected SpriteFont createdByLabel4;
    protected SpriteFont createdByLabel5;
    protected SpriteFont returnInstructionsLabel;

    public BetterCreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (big Alex)
        bigAlex = new Sprite(ImageLoader.load("BigAlex.png"));
        bigAlex.setLocation(225, 175);
        backgroundArt = new Sprite(ImageLoader.load("TransitionArt.png"));

        creditsLabel = new SpriteFont("Credits", 10, 107, "Times New Roman", 30, Color.WHITE);
        createdByLabel1 = new SpriteFont("Base game engine code created by Alex Thimineur", 50, 150, "Times New Roman", 20, Color.WHITE);
        createdByLabel2 = new SpriteFont("Game art and music made by MollyAnn O'Malley", 50, 180, "Times New Roman", 20, Color.WHITE);
        createdByLabel3 = new SpriteFont("Inventory and puzzles created by Aleena Graveline", 50, 210, "Times New Roman", 20, Color.WHITE);
        createdByLabel4 = new SpriteFont("Improved combat functionality and more puzzles created by Ryan Barbarito", 50, 240, "Times New Roman", 20, Color.WHITE);
        createdByLabel5 = new SpriteFont("Everything else done by John Caceres", 50, 270, "Times New Roman", 20, Color.WHITE);
        returnInstructionsLabel = new SpriteFont("Press Space to return to the menu", 20, 532, "Times New Roman", 30, Color.WHITE);

        creditsLabel.setOutlineColor(Color.BLACK);
        createdByLabel1.setOutlineColor(Color.BLACK);
        createdByLabel2.setOutlineColor(Color.BLACK);
        createdByLabel3.setOutlineColor(Color.BLACK);
        createdByLabel4.setOutlineColor(Color.BLACK);
        createdByLabel5.setOutlineColor(Color.BLACK);
        returnInstructionsLabel.setOutlineColor(Color.BLACK);

        creditsLabel.setOutlineThickness(2);
        createdByLabel1.setOutlineThickness(1);
        createdByLabel2.setOutlineThickness(1);
        createdByLabel3.setOutlineThickness(1);
        createdByLabel4.setOutlineThickness(1);
        createdByLabel5.setOutlineThickness(1);
        returnInstructionsLabel.setOutlineThickness(2);
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
        creditsLabel.draw(graphicsHandler);
        createdByLabel1.draw(graphicsHandler);
        createdByLabel2.draw(graphicsHandler);
        createdByLabel3.draw(graphicsHandler);
        createdByLabel4.draw(graphicsHandler);
        createdByLabel5.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
