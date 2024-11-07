package Maps.IceMaps;

import Level.Map;
import Level.NPC;
import Tilesets.IceTileset;
import Utils.Point;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.IceOneTransitionScript;
import Scripts.MapTransitions.IceThreeTransitionScript;

import java.util.ArrayList;

public class IceTwoMap extends Map {

    public IceTwoMap() {
        super("ice_two_map.txt", new IceTileset());
        this.playerStartPosition = getMapTile(0, 14).getLocation();
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
        
        // IceOne transition
        triggers.add(new Trigger(0, 672, 12, 96, new IceOneTransitionScript()));
        // IceThree transition
        triggers.add(new Trigger(2148, 240, 12, 96, new IceThreeTransitionScript()));

        return triggers;

    }
    
}
