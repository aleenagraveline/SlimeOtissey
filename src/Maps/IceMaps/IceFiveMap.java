package Maps.IceMaps;

import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.IceTileset;
import Level.Trigger;
import NPCs.Otis;
import ScriptActions.ChangeFlagScriptAction;
import Scripts.SimpleTextScript;
import Scripts.StartKingFight;
import Scripts.MapTransitions.IceFourTransitionScript;
import Scripts.OtisInteractionScripts.OtisIceFiveScript;
import Scripts.TestMap.FoodScript;


import java.util.ArrayList;

import EnhancedMapTiles.CrackingIce0;
import EnhancedMapTiles.Food;

public class IceFiveMap extends Map {

    public IceFiveMap() {
        super("ice_five_map.txt", new IceTileset());
        this.playerStartPosition = getMapTile(21, 4).getLocation().subtractX(24);
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Initialize Otis right next to the player
        Otis otis = new Otis(1, getMapTile(21,5).getLocation().subtractX(12)); // Change coordinates as needed to place Otis next to the player
        npcs.add(otis);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // IceFour transition
        triggers.add(new Trigger(1044, 192, 12, 96, new IceFourTransitionScript()));

        triggers.add(new Trigger(996, 144, 10, 240, new OtisIceFiveScript(), "hasEnteredIceFive"));

        return triggers;

    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        for (int row = 3; row < 12; row++) {
            for (int column = 4; column < 13; column++) {
        
                boolean doNotPlace = (row == 4 && column == 6) ||
                    (row == 5 && column == 8) || (row == 8 && column == 5) ||
                    (row == 9 && column == 9);
                
                if (!doNotPlace) {
                    enhancedMapTiles.add(new CrackingIce0(getMapTile(column, row).getLocation()));
                }
            }
        }

        Food apple = new Food(getMapTile(3, 15).getLocation());
        apple.setInteractScript(new FoodScript());
        enhancedMapTiles.add(apple);

        return enhancedMapTiles;
    }

    @Override
    public void loadScripts() {
        getMapTile(6, 26).setInteractScript(new SimpleTextScript("I've lead you right where I need you. \n(Interact with the snow rocks)"));
        getMapTile(1, 7).setInteractScript(new SimpleTextScript("You did it! There is nothing else here."));
        getMapTile(15, 7).setInteractScript(new SimpleTextScript("CAUTION: \nThin Ice (You should step on it)"));
        getMapTile(5, 29).setInteractScript(new StartKingFight());
        getMapTile(4, 29).setInteractScript(new StartKingFight());
    }
    
}
