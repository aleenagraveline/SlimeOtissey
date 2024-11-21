package Tilesets;

import Builders.MapTileBuilder;
import Engine.ImageLoader;
import Level.Tileset;

import java.util.ArrayList;

// This class allows two tilesets to be combined
public class ForestCaveTileset extends Tileset {
    public static ForestTileset forestTiles = new ForestTileset();
    public static CaveTileset caveTiles = new CaveTileset();

    public ForestCaveTileset() {
        super(ImageLoader.load("CommonTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        mapTiles.addAll(forestTiles.defineTiles());
        mapTiles.addAll(caveTiles.defineTiles());

        return mapTiles;
    }

}