package Game;

import java.security.SecureRandom;

import Engine.DefaultScreen;
import Engine.GraphicsHandler;
import Engine.Screen;
import Screens.CaveOneScreen;
import Screens.CreditsScreen;
import Screens.ForestCaveScreen;
import Screens.ForestOneScreen;
import Screens.ForestThreeScreen;
import Screens.ForestTwoScreen;
import Screens.MenuScreen;
import Screens.PlayLevelScreen;
import Screens.RandomBattleScreens.RandomBugBattleScreen;



/*
 * Based on the current game state, this class determines which Screen should be shown
 * There can only be one "currentScreen", although screens can have "nested" screens
 */
public class ScreenCoordinator extends Screen {
	static final SecureRandom RANDOM_NUMBERS = new SecureRandom();
	
	// Count, cap, and constants for random battles
	protected int randomBattleStepCap;
	protected int randomBattleStepCounter;
	static final int RANDOM_BATTLE_STEP_MINIMUM = 500;
	static final int RANDOM_BATTLE_STEP_MAXIMUM = 1000;

	// -1 for error checking
	protected int nextRandomBattle = -1;
	
	static final int NUM_OF_MAJOR_SCREENS = 8; // The number of major screens to be loaded
	static final int NUM_OF_RANDOM_BATTLES = 1;
	
	// Index for each major screen
	static final int CREDITS_INDEX = 0;
	static final int MENU_INDEX = 1;
	static final int SPAWN_INDEX = 2;
	static final int FOREST_ONE_INDEX = 3;
	static final int FOREST_TWO_INDEX = 4;
	static final int FOREST_THREE_INDEX = 5;
	static final int FOREST_CAVE_INDEX = 6;
	static final int CAVE_ONE_INDEX = 7;

	// Index for each random battle screen
	static final int RANDOM_BUG_INDEX = 0;

	// currently shown Screen
	protected Screen currentScreen = new DefaultScreen();
	// gameState prior to battle
	protected GameState gameStateBeforeBattle;
	// array to keep non-subscreen screens and random battle screens
	protected Screen[] majorScreens = new Screen[NUM_OF_MAJOR_SCREENS];
	protected Screen[] randomBattleScreens = new Screen[NUM_OF_RANDOM_BATTLES];

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
		gameState = GameState.FOREST_CAVE;

		// Fill majorScreens
		majorScreens[CREDITS_INDEX] = new CreditsScreen(this);
		majorScreens[MENU_INDEX] = new MenuScreen(this);
		majorScreens[SPAWN_INDEX] = new PlayLevelScreen(this);
		majorScreens[FOREST_ONE_INDEX] = new ForestOneScreen(this);
		majorScreens[FOREST_TWO_INDEX] = new ForestTwoScreen(this);
		majorScreens[FOREST_THREE_INDEX] = new ForestThreeScreen(this);
		majorScreens[FOREST_CAVE_INDEX] = new ForestCaveScreen(this);
		majorScreens[CAVE_ONE_INDEX] = new CaveOneScreen(this);

		// Fill randomBattleScreens
		randomBattleScreens[RANDOM_BUG_INDEX] = new RandomBugBattleScreen(this);

		// Initialize each major screen
		for (int index = 0; index < NUM_OF_MAJOR_SCREENS; index++) {
			majorScreens[index].initialize();
		}

		// Set random battle cap and counter to appropriate values
		randomBattleStepCounter = 0;
		randomBattleStepCap = RANDOM_NUMBERS.nextInt(RANDOM_BATTLE_STEP_MINIMUM, RANDOM_BATTLE_STEP_MAXIMUM);		
	}

	@Override
	public void update() {
		do {
			if (randomBattleStepCounter >= randomBattleStepCap) {
				this.randomBattleStepCounter = 0;
				this.randomBattleStepCap = RANDOM_NUMBERS.nextInt(RANDOM_BATTLE_STEP_MINIMUM, RANDOM_BATTLE_STEP_MAXIMUM);
				this.enterRandomBattle();
			}

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
					case FOREST_TWO:
						currentScreen = majorScreens[FOREST_TWO_INDEX];
						break;
					case FOREST_THREE:
						currentScreen = majorScreens[FOREST_THREE_INDEX];
						break;
					case FOREST_CAVE:
						currentScreen = majorScreens[FOREST_CAVE_INDEX];
						break;
					case CAVE_ONE:
						currentScreen = majorScreens[CAVE_ONE_INDEX];
						break;
					case RANDOM_BATTLE:
						randomBattleScreens[nextRandomBattle].initialize();
						currentScreen = randomBattleScreens[nextRandomBattle];
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

	public void leaveRandomBattle() {
		this.gameState = gameStateBeforeBattle;
	}

	public void enterRandomBattle() {
		nextRandomBattle = RANDOM_NUMBERS.nextInt(NUM_OF_RANDOM_BATTLES);
		this.gameStateBeforeBattle = gameState;
		this.gameState = GameState.RANDOM_BATTLE;
	}

	public void increaseRandomBattleStepCounter() {
		randomBattleStepCounter++;
	}

	public void increaseRandomBattleStepCounter(int incrementAmount) {
		randomBattleStepCounter += incrementAmount;
	}
}
