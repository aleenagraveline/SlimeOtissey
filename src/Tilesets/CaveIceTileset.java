package Tilesets;

import Builders.MapTileBuilder;
import Engine.ImageLoader;
import Level.Tileset;

import java.util.ArrayList;

// This class allows two tilesets to be combined
public class CaveIceTileset extends Tileset {
    public static CaveTileset caveTiles = new CaveTileset();
    public static IceTileset iceTiles = new IceTileset();

    public CaveIceTileset() {
        super(ImageLoader.load("CommonTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        mapTiles.addAll(caveTiles.defineTiles());
        mapTiles.addAll(iceTiles.defineTiles());

        return mapTiles;
    }

    

}


