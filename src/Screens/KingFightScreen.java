package Screens;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
import Maps.KingFightMap;
import Scripts.SimpleTextScript;
import SpriteFont.SpriteFont;

import java.awt.*;

// This is the class for the king combat screen
public class KingFightScreen extends Screen {
    // Hold playLevelScreen to return to same level screen
    //protected PlayLevelScreen playLevelScreen;

    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0;
    protected int menuItemSelected = -1;

    protected SpriteFont attack;
    protected SpriteFont block;
    protected SpriteFont runAway;

    protected boolean attacking;
    protected boolean hasInteracted;

    protected SpriteFont swordAttack;
    protected SpriteFont hammerAttack;
    protected SpriteFont bowAttack;

    protected KingFightMap background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();

    protected int playerHealth;
    protected int playerStrength;
    protected SpriteFont playerHealthDisplay;

    protected SpriteFont critIndicator;
    protected SpriteFont glanceIndicator;
    protected SpriteFont damageIndicator;
    protected boolean crit;
    protected boolean glance;
    
    protected int shakeTimer;
    protected int kingHealth;
    protected int kingStrength;
    protected boolean armored;
    protected boolean flying;
    protected SpriteFont kingHealthDisplay;

    public KingFightScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup combat attributes
        this.playerHealth = PlayLevelScreen.playerHealth;
        playerStrength = 5;
        attacking = false;
        hasInteracted = false;

        shakeTimer = 0;
        kingHealth = 66;
        kingStrength = 6;
        armored = false;
        flying = false;

        // setup menu options
        playerHealthDisplay = new SpriteFont("PLAYER HEALTH: " + this.playerHealth, 50, 50, "Arial", 30, new Color(49, 207, 240));
        playerHealthDisplay.setOutlineColor(Color.black);
        playerHealthDisplay.setOutlineThickness(3);

        kingHealthDisplay = new SpriteFont("KING HEALTH: " + kingHealth, 500, 50, "Arial", 30, new Color(49, 207, 240));
        kingHealthDisplay.setOutlineColor(Color.black);
        kingHealthDisplay.setOutlineThickness(3);

        attack = new SpriteFont("ATTACK", 200, 500, "Arial", 30, new Color(49, 207, 240));
        attack.setOutlineColor(Color.black);
        attack.setOutlineThickness(3);

        block = new SpriteFont("BLOCK", 460, 500, "Arial", 30, new Color(49, 207, 240));
        block.setOutlineColor(Color.black);
        block.setOutlineThickness(3);

        runAway = new SpriteFont("RUN AWAY", 550, 500, "Arial", 30, new Color(49, 207, 240));
        runAway.setOutlineColor(Color.black);
        runAway.setOutlineThickness(3);

        swordAttack = new SpriteFont("SWORD", 100, 500, "Arial", 30, new Color(49, 207, 240));
        swordAttack.setOutlineColor(Color.black);
        swordAttack.setOutlineThickness(3);

        hammerAttack = new SpriteFont("HAMMER", 300, 500, "Arial", 30, new Color(49, 207, 240));
        hammerAttack.setOutlineColor(Color.black);
        hammerAttack.setOutlineThickness(3);

        bowAttack = new SpriteFont("BOW", 550, 500, "Arial", 30, new Color(49, 207, 240));
        bowAttack.setOutlineColor(Color.black);
        bowAttack.setOutlineThickness(3);

        critIndicator = new SpriteFont("CRIT!", 375, 300, "Arial", 30, Color.YELLOW);
        critIndicator.setOutlineColor(Color.ORANGE);
        critIndicator.setOutlineThickness(3);
        crit = false;

        glanceIndicator = new SpriteFont("Glance...", 375, 300, "Arial", 30, Color.LIGHT_GRAY);
        glanceIndicator.setOutlineColor(Color.DARK_GRAY);
        glanceIndicator.setOutlineThickness(3);
        glance = false;

        damageIndicator = new SpriteFont("0", 300, 300, "Arial", 30, Color.RED);
        damageIndicator.setOutlineColor(new Color(120, 6, 6));
        damageIndicator.setOutlineThickness(3);

        // define/setup map
        background = new KingFightMap();
        background.setAdjustCamera(false);
        
