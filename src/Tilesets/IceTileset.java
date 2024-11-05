package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class IceTileset extends Tileset {

    public IceTileset() {
        super(ImageLoader.load("IceTileset.png"), 16, 16, 3);
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

        // snowFloor
        Frame snowFloorFrame = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();
        MapTileBuilder snowFloorTile = new MapTileBuilder(snowFloorFrame);
        mapTiles.add(snowFloorTile);

        // iceTile
        Frame iceTileFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();
        MapTileBuilder iceTile = new MapTileBuilder(iceTileFrame);
        mapTiles.add(iceTile);

        // crackedIce
        Frame crackedIceFrame = new FrameBuilder(getSubImage(0, 4))
                .withScale(tileScale)
                .build();
        MapTileBuilder crackedIceTile = new MapTileBuilder(crackedIceFrame);
        mapTiles.add(crackedIceTile);
        
        // verticalPathLeft
        Frame verticalPathLeftFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();
        MapTileBuilder verticalPathLeftTile = new MapTileBuilder(verticalPathLeftFrame);
        mapTiles.add(verticalPathLeftTile);

        // endTopPath
        Frame endTopPathFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();
        MapTileBuilder endTopPathTile = new MapTileBuilder(endTopPathFrame);
        mapTiles.add(endTopPathTile);

        // iceCrystal
        Frame iceCrystalFrame = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .build();
        MapTileBuilder iceCrystalTile = new MapTileBuilder(snowFloorFrame)
                .withMidBottomLayer(iceCrystalFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(iceCrystalTile);

        // iceHole
        Frame iceHoleFrame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .build();
        MapTileBuilder iceHoleTile = new MapTileBuilder(snowFloorFrame);
        mapTiles.add(iceHoleTile);

        // snowRock
        Frame snowRockFrame = new FrameBuilder(getSubImage(1, 4))
                .withScale(tileScale)
                .build();
        MapTileBuilder snowRockTile = new MapTileBuilder(snowFloorFrame)
                .withMidBottomLayer(snowRockFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(snowRockTile);

        // icePebbles
        Frame icePebblesFrame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();
        MapTileBuilder icePebblesTile = new MapTileBuilder(snowFloorFrame)
                .withMidBottomLayer(icePebblesFrame);
        mapTiles.add(icePebblesTile);

        // endBottomPath
        Frame endBottomPathFrame = new FrameBuilder(getSubImage(2, 1))
                .withScale(tileScale)
                .build();
        MapTileBuilder endBottomPathTile = new MapTileBuilder(endBottomPathFrame);
        mapTiles.add(endBottomPathTile);

        // treeOne
        Frame treeOneFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeOneTile = new MapTileBuilder(treeOneFrame);
        mapTiles.add(treeOneTile);

        // treeTwo
        Frame treeTwoFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeTwoTile = new MapTileBuilder(treeTwoFrame);
        mapTiles.add(treeTwoTile);

        // treeThree
        Frame treeThreeFrame = new FrameBuilder(getSubImage(2, 4))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeThreeTile = new MapTileBuilder(treeThreeFrame);
        mapTiles.add(treeThreeTile);

        // treeFour
        Frame treeFourFrame = new FrameBuilder(getSubImage(3, 2))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeFourTile = new MapTileBuilder(treeFourFrame);
        mapTiles.add(treeFourTile);

        // treeFive
        Frame treeFiveFrame = new FrameBuilder(getSubImage(3, 3))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeFiveTile = new MapTileBuilder(treeFiveFrame);
        mapTiles.add(treeFiveTile);

        // treeSix
        Frame treeSixFrame = new FrameBuilder(getSubImage(3,4))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeSixTile = new MapTileBuilder(treeSixFrame);
        mapTiles.add(treeSixTile);

        // treeSeven
        Frame treeSevenFrame = new FrameBuilder(getSubImage(4,2))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeSevenTile = new MapTileBuilder(treeSevenFrame);
        mapTiles.add(treeSevenTile);

        // treeEight
        Frame treeEightFrame = new FrameBuilder(getSubImage(4,3))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeEightTile = new MapTileBuilder(treeEightFrame);
                //.withMidBottomLayer(treeEightFrame)
                //.withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(treeEightTile);

        // treeNine
        Frame treeNineFrame = new FrameBuilder(getSubImage(4,4))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeNineTile = new MapTileBuilder(treeNineFrame);
                //.withMidBottomLayer(treeNineFrame)
                //.withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(treeNineTile);

        // sign
        Frame signFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .withBounds(1, 2, 14, 14)
                .build();
        MapTileBuilder signTile = new MapTileBuilder(signFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(signTile);

        // verticalPathRight
        Frame verticalPathRightFrame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();
        MapTileBuilder verticalPathRightTile = new MapTileBuilder(verticalPathRightFrame);
        mapTiles.add(verticalPathRightTile);

        // endLeftPath
        Frame endLeftPathFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();
        MapTileBuilder endLeftPathTile = new MapTileBuilder(endLeftPathFrame);
        mapTiles.add(endLeftPathTile);

        // endRightPath
        Frame endRightPathFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();
        MapTileBuilder endRightPathTile = new MapTileBuilder(endRightPathFrame);
        mapTiles.add(endRightPathTile);
        
        return mapTiles;
    }


}


