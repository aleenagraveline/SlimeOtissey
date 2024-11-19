package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import Level.Map;
import Maps.TestMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This is the class for the main menu screen
public class TitleScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont newGame;
    // maybe for sprint 6... :(
    //protected SpriteFont loadGame;
    protected SpriteFont credits;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();
    protected Sprite logo;

    public TitleScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        newGame = new SpriteFont("PLAY GAME", 125, 450, "Arial", 30, new Color(1,215,1));
        newGame.setOutlineColor(new Color(10,89,10));
        newGame.setOutlineThickness(3);

        /*loadGame = new SpriteFont("LOAD GAME", 200, 223, "Arial", 30, new Color(49, 207, 240));
        loadGame.setOutlineColor(Color.black);
        loadGame.setOutlineThickness(3);*/

        credits = new SpriteFont("CREDITS", 450, 450, "Arial", 30, new Color(1,215,1));
        credits.setOutlineColor(new Color(10,89,10));
        credits.setOutlineThickness(3);

        logo = new Sprite(ImageLoader.load("SOLogo.png"));
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
    }

    public Map getMap() {
        return new TestMap();
    }

    public void update() {

        // if S or W is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
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

        // if S is pressed on last menu item or W is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 1) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 1;
        }

        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            newGame.setColor(new Color(1,215,1));
            //loadGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(10,89,10));
            pointerLocationX = 100;
            pointerLocationY = 457;
        } else if (currentMenuItemHovered == 1) {
            newGame.setColor(new Color(10,89,10));
           // loadGame.setColor(new Color(255, 215, 0));
            credits.setColor(new Color(1,215,1));
            pointerLocationX = 425;
            pointerLocationY = 457;
        }/* else if (currentMenuItemHovered == 2) {
            newGame.setColor(new Color(49, 207, 240));
            loadGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(255, 215, 0));
            pointerLocationX = 170;
            pointerLocationY = 330;
        }*/

        // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 0) {
                screenCoordinator.setGameState(GameState.SPAWN);
            } /*else if (menuItemSelected == 1) {
                screenCoordinator.setGameState(GameState.SPAWN);
            }*/ else if (menuItemSelected == 1) {
                screenCoordinator.setGameState(GameState.CREDITS);
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        logo.draw(graphicsHandler);
        newGame.draw(graphicsHandler);
        //loadGame.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(1,215,1), Color.black, 2);
    }
}