        // setup key/menu interactions
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
    }

    public Map getMap() {
        return background;
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        if(hasInteracted) {
            attack.setX(100);
            block.setX(325);
        }

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
        if (currentMenuItemHovered < 0) {
            if (hasInteracted || attacking) {
                currentMenuItemHovered = 2;
            } else {
                currentMenuItemHovered = 1;
            }
        } else if ((hasInteracted || attacking) && currentMenuItemHovered > 2) {
            currentMenuItemHovered = 0;
        } else if ((!hasInteracted && !attacking) && currentMenuItemHovered > 1){
            currentMenuItemHovered = 0;
        }

        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if(!attacking) {
            if (currentMenuItemHovered == 0) {
                attack.setColor(new Color(255, 215, 0));
                block.setColor(new Color(49, 207, 240));
                runAway.setColor(new Color(49, 207, 240));
                pointerLocationX = (int) attack.getX() - 30;
                pointerLocationY = 507;
            } else if (currentMenuItemHovered == 1) {
                attack.setColor(new Color(49, 207, 240));
                block.setColor(new Color(255, 215, 0));
                runAway.setColor(new Color(49, 207, 240));
                pointerLocationX = (int) block.getX() - 30;
                pointerLocationY = 507;
            } else if (currentMenuItemHovered == 2 && hasInteracted) {
                attack.setColor(new Color(49, 207, 240));
                block.setColor(new Color(49, 207, 240));
                runAway.setColor(new Color(255, 215, 0));
                pointerLocationX = (int) runAway.getX() - 30;
                pointerLocationY = 507;
            }
        } else {
            if (currentMenuItemHovered == 0) {
                swordAttack.setColor(new Color(255, 215, 0));
                hammerAttack.setColor(new Color(49, 207, 240));
                bowAttack.setColor(new Color(49, 207, 240));
                pointerLocationX = (int) swordAttack.getX() - 30;
                pointerLocationY = 507;
            } else if (currentMenuItemHovered == 1) {
                swordAttack.setColor(new Color(49, 207, 240));
                hammerAttack.setColor(new Color(255, 215, 0));
                bowAttack.setColor(new Color(49, 207, 240));
                pointerLocationX = (int) hammerAttack.getX() - 30;
                pointerLocationY = 507;
            } else if (currentMenuItemHovered == 2) {
                swordAttack.setColor(new Color(49, 207, 240));
                hammerAttack.setColor(new Color(49, 207, 240));
                bowAttack.setColor(new Color(255, 215, 0));
                pointerLocationX = (int) bowAttack.getX() - 30;
                pointerLocationY = 507;
            }
        }

        // if space is pressed on menu item, perform action depending on which button was pressed
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            menuItemSelected = currentMenuItemHovered;
            keyLocker.lockKey(Key.SPACE);
            if(!attacking) {
                if (menuItemSelected == 0) { // move to attack submenu
                    attacking = true;
                } else if (menuItemSelected == 1) { // block damage
                    playerHealth -= attack(kingStrength) / 2;
                    playerHealthDisplay.setText("PLAYER HEALTH: " + playerHealth);
                    if (playerHealth <= 0) {
                        this.screenCoordinator.exitKingBattleAsLoser();
                    }
                    hasInteracted = true;
                } else if (menuItemSelected == 2) { // exit fight peacefully
                    if (hasInteracted) {
                        PlayLevelScreen.playerHealth = this.playerHealth;
                        this.screenCoordinator.exitKingBattleAsCoward();
                    }
                }
            } else {
                double attack = 0;
                if (menuItemSelected == 0) { // sword atack
                    if (!armored && !flying) {
                        attack = attack(playerStrength) * 1.5;
                        kingHealth -= attack;
                        crit = true;
                    } else if (!flying) {
                        attack = attack(playerStrength);
                        kingHealth -= attack;
                    } else {
                        attack = attack(playerStrength) * 0.5;
                        kingHealth -= attack;
                        glance = true;
                    }

                    hasInteracted = true;
                } else if (menuItemSelected == 1) { // hammer attack
                    if (armored && !flying) {
                        attack = attack(playerStrength) * 1.5;
                        kingHealth -= attack;
                        crit = true;
                    } else if (!flying) {
                        attack = attack(playerStrength);
                        kingHealth -= attack;
                    } else {
                        attack = attack(playerStrength) * 0.5;
                        kingHealth -= attack;
                        glance = true;
                    }

                    hasInteracted = true;
                } else if (menuItemSelected == 2) { // bow attack
                    if (flying) {
                        attack = attack(playerStrength) * 2;
                        kingHealth -= attack;
                        crit = true;
                    } else {
                        attack = attack(playerStrength) * 0.5;
                        kingHealth -= attack;
                        glance = true;
                    }

                    hasInteracted = true;
                }

                kingHealthDisplay.setText("KING HEALTH: " + kingHealth);
                damageIndicator.setText("" + attack);
                if (kingHealth <= 0) {
                    PlayLevelScreen.playerHealth = this.playerHealth;
                    Player.gainFriendshipPoints(100, 100);

                    this.screenCoordinator.exitKingBattleAsVictor();
                } else {
                    playerHealth -= attack(kingStrength);
                    playerHealthDisplay.setText("PLAYER HEALTH: " + playerHealth);
                    if (playerHealth <= 0) {
                        this.screenCoordinator.exitKingBattleAsLoser();
                    }
                }

                attacking = false;
                currentMenuItemHovered = 0;
                shakeTimer = 20;

                int newResistance = background.cycleKingResistance();
                if (newResistance == 0) {
                    armored = false;
                    flying = false;
                } else if (newResistance == 1) {
                    armored = true;
                    flying = false;
                } else {
                    armored = false;
                    flying = true;
                }
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if(shakeTimer > 0) {
            if (shakeTimer % 5 == 0) { background.shakeKing(); }
            background.shakeKing();
            shakeTimer--;
        } else {
            background.unshakeKing();
            crit = false;
            glance = false;
        }
        background.draw(graphicsHandler);

        if(shakeTimer > 0) { damageIndicator.draw(graphicsHandler); }
        if(crit) { critIndicator.draw(graphicsHandler); }
        if(glance) { glanceIndicator.draw(graphicsHandler); }

        playerHealthDisplay.draw(graphicsHandler);
        kingHealthDisplay.draw(graphicsHandler);

        if (!attacking) {
            attack.draw(graphicsHandler);
            block.draw(graphicsHandler);

            if (hasInteracted) {runAway.draw(graphicsHandler);}
        } else {
            swordAttack.draw(graphicsHandler);
            hammerAttack.draw(graphicsHandler);
            bowAttack.draw(graphicsHandler);
        }
        
        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(49, 207, 240), Color.black, 2);
    }

    public int attack(int attackStrength) {
        return (int) (Math.random() * 7 - (attackStrength * 0.6)) + attackStrength;
    }
    
}
