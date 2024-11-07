package Maps.IceMaps;

import Level.Map;
import Level.NPC;
import Tilesets.IceTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.IceFourTransitionScript;

import java.util.ArrayList;

public class IceFiveMap extends Map {

    public IceFiveMap() {
        super("ice_five_map.txt", new IceTileset());
        this.playerStartPosition = getMapTile(21, 4).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        // Otis otis = new Otis(1, getMapTile(0,18).getLocation().subtractX(12)); // Change coordinates as needed to place Otis next to the player
        // npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // IceFour transition
        triggers.add(new Trigger(1044, 192, 12, 96, new IceFourTransitionScript()));

        return triggers;

    }
    
}
