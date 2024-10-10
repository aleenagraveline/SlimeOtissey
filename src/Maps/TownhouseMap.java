package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import NPCs.Dinosaur;
import Scripts.MapTransitions.SpawnTransitionScript;
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

        return npcs;
    }

    @Override
    public void loadScripts() {
        // door script
        getMapTile(12, 1).setInteractScript(new SpawnTransitionScript());

        // sign script
        getMapTile(5,2).setInteractScript(new TownhouseSignScript());
    }

}
