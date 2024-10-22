package Engine;

import GameObject.Rectangle;
import Level.Player;
import SpriteFont.SpriteFont;
import Utils.Colors;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;


/*
 * This is where the game loop process and render back buffer is setup
 */
public class GamePanel extends JPanel {
	// loads Screens on to the JPanel
	// each screen has its own update and draw methods defined to handle a "section" of the game.
	private ScreenManager screenManager;

	// used to draw graphics to the panel
	private static GraphicsHandler graphicsHandler;

	private boolean isGamePaused = false;
	private boolean isViewingInventory = false;
	private boolean isPlayingPuzzle = false;
	private SpriteFont pauseLabel;
	private SpriteFont inventoryLabel;
	private static SpriteFont friendshipPointsLabel;
	private KeyLocker keyLocker = new KeyLocker();
	private final Key pauseKey = Key.P;
	private final Key inventoryKey = Key.I;
	private Thread gameLoopProcess;
	private Font arial = new Font("Arial", Font.BOLD, 18);
	private static int inventorySelector = -1;
	private static int inventorySlotsFilled = 0;
	private static String[] itemsInInventory = new String[10];
	private static String itemInUse = "";
	private static boolean isUsingItem = false;
	private ArrayList<String> droppableItems = new ArrayList<>();
	private static boolean shouldDrawFriendshipPoints = false;

	private Key showFPSKey = Key.G;
	private SpriteFont fpsDisplayLabel;
	private boolean showFPS = false;
	private int currentFPS;
	private boolean doPaint;

	// The JPanel and various important class instances are setup here
	public GamePanel() {
		super();
		this.setDoubleBuffered(true);

		// attaches Keyboard class's keyListener to this JPanel
		this.addKeyListener(Keyboard.getKeyListener());

		graphicsHandler = new GraphicsHandler();

		screenManager = new ScreenManager();

		pauseLabel = new SpriteFont("PAUSE", 365, 280, "Arial", 24, Color.white);
		pauseLabel.setOutlineColor(Color.black);
		pauseLabel.setOutlineThickness(2.0f);

		inventoryLabel = new SpriteFont("INVENTORY", 250, ScreenManager.getScreenHeight() + 140, "Arial", 24, Color.white);
		inventoryLabel.setOutlineColor(Color.black);
		inventoryLabel.setOutlineThickness(2.0f);

		friendshipPointsLabel = new SpriteFont("FRIENDSHIP POINTS: " + Player.getFriendshipPoints(), 5, 5, "Arial", 24, Color.white);
		for(int i=0; i<itemsInInventory.length; i++) {
			itemsInInventory[i] = "EMPTY";
		}

		fpsDisplayLabel = new SpriteFont("FPS", 4, 3, "Arial", 12, Color.black);

		currentFPS = Config.TARGET_FPS;

		// this game loop code will run in a separate thread from the rest of the program
		// will continually update the game's logic and repaint the game's graphics
		GameLoop gameLoop = new GameLoop(this);
		gameLoopProcess = new Thread(gameLoop.getGameLoopProcess());
	}

	// this is called later after instantiation, and will initialize screenManager
	public void setupGame() {
		setBackground(Colors.CORNFLOWER_BLUE);
		screenManager.initialize(new Rectangle(getX(), getY(), getWidth(), getHeight()));
	}

	// this starts the timer (the game loop is started here)
	public void startGame() {
		gameLoopProcess.start();
	}

	public ScreenManager getScreenManager() {
		return screenManager;
	}

	public void setCurrentFPS(int currentFPS) {
		this.currentFPS = currentFPS;
	}

	public void setDoPaint(boolean doPaint) {
		this.doPaint = doPaint;
	}

	public void update() {
		updatePauseState();
		updateInventoryState();
		updateShowFPSState();
		updateFriendshipPoints();

		if (!isGamePaused && !isViewingInventory && !isPlayingPuzzle) {
			screenManager.update();
		}
	}

	public static void updateFriendshipPoints() {
		friendshipPointsLabel.setText("FRIENDSHIP POINTS: " + Player.getFriendshipPoints());
	}

	private void updatePauseState() {
		if (Keyboard.isKeyDown(pauseKey) && !keyLocker.isKeyLocked(pauseKey)) {
			isGamePaused = !isGamePaused;
			keyLocker.lockKey(pauseKey);
		}

		if (Keyboard.isKeyUp(pauseKey)) {
			keyLocker.unlockKey(pauseKey);
		}
	}

	private void updateInventoryState() {
		//show inventory
		if (Keyboard.isKeyDown(inventoryKey) && !keyLocker.isKeyLocked(inventoryKey)) {
			isViewingInventory = !isViewingInventory;
			keyLocker.lockKey(inventoryKey);
		}

		if (Keyboard.isKeyUp(inventoryKey)) {
			keyLocker.unlockKey(inventoryKey);
		}

		//inventory navigation
		if(Keyboard.isKeyDown(Key.DOWN) && !keyLocker.isKeyLocked(Key.DOWN) && isViewingInventory) {
			keyLocker.lockKey(Key.DOWN);

			if(inventorySelector < 9) {
				inventorySelector++;
			}
			else {
				inventorySelector = 0;
			}
		}

		if (Keyboard.isKeyUp(Key.DOWN)) {
			keyLocker.unlockKey(Key.DOWN);
		}

		if(Keyboard.isKeyDown(Key.UP) && !keyLocker.isKeyLocked(Key.UP) && isViewingInventory) {
			keyLocker.lockKey(Key.UP);

			if(inventorySelector > 0) {
				inventorySelector--;
			}
			else {
				inventorySelector = 9;
			}
		}

		if (Keyboard.isKeyUp(Key.UP)) {
			keyLocker.unlockKey(Key.UP);
		}

		//drop item
		if(Keyboard.isKeyDown(Key.ONE) && !keyLocker.isKeyLocked(Key.ONE) && inventorySelector >= 0) {
			keyLocker.lockKey(Key.ONE);
			if(!isUsingItem && !itemsInInventory[inventorySelector].isEmpty()) {
				if(droppableItems.contains(itemsInInventory[inventorySelector]))
				{
					removeInventoryItem(itemsInInventory[inventorySelector]);
					System.out.println(itemsInInventory[inventorySelector]);
				}
			}
		}
		if (Keyboard.isKeyUp(Key.ONE)) {
			keyLocker.unlockKey(Key.ONE);
		}

		//use item
		if(Keyboard.isKeyDown(Key.TWO) && !keyLocker.isKeyLocked(Key.TWO) && inventorySelector >= 0) {
			keyLocker.lockKey(Key.TWO);
			isUsingItem = !isUsingItem;
			if(isUsingItem && !itemsInInventory[inventorySelector].isEmpty()) {
				itemInUse = itemsInInventory[inventorySelector];
				System.out.println("Using item");
			} else {
				itemInUse = "";
				System.out.println("No item in use");
			}
		}
		if (Keyboard.isKeyUp(Key.TWO)) {
			keyLocker.unlockKey(Key.TWO);
		}

		//item info
		// if(Keyboard.isKeyDown(Key.THREE) && !keyLocker.isKeyLocked(Key.THREE) && inventorySelector >= 0) {
		// 	keyLocker.lockKey(Key.ONE);
		// 	if(itemsInInventory[inventorySelector].)
		// }
		//
		
	}

	private void updateShowFPSState() {
		if (Keyboard.isKeyDown(showFPSKey) && !keyLocker.isKeyLocked(showFPSKey)) {
			showFPS = !showFPS;
			keyLocker.lockKey(showFPSKey);
		}

		if (Keyboard.isKeyUp(showFPSKey)) {
			keyLocker.unlockKey(showFPSKey);
		}

		fpsDisplayLabel.setText("FPS: " + currentFPS);
	}

	public static void enableDrawFriendshipPoints(boolean enabled) {
		shouldDrawFriendshipPoints = enabled;
	}

	public void draw() {			
		// draw current game state
		screenManager.draw(graphicsHandler);

		if(shouldDrawFriendshipPoints)
		{
			friendshipPointsLabel.draw(graphicsHandler);
		}

		// if game is paused, draw pause gfx over Screen gfx
		if (isGamePaused) {
			pauseLabel.draw(graphicsHandler);
			graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 0, 0, 100));
		}

		if(isViewingInventory) {

			/*for(int inventorySpaces=0; inventorySpaces<10; inventorySpaces++) {
				graphicsHandler.drawFilledRectangleWithBorder((int)(2.5 + ScreenManager.getScreenWidth()/10)*inventorySpaces, ScreenManager.getScreenHeight()/2 - 50, ScreenManager.getScreenWidth()/10, 100, new Color(0, 0, 0), new Color(0, 100, 0), 5);
			}*/

			graphicsHandler.drawFilledRectangleWithBorder(ScreenManager.getScreenWidth() / 2 - 150, ScreenManager.getScreenHeight()/2 - 150, 300, 300, new Color(0, 0, 100), new Color(255, 255, 255), 3);

			for(int inventorySlots=0; inventorySlots<10; inventorySlots++) {
				Color colorToDraw = new Color (0, 0, 255);
				if(inventorySlots == inventorySelector)
				{
					colorToDraw = new Color (0, 255, 0);
				}

				if(isUsingItem && itemsInInventory[inventorySlots].equalsIgnoreCase(itemInUse))
				{
					colorToDraw = new Color(0, 100, 100);
				}
				graphicsHandler.drawFilledRectangleWithBorder((ScreenManager.getScreenWidth() / 2 - 150), ((ScreenManager.getScreenHeight() / 10 - 30) * inventorySlots) + 170, 225, 30, colorToDraw, new Color(255, 255, 255), 3);
			}

			for(int itemToAdd=0; itemToAdd < inventorySlotsFilled; itemToAdd++) {
				String textToDisplay = itemsInInventory[itemToAdd];
				if(itemsInInventory[itemToAdd].equalsIgnoreCase("EMPTY")) {
					textToDisplay = "";
				}
				graphicsHandler.drawString(textToDisplay, (ScreenManager.getScreenWidth() / 2 - 140), ((ScreenManager.getScreenHeight() / 10 - 30) * itemToAdd) + 190, arial, new Color(255, 255, 255));
			}

			graphicsHandler.drawString("Drop", ScreenManager.getScreenWidth()/2 + 90, ScreenManager.getScreenHeight()/2 - 50, arial, new Color(255, 255, 255));
			graphicsHandler.drawString("Use", ScreenManager.getScreenWidth()/2 + 94, ScreenManager.getScreenHeight()/2 + 50, arial, new Color(255, 255, 255));
			//graphicsHandler.drawString("Info", ScreenManager.getScreenWidth()/2 + 95, ScreenManager.getScreenHeight()/2 + 50, arial, new Color(255, 255, 255));

			inventoryLabel.draw(graphicsHandler);
		}

		if(isPlayingPuzzle) {
			//Puzzles.MemoryGameDisplay.startMemoryPuzzle();
		}

		if (showFPS) {
			fpsDisplayLabel.draw(graphicsHandler);
		}
	}
 
	//other classes can use this method to add to the inventory
	public static void addToInventory(String item) {
		if(inventorySlotsFilled < 10) {
			itemsInInventory[inventorySlotsFilled] = item;
			inventorySlotsFilled++;
		}
		else {
			System.out.println("ERROR: INVENTORY FULL"); //PLACEHOLDER: currently, there aren't 10 items, so we don't need anything to occur on screen yet.
		}
	}

	//other classes should call these two methods to determine if the player is using the correct item to do what they are attempting to do
	public static boolean getIsUsingItem() {
		return isUsingItem;
	}

	public static String getItemInUse() {
		return itemInUse;
	}

	//other classes should call this method after an inventory item is used
	public static void removeInventoryItem(String itemToRemove) {
		boolean itemInInventory = false;
		int itemSlot = -1;
		for(int item = 0; item < inventorySlotsFilled; item++) {
			if(!itemsInInventory[item].isEmpty()) {
				if(itemsInInventory[item].equalsIgnoreCase(itemToRemove))
				{
					itemInInventory = true;
					itemSlot = item;
					break;
				}
			}
		}

		if(itemInInventory) {
			itemsInInventory[itemSlot] = "";
			inventorySlotsFilled--;
		} else {
			System.out.println("ERROR: ITEM NOT FOUND"); // i will clean this up later i promise
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (doPaint) {
			// every repaint call will schedule this method to be called
			// when called, it will setup the graphics handler and then call this class's draw method
			graphicsHandler.setGraphics((Graphics2D) g);
			draw();
		}
	}
}
