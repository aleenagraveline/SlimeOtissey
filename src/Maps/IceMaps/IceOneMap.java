package Maps.IceMaps;

import Level.Map;
import Level.NPC;
import Tilesets.IceTileset;
import Utils.Point;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.IceTwoTransitionScript;
import Scripts.MapTransitions.CaveIceTransitionScript;

import java.util.ArrayList;

public class IceOneMap extends Map {

    public IceOneMap() {
        super("ice_one_map.txt", new IceTileset());
        this.playerStartPosition = getMapTile(3, 17).getLocation();
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

        // CaveIce transition
        triggers.add(new Trigger(144, 852, 96, 12, new CaveIceTransitionScript()));
        // IceTwo transition
        triggers.add(new Trigger(1140, 336, 12, 96, new IceTwoTransitionScript()));

        return triggers;

    }
    
}
