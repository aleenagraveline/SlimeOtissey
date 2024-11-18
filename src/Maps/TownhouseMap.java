package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.Food;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import NPCs.Dinosaur;
import NPCs.Otis;
import Scripts.MapTransitions.SpawnTransitionScript;
import Scripts.TestMap.FoodScript;
import Scripts.TestMap.FoodSignScript;
import Scripts.TestMap.OtisHouseScript;
import Scripts.TestMap.TownhouseSignScript;
import Tilesets.CommonTileset;

public class TownhouseMap extends Map{

    public TownhouseMap() {
        super("townhouse_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(12, 2).getLocation().subtractX(12).subtractY(24); // Position below door in house
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Dinosaur dinosaur = new Dinosaur(1, getMapTile(6, 6).getLocation());
        dinosaur.setInteractScript(new OtisHouseScript());
        npcs.add(dinosaur);
        
        Otis otis = new Otis(4, getMapTile(14, 2).getLocation().subtractX(20));
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        Food apple = new Food(getMapTile(10, 10).getLocation());
        apple.setInteractScript(new FoodScript());
        Food apple2 = new Food(getMapTile(12, 10).getLocation());
        apple2.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);
        enhancedMapTiles.add(apple2);       
        return enhancedMapTiles;
    }

    @Override
    public void loadScripts() {
        // door script
        getMapTile(12, 1).setInteractScript(new SpawnTransitionScript());

        // sign script
        getMapTile(5,2).setInteractScript(new TownhouseSignScript());

        // food sign script
        Level.Script temp = new FoodSignScript();
        getMapTile(9, 7).setInteractScript(temp);

        System.out.println("Loading");
    }

}
