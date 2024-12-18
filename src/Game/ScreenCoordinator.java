package Game;

import java.security.SecureRandom;

import Engine.DefaultScreen;
import Engine.GraphicsHandler;
import Engine.Screen;
import Screens.*;
import Screens.CaveScreens.*;
import Screens.ForestScreens.*;
import Screens.IceScreens.*;
import Screens.RandomBattleScreens.*;
import Screens.TransitionScreens.*;
import Scripts.EnterRandomBattleScript;
import Scripts.SimpleTextScript;
import Level.Map;
import Level.MusicManager;



/*
 * Based on the current game state, this class determines which Screen should be shown
 * There can only be one "currentScreen", although screens can have "nested" screens
 */
public class ScreenCoordinator extends Screen {
	static final SecureRandom RANDOM_NUMBERS = new SecureRandom();
	
	// Count, cap, and constants for random battles
	public static int randomBattleStepCap;
	public static float randomBattleStepCounter;
	static final int RANDOM_BATTLE_STEP_MINIMUM = 700;
	static final int RANDOM_BATTLE_STEP_MAXIMUM = 1200;

	// -1 for error checking
	protected int nextRandomBattle = -1;
	
	static final int NUM_OF_MAJOR_SCREENS = 18; // The number of major screens to be loaded
	static final int NUM_OF_RANDOM_BATTLES = 2;
	
	// Index for each major screen
	static final int CREDITS_INDEX = 0;
	static final int MENU_INDEX = 1;
	static final int SPAWN_INDEX = 2;
	static final int FOREST_ONE_INDEX = 3;
	static final int FOREST_TWO_INDEX = 4;
	static final int FOREST_THREE_INDEX = 5;
	static final int FOREST_CAVE_INDEX = 6;
	static final int CAVE_ONE_INDEX = 7;
	static final int CAVE_TWO_INDEX = 8;
	static final int CAVE_THREE_INDEX = 9;
	static final int CAVE_ICE_INDEX = 10;
	static final int ICE_ONE_INDEX = 11;
	static final int ICE_TWO_INDEX = 12;
	static final int ICE_THREE_INDEX = 13;
	static final int ICE_FOUR_INDEX = 14;
	static final int ICE_FIVE_INDEX = 15;
	static final int KING_FIGHT_INDEX = 16;
	static final int WIN_SCREEN_INDEX = 17;

	// Index for each random battle screen
	static final int RANDOM_BUG_INDEX = 0;
	static final int RANDOM_EYEBAT_INDEX = 1;

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

	private String ending;

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
		majorScreens[CREDITS_INDEX] = new BetterCreditsScreen(this);
		majorScreens[MENU_INDEX] = new TitleScreen(this);
		majorScreens[SPAWN_INDEX] = new PlayLevelScreen(this);
		majorScreens[FOREST_ONE_INDEX] = new ForestOneScreen(this);
		majorScreens[FOREST_TWO_INDEX] = new ForestTwoScreen(this);
		majorScreens[FOREST_THREE_INDEX] = new ForestThreeScreen(this);
		majorScreens[FOREST_CAVE_INDEX] = new ForestCaveScreen(this);
		majorScreens[CAVE_ONE_INDEX] = new CaveOneScreen(this);
		majorScreens[CAVE_TWO_INDEX] = new CaveTwoScreen(this);
		majorScreens[CAVE_THREE_INDEX] = new CaveThreeScreen(this);
		majorScreens[CAVE_ICE_INDEX] = new CaveIceScreen(this);
		majorScreens[ICE_ONE_INDEX] = new IceOneScreen(this);
		majorScreens[ICE_TWO_INDEX] = new IceTwoScreen(this);
		majorScreens[ICE_THREE_INDEX] = new IceThreeScreen(this);
		majorScreens[ICE_FOUR_INDEX] = new IceFourScreen(this);
		majorScreens[ICE_FIVE_INDEX] = new IceFiveScreen(this);
		majorScreens[KING_FIGHT_INDEX] = new KingFightScreen(this);
		majorScreens[WIN_SCREEN_INDEX] = new WinScreen(this);

