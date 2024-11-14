package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class represents a "common" tileset of standard tiles defined in the CommonTileset.png file
public class CaveTileset extends Tileset {

    public CaveTileset() {
        super(ImageLoader.load("updatedCaveTileset.png"), 16, 16, 3);
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
        // outerCorner
        Frame outerCornerFrame = new FrameBuilder(getSubImage(2,4))
                .withScale(tileScale)
                .build();
        MapTileBuilder outerCornerTile = new MapTileBuilder(outerCornerFrame);
        mapTiles.add(outerCornerTile);

        //leftPathEnd
        Frame leftPathEndFrame = new FrameBuilder(getSubImage(3,1))
                .withScale(tileScale)
                .build();
        MapTileBuilder leftPathEndTile = new MapTileBuilder(leftPathEndFrame);
        mapTiles.add(leftPathEndTile);

        //rightPathEnd
        Frame rightPathEndFrame = new FrameBuilder(getSubImage(3,2))
                .withScale(tileScale)
                .build();
        MapTileBuilder rightPathEndTile = new MapTileBuilder(rightPathEndFrame);
        mapTiles.add(rightPathEndTile);

        //leftPathEnd2
        Frame leftPathEnd2Frame = new FrameBuilder(getSubImage(3,1))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();
        MapTileBuilder leftPathEnd2Tile = new MapTileBuilder(leftPathEnd2Frame);
        mapTiles.add(leftPathEnd2Tile);

        //rightPathEnd2
        Frame rightPathEnd2Frame = new FrameBuilder(getSubImage(3,2))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();
        MapTileBuilder rightPathEnd2Tile = new MapTileBuilder(rightPathEnd2Frame);
        mapTiles.add(rightPathEnd2Tile);

        //topPathEnd
        Frame pathEndFrame = new FrameBuilder(getSubImage(3,0))
                .withScale(tileScale)
                .build();
        MapTileBuilder pathEndTile = new MapTileBuilder(pathEndFrame);
        mapTiles.add(pathEndTile);

        //bottomEnd
        Frame bottomEndFrame = new FrameBuilder(getSubImage(4,0))
                .withScale(tileScale)
                .build();
        MapTileBuilder bottomEndTile = new MapTileBuilder(bottomEndFrame);
        mapTiles.add(bottomEndTile);

        //flippedPathEnd
        Frame flippedPathEndFrame = new FrameBuilder(getSubImage(3,0))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();
        MapTileBuilder flippedPathEndTile = new MapTileBuilder(flippedPathEndFrame);
        mapTiles.add(flippedPathEndTile);

        //flippedBottomEnd
        Frame flippedBottomEndFrame = new FrameBuilder(getSubImage(4,0))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();
        MapTileBuilder flippedBottomEndTile = new MapTileBuilder(flippedBottomEndFrame);
        mapTiles.add(flippedBottomEndTile);

        //innerCorner
        Frame innerCornerFrame = new FrameBuilder(getSubImage(3,3))
                .withScale(tileScale)
                .build();
        MapTileBuilder innerCornerTile = new MapTileBuilder(innerCornerFrame);
        mapTiles.add(innerCornerTile);

        //sign
        Frame signFrame = new FrameBuilder(getSubImage(4, 1))
                .withScale(tileScale)
                .withBounds(1, 2, 14, 14)
                .build();

        MapTileBuilder signTile = new MapTileBuilder(caveFloorFrame)
                .withTopLayer(signFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(signTile);

        //leftPath
        Frame leftPathFrame = new FrameBuilder(getSubImage(4,4))
                .withScale(tileScale)
                .build();
        MapTileBuilder leftPathTile = new MapTileBuilder(leftPathFrame);
        mapTiles.add(leftPathTile);

        //rightPath
        Frame rightPathFrame = new FrameBuilder(getSubImage(3,4))
                .withScale(tileScale)
                .build();
        MapTileBuilder rightPathTile = new MapTileBuilder(rightPathFrame);
        mapTiles.add(rightPathTile);

        //outerCornerFlipped
        Frame outerCornerFlippedFrame = new FrameBuilder(getSubImage(2,4))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();
        MapTileBuilder outerCornerFlippedTile = new MapTileBuilder(outerCornerFlippedFrame);
        mapTiles.add(outerCornerFlippedTile);

        //innerCornerFlipped
        Frame innerCornerFlippedFrame = new FrameBuilder(getSubImage(3,3))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();
        MapTileBuilder innerCornerFlippedTile = new MapTileBuilder(innerCornerFlippedFrame);
        mapTiles.add(innerCornerFlippedTile);
        
        //outerCorner2
        Frame outerCorner2Frame = new FrameBuilder(getSubImage(4,2))
                .withScale(tileScale)
                .build();
        MapTileBuilder outerCorner2Tile = new MapTileBuilder(outerCorner2Frame);
        mapTiles.add(outerCorner2Tile);

        //innerCorner2
        Frame innerCorner2Frame = new FrameBuilder(getSubImage(4,3))
                .withScale(tileScale)
                .build();
        MapTileBuilder innerCorner2Tile = new MapTileBuilder(innerCorner2Frame);
        mapTiles.add(innerCorner2Tile);

        //outerCorner2Flipped
                Frame outerCorner2FlippedFrame = new FrameBuilder(getSubImage(4,2))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();
        MapTileBuilder outerCorner2FlippedTile = new MapTileBuilder(outerCorner2FlippedFrame);
        mapTiles.add(outerCorner2FlippedTile);

        //innerCorner2Flipped
        Frame innerCorner2FlippedFrame = new FrameBuilder(getSubImage(4,3))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();
        MapTileBuilder innerCorner2FlippedTile = new MapTileBuilder(innerCorner2FlippedFrame);
        mapTiles.add(innerCorner2FlippedTile);


        return mapTiles;
    }
}
