package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class CaveTileset extends Tileset {

    public CaveTileset() {
        super(ImageLoader.load("CaveTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // topPath
        Frame topPathFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();
        MapTileBuilder topPathTile = new MapTileBuilder(topPathFrame);
        mapTiles.add(topPathTile);

        // bottomPath
        Frame bottomPathFrame = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();
        MapTileBuilder bottomPathTile = new MapTileBuilder(bottomPathFrame);
        mapTiles.add(bottomPathTile);

        // caveFloor
        Frame caveFloorFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();
        MapTileBuilder caveFloorTile = new MapTileBuilder(caveFloorFrame);
        mapTiles.add(caveFloorTile);

        // caveFloorWall
        Frame caveFloorWallFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();
        MapTileBuilder caveFloorWallTile = new MapTileBuilder(caveFloorWallFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(caveFloorWallTile);

        // caveWall
        Frame caveWallFrame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();
        MapTileBuilder caveWallTile = new MapTileBuilder(caveWallFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(caveWallTile);

        // blueCrystal
        Frame blueCrystalFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();
        MapTileBuilder blueCrystalTile = new MapTileBuilder(caveFloorFrame)
                .withMidBottomLayer(blueCrystalFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(blueCrystalTile);

        // blueShards
        Frame blueShardsFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();
        MapTileBuilder blueShardsTile = new MapTileBuilder(caveFloorFrame)
                .withMidBottomLayer(blueShardsFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(blueShardsTile);

        // pinkCrystal
        Frame pinkCrystalFrame = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .build();
        MapTileBuilder pinkCrystalTile = new MapTileBuilder(caveFloorFrame)
                .withMidBottomLayer(pinkCrystalFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(pinkCrystalTile);

        // pinkShards
        Frame pinkShardsFrame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .build();
        MapTileBuilder pinkShardsTile = new MapTileBuilder(caveFloorFrame)
                .withMidBottomLayer(pinkShardsFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(pinkShardsTile);

        // updatedRock
        Frame updatedRockFrame = new FrameBuilder(getSubImage(1, 4))
                .withScale(tileScale)
                .build();
        MapTileBuilder updatedRockTile = new MapTileBuilder(caveFloorFrame)
                .withMidBottomLayer(updatedRockFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(updatedRockTile);

        // pebblesOn
        Frame pebblesFrame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();
        MapTileBuilder pebblesTile = new MapTileBuilder(caveFloorFrame)
                .withMidBottomLayer(pebblesFrame);
        mapTiles.add(pebblesTile);

        // wall frame used to create overhang
        MapTileBuilder overhangTile = new MapTileBuilder(caveFloorFrame)
                .withTopLayer(caveWallFrame)
                .withTileType(TileType.PASSABLE);
        mapTiles.add(overhangTile);

        return mapTiles;
    }
}
