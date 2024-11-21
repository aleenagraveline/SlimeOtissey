package Maps.TransitionMaps;

import Level.Map;
import Level.NPC;
import Tilesets.CaveIceTileset;
import Level.Trigger;
import NPCs.Otis;
import Scripts.MapTransitions.CaveThreeTransitionScript;
import Scripts.MapTransitions.IceOneTransitionScript;
import Scripts.OtisInteractionScripts.OtisCaveIceScript;
import Scripts.OtisInteractionScripts.OtisForestCaveScript;

import java.util.ArrayList;

public class CaveIceMap extends Map {

    public CaveIceMap() {
        super("cave_ice_map.txt", new CaveIceTileset());
        this.playerStartPosition = getMapTile(0, 11).getLocation();
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        //Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(0,11).getLocation().subtractX(12)); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // CaveThree transition
        triggers.add(new Trigger(0, 528, 12, 96, new CaveThreeTransitionScript()));

        // IceOne transition
        triggers.add(new Trigger (2640, 0, 96, 12, new IceOneTransitionScript()));

        triggers.add(new Trigger(100, 288, 10, 288, new OtisCaveIceScript(), "hasEnteredCaveIce"));

        return triggers;

    }
    
}
