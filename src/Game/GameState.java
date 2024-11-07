package Game;

/*
 * This is used by the ScreenCoordinator class to determine which "state" the game is currently in
 */
public enum GameState {
    MENU,  CREDITS, SPAWN, 
    FOREST_ONE, FOREST_TWO, FOREST_THREE, FOREST_CAVE, 
    CAVE_ONE, CAVE_TWO, CAVE_THREE, CAVE_ICE, 
    ICE_ONE, ICE_TWO, ICE_THREE, ICE_FOUR, ICE_FIVE,
    RANDOM_BATTLE
}