		// Fill randomBattleScreens
		randomBattleScreens[RANDOM_BUG_INDEX] = new RandomBugBattleScreen(this);
		randomBattleScreens[RANDOM_EYEBAT_INDEX] = new RandomEyebatBattleScreen(this);

		// Initialize each major screen
		for (int index = 0; index < NUM_OF_MAJOR_SCREENS; index++) {
			majorScreens[index].initialize();
		}

		// Set random battle cap and counter to appropriate values
		randomBattleStepCounter = 0;
		randomBattleStepCap = RANDOM_NUMBERS.nextInt(RANDOM_BATTLE_STEP_MINIMUM, RANDOM_BATTLE_STEP_MAXIMUM);		

		ending = "";
	}

	@Override
	public void update() {
		do {
			if (randomBattleStepCounter >= randomBattleStepCap) {
				randomBattleStepCounter = 0;
				randomBattleStepCap = RANDOM_NUMBERS.nextInt(RANDOM_BATTLE_STEP_MINIMUM, RANDOM_BATTLE_STEP_MAXIMUM);
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
						MusicManager.playMusic("VillageSong (online-audio-converter.com).wav");
						break;
					case FOREST_ONE:
						currentScreen = majorScreens[FOREST_ONE_INDEX];
							MusicManager.playMusic("WhimsyWoods (online-audio-converter.com).wav");
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
						MusicManager.playMusic("Cave (online-audio-converter.com) (1).wav");
						break;
					case CAVE_TWO:
						currentScreen = majorScreens[CAVE_TWO_INDEX];
						break;
					case CAVE_THREE:
						currentScreen = majorScreens[CAVE_THREE_INDEX];
						break;
					case CAVE_ICE:
						currentScreen = majorScreens[CAVE_ICE_INDEX];
						break;
					case ICE_ONE:
						currentScreen = majorScreens[ICE_ONE_INDEX];
						//MusicManager.playMusic("WhimsyWoods (online-audio-converter.com).wav");
						break;
					case ICE_TWO:
						currentScreen = majorScreens[ICE_TWO_INDEX];
						break;
					case ICE_THREE:
						currentScreen = majorScreens[ICE_THREE_INDEX];
						break;
					case ICE_FOUR:
						currentScreen = majorScreens[ICE_FOUR_INDEX];
						break;
					case ICE_FIVE:
						currentScreen = majorScreens[ICE_FIVE_INDEX];
						break;
					case KING_FIGHT:
						currentScreen = majorScreens[KING_FIGHT_INDEX];
						break;
					case WIN_SCREEN:
						currentScreen = majorScreens[WIN_SCREEN_INDEX];
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

	@Override
	public Map getMap() {
		return currentScreen.getMap();
	}

	public void exitKingBattleAsVictor() {
        ending = "victor";
		setGameState(GameState.WIN_SCREEN);
		System.out.println(ending);
    }

    public void exitKingBattleAsLoser() {
        ending = "loser";
		setGameState(GameState.WIN_SCREEN);
		System.out.println(ending);
    }

    public void exitKingBattleAsCoward() {
        ending = "coward";
		setGameState(GameState.WIN_SCREEN);
		System.out.println(ending);
    }

	public String getEnding() {
		return ending;
	}

	public void leaveRandomBattle() {
		this.gameState = gameStateBeforeBattle;
	}

	public void enterRandomBattle() {
		int decider = RANDOM_NUMBERS.nextInt(100);
		if (decider < 40) {
			this.nextRandomBattle = RANDOM_EYEBAT_INDEX;
		}
		else {
			this.nextRandomBattle = RANDOM_BUG_INDEX;
		}
		// Keeping this here in case we go back to equal random chances for some reason - John
		// nextRandomBattle = RANDOM_NUMBERS.nextInt(NUM_OF_RANDOM_BATTLES);
		this.gameStateBeforeBattle = gameState;
		getMap().setActiveScript(new EnterRandomBattleScript(this));
	}

	public static void increaseRandomBattleStepCounter() {
		randomBattleStepCounter++;
	}

	public static void increaseRandomBattleStepCounter(float incrementAmount) {
		randomBattleStepCounter += incrementAmount;
	}
}
