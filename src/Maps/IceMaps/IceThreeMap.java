package Maps.IceMaps;

import Level.Map;
import Level.NPC;
import Tilesets.IceTileset;
import Utils.Point;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.IceFourTransitionScript;
import Scripts.MapTransitions.IceTwoTransitionScript;

import java.util.ArrayList;

public class IceThreeMap extends Map {

    public IceThreeMap() {
        super("ice_three_map.txt", new IceTileset());
        this.playerStartPosition = getMapTile(0, 4).getLocation();
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
        
        // IceTwo transition
        triggers.add(new Trigger(0, 192, 12, 96, new IceTwoTransitionScript()));
        // IceFour transition
        triggers.add(new Trigger(432, 1428, 96, 12, new IceFourTransitionScript()));

        return triggers;

    }
    
}
