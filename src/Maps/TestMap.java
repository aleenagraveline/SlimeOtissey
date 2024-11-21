package Maps;

import EnhancedMapTiles.Food;
import EnhancedMapTiles.PushableRock;
import Level.*;
import NPCs.Bug;
import NPCs.Dinosaur;
import NPCs.Otis;
import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.MapTransitions.ForestOneTransitionScript;
import Scripts.TestMap.*;
import Tilesets.CommonTileset;
import Tilesets.VillageTileset;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new VillageTileset());
        this.playerStartPosition = getMapTile(4, 8).getLocation();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PushableRock pushableRock = new PushableRock(getMapTile(20, 10).getLocation());
        enhancedMapTiles.add(pushableRock);

        Food apple = new Food(getMapTile(3, 23).getLocation());
        apple.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Walrus walrus = new Walrus(1, getMapTile(16, 14).getLocation().subtractY(40));
        walrus.setInteractScript(new WalrusScript());
        npcs.add(walrus);

        Dinosaur dinosaur = new Dinosaur(2, getMapTile(4, 23).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);
        
        Bug bug = new Bug(3, getMapTile(7, 12).getLocation().subtractX(20));
        bug.setInteractScript(new BugScript());
        npcs.add(bug);

        Otis otis = new Otis(4, getMapTile(5, 8).getLocation().subtractX(20));
        npcs.add(otis);


        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        // story start triggers
        triggers.add(new Trigger(144, 460, 148, 10, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(144, 380, 10, 80, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(292, 380, 10, 80, new LostBallScript(), "hasLostBall"));

        // forest transition trigger
        triggers.add(new Trigger(1140, 1104, 12, 144, new ForestOneTransitionScript()));

        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(7, 7).setInteractScript(new SimpleTextScript("Alex's house"));

        getMapTile(19, 13).setInteractScript(new SimpleTextScript("Walrus's house"));

        getMapTile(11, 23).setInteractScript(new SimpleTextScript("King Lodeon's... Castle..."));

        // Forest sign interaction
        getMapTile(18, 26).setInteractScript(new SimpleTextScript("To Forest:\n>>>>"));

        getMapTile(20, 9).setInteractScript(new TreeScript());
    }
}

