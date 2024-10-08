package Screens;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.ScreenCoordinator;
import Level.*;
import Maps.BugFightMap;
import Scripts.SimpleTextScript;
import SpriteFont.SpriteFont;

import java.awt.*;

// This is the class for the bug combat screen
public class BugFightScreen extends Screen {
    // Hold playLevelScreen to return to same level screen
    protected PlayLevelScreen playLevelScreen;

    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0;
    protected int menuItemSelected = -1;
    protected SpriteFont win;
    protected SpriteFont lose;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();

    public BugFightScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        this.initialize();
    }

    public void initialize() {
        // setup menu options
        win = new SpriteFont("WIN", 250, 500, "Arial", 30, new Color(49, 207, 240));
        win.setOutlineColor(Color.black);
        win.setOutlineThickness(3);
        lose = new SpriteFont("LOSE", 450, 500, "Arial", 30, new Color(49, 207, 240));
        lose.setOutlineColor(Color.black);
        lose.setOutlineThickness(3);

        // define/setup map
        background = new BugFightMap();
        background.setAdjustCamera(false);
        
        // setup key/menu interactions
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        // if left or right is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.RIGHT) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered++;
        } else if (Keyboard.isKeyDown(Key.LEFT) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered--;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        // if right is pressed on last menu item or left is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 1) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 1;
        }

        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            win.setColor(new Color(255, 215, 0));
            lose.setColor(new Color(49, 207, 240));
            pointerLocationX = 220;
            pointerLocationY = 507;
        } else if (currentMenuItemHovered == 1) {
            win.setColor(new Color(49, 207, 240));
            lose.setColor(new Color(255, 215, 0));
            pointerLocationX = 420;
            pointerLocationY = 507;
        }

        // if space is pressed on menu item, perform action depending on which button was pressed
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 0) { // continue level
                this.playLevelScreen.exitBugBattle();
                Player.gainFriendshipPoints(1);
                this.playLevelScreen.map.setActiveScript(new SimpleTextScript(new String[] {
                    "Alex won!", 
                    "Alex gains friendship points with Otis!", 
                    "But Otis still hates him... too early to change that"}));
                //TODO Figure out why the bug stops walking after this.
            } else if (menuItemSelected == 1) { // restart level
                this.playLevelScreen.resetLevel();
            }
        }

    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        win.draw(graphicsHandler);
        lose.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(49, 207, 240), Color.black, 2);
    }
    
}
