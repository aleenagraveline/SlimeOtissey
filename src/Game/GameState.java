package Game;

/*
 * This is used by the ScreenCoordinator class to determine which "state" the game is currently in
 */
public enum GameState {
    MENU, SPAWN, 
    FOREST_ONE, FOREST_TWO, FOREST_THREE, FOREST_CAVE, 
    CAVE_ONE, CAVE_TWO, CAVE_THREE, CAVE_ICE, 
    RANDOM_BATTLE, CREDITS
}
