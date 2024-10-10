package Screens.RandomBattleScreens;

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

// This is the class for the random bug battle screen
public class RandomBugBattleScreen extends Screen {

    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0;
    protected int menuItemSelected = -1;
    protected SpriteFont attack;
    protected SpriteFont pass;
    protected SpriteFont runAway;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();

    protected int playerHealth;
    protected int playerStrength;
    protected SpriteFont playerHealthDisplay;
    
    protected int bugHealth;
    protected int bugStrength;
    protected SpriteFont bugHealthDisplay;

    public RandomBugBattleScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup combat attributes
        playerHealth = 50;
        playerStrength = 5;

        bugHealth = 15;
        bugStrength = 3;

        // setup menu options
        playerHealthDisplay = new SpriteFont("PLAYER HEALTH: " + playerHealth, 50, 50, "Arial", 30, new Color(49, 207, 240));
        playerHealthDisplay.setOutlineColor(Color.black);
        playerHealthDisplay.setOutlineThickness(3);

        bugHealthDisplay = new SpriteFont("BUG HEALTH: " + playerHealth, 500, 50, "Arial", 30, new Color(49, 207, 240));
        bugHealthDisplay.setOutlineColor(Color.black);
        bugHealthDisplay.setOutlineThickness(3);

        attack = new SpriteFont("ATTACK", 100, 500, "Arial", 30, new Color(49, 207, 240));
        attack.setOutlineColor(Color.black);
        attack.setOutlineThickness(3);

        pass = new SpriteFont("PASS TURN", 300, 500, "Arial", 30, new Color(49, 207, 240));
        pass.setOutlineColor(Color.black);
        pass.setOutlineThickness(3);

        runAway = new SpriteFont("RUN AWAY", 550, 500, "Arial", 30, new Color(49, 207, 240));
        runAway.setOutlineColor(Color.black);
        runAway.setOutlineThickness(3);

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

        // if right is pressed on last menu item or left is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 2) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 2;
        }

        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            attack.setColor(new Color(255, 215, 0));
            pass.setColor(new Color(49, 207, 240));
            runAway.setColor(new Color(49, 207, 240));
            pointerLocationX = (int) attack.getX() - 30;
            pointerLocationY = 507;
        } else if (currentMenuItemHovered == 1) {
            attack.setColor(new Color(49, 207, 240));
            pass.setColor(new Color(255, 215, 0));
            runAway.setColor(new Color(49, 207, 240));
            pointerLocationX = (int) pass.getX() - 30;
            pointerLocationY = 507;
        } else if (currentMenuItemHovered == 2) {
            attack.setColor(new Color(49, 207, 240));
            pass.setColor(new Color(49, 207, 240));
            runAway.setColor(new Color(255, 215, 0));
            pointerLocationX = (int) runAway.getX() - 30;
            pointerLocationY = 507;
        }

        // if space is pressed on menu item, perform action depending on which button was pressed
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            menuItemSelected = currentMenuItemHovered;
            keyLocker.lockKey(Key.SPACE);
            if (menuItemSelected == 0) { // continue level
                bugHealth -= attack(playerStrength);
                bugHealthDisplay.setText("BUG HEALTH: " + bugHealth);
                if (bugHealth <= 0) {
                    Player.gainFriendshipPoints(1);
                    this.background.setActiveScript(new SimpleTextScript(new String[] {
                    "Alex won!", 
                    "Alex gains friendship points with Otis!", 
                    "But Otis still hates him... too early to change that"}));
                    this.screenCoordinator.leaveRandomBattle();
                } else {
                    playerHealth -= attack(bugStrength);
                    playerHealthDisplay.setText("PLAYER HEALTH: " + playerHealth);
                    if (playerHealth <= 0) {
                        this.screenCoordinator.leaveRandomBattle();
                    }
                }
            } else if (menuItemSelected == 1) { // restart level
                playerHealth -= attack(bugStrength);
                playerHealthDisplay.setText("PLAYER HEALTH: " + playerHealth);
            } else if (menuItemSelected == 2) {
                this.screenCoordinator.leaveRandomBattle();
            }

            menuItemSelected = -1;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);

        playerHealthDisplay.draw(graphicsHandler);
        bugHealthDisplay.draw(graphicsHandler);

        attack.draw(graphicsHandler);
        pass.draw(graphicsHandler);
        runAway.draw(graphicsHandler);

        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(49, 207, 240), Color.black, 2);
    }

    public int attack(int attackStrength) {
        return (int) (Math.random() * 7 - (attackStrength * 0.6)) + attackStrength;
    }
}