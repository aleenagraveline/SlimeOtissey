package Game;

import Engine.DefaultScreen;
import Engine.GraphicsHandler;
import Engine.Screen;
import Screens.CreditsScreen;
import Screens.ForestOneScreen;
import Screens.MenuScreen;
import Screens.PlayLevelScreen;

/*
 * Based on the current game state, this class determines which Screen should be shown
 * There can only be one "currentScreen", although screens can have "nested" screens
 */
public class ScreenCoordinator extends Screen {
	
	static final int NUM_OF_MAJOR_SCREENS = 4; // The number of major screens to be loaded
	
	// Index for each major screen
	static final int CREDITS_INDEX = 0;
	static final int MENU_INDEX = 1;
	static final int SPAWN_INDEX = 2;
	static final int FOREST_ONE_INDEX = 3;

	// currently shown Screen
	protected Screen currentScreen = new DefaultScreen();

	// array to keep non-subscreen screens
	protected Screen[] majorScreens = new Screen[NUM_OF_MAJOR_SCREENS];

	// keep track of gameState so ScreenCoordinator knows which Screen to show
	protected GameState gameState;
	protected GameState previousGameState;

	public GameState getGameState() {
		return gameState;
	}

	// Other Screens can set the gameState of this class to force it to change the currentScreen
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void initialize() {
		// start game off with Menu Screen
		gameState = GameState.MENU;

		// Fill majorScreens
		majorScreens[CREDITS_INDEX] = new CreditsScreen(this);
		majorScreens[MENU_INDEX] = new MenuScreen(this);
		majorScreens[SPAWN_INDEX] = new PlayLevelScreen(this);
		majorScreens[FOREST_ONE_INDEX] = new ForestOneScreen(this);

		// Initialize each major screen
		for (int index = 0; index < NUM_OF_MAJOR_SCREENS; index++) {
			majorScreens[index].initialize();
		}
	}

	@Override
	public void update() {
		do {
			// if previousGameState does not equal gameState, it means there was a change in gameState
			// this triggers ScreenCoordinator to bring up a new Screen based on what the gameState is
			if (previousGameState != gameState) {
				switch(gameState) {
					case MENU:
						currentScreen = majorScreens[MENU_INDEX];
						break;
					case SPAWN:
						currentScreen = majorScreens[SPAWN_INDEX];
						break;
					case FOREST_ONE:
						currentScreen = majorScreens[FOREST_ONE_INDEX];
						break;
					case CREDITS:
						currentScreen = majorScreens[CREDITS_INDEX];
						break;
				}
			}
			previousGameState = gameState;

			// call the update method for the currentScreen
			currentScreen.update();
		} while (previousGameState != gameState);
	}

	@Override
	public void draw(GraphicsHandler graphicsHandler) {
		// call the draw method for the currentScreen
		currentScreen.draw(graphicsHandler);
	}
}
