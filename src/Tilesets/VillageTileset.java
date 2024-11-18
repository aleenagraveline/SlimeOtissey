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
public class VillageTileset extends Tileset {

    public VillageTileset() {
        super(ImageLoader.load("updatedVillageTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // grass
        Frame grassFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();
        MapTileBuilder grassTile = new MapTileBuilder(grassFrame);
        mapTiles.add(grassTile);

        // topPath
        Frame topPathFrame = new FrameBuilder(getSubImage(0,0))
                .withScale(tileScale)
                .build();
        MapTileBuilder topPathTile = new MapTileBuilder(topPathFrame);
        mapTiles.add(topPathTile);

        // midPath
        // Frame midPathFrame = new FrameBuilder(getSubImage(0,0))
        //         .withScale(tileScale)
        //         .build();
        // MapTileBuilder midPathTile = new MapTileBuilder(midPathFrame);
        // mapTiles.add(midPathTile);

        // bottomPath
        Frame bottomPathFrame = new FrameBuilder(getSubImage(0,0))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                //.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();
        MapTileBuilder bottomPathTile = new MapTileBuilder(bottomPathFrame);
        mapTiles.add(bottomPathTile);

        //leftPath
        Frame leftPathFrame = new FrameBuilder(getSubImage(0,1))
                .withScale(tileScale)
                .build();
        MapTileBuilder leftPathTile = new MapTileBuilder(leftPathFrame);
        mapTiles.add(leftPathTile);

        //rightPath
        Frame rightPathFrame = new FrameBuilder(getSubImage(0,1))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_VERTICAL)
                //.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();
        MapTileBuilder rightPathTile = new MapTileBuilder(rightPathFrame);
        mapTiles.add(rightPathTile);

        // houseWall
        Frame houseWallFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();
        MapTileBuilder houseWallTile = new MapTileBuilder(grassFrame)
                .withMidBottomLayer(houseWallFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(houseWallTile);

        // fullRoof
        Frame fullRoofFrame = new FrameBuilder(getSubImage(2,0))
                .withScale(tileScale)
                .build();
        MapTileBuilder fullRoofTile = new MapTileBuilder(grassFrame)
                .withMidBottomLayer(fullRoofFrame);
        mapTiles.add(fullRoofTile);

        //leftRoof
        Frame leftHouseRoofFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftHouseRoofTile = new MapTileBuilder(grassFrame)
                .withTopLayer(leftHouseRoofFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(leftHouseRoofTile);

        //rightRoof
        Frame rightRoofFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder rightRoofTile = new MapTileBuilder(grassFrame)
                .withTopLayer(rightRoofFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(rightRoofTile);

        // smallMushrooms
        Frame smallMushroomsFrame = new FrameBuilder(getSubImage(1,1))
                .withScale(tileScale)
                .build();
        MapTileBuilder smallMushroomsTile = new MapTileBuilder(grassFrame)
                .withMidBottomLayer(smallMushroomsFrame);
        mapTiles.add(smallMushroomsTile);

        // leftTreeBase
        Frame leftTreeBaseFrame = new FrameBuilder(getSubImage(5, 0))
                .withScale(tileScale)
                .build();
        MapTileBuilder leftTreeBaseTile = new MapTileBuilder(grassFrame)
                .withMidBottomLayer(leftTreeBaseFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(leftTreeBaseTile);
        
        // rightTreeBase
        Frame rightTreeBaseFrame = new FrameBuilder(getSubImage(5, 1))
                .withScale(tileScale)
                .build();
        MapTileBuilder rightTreeBaseTile = new MapTileBuilder(grassFrame)
                .withMidBottomLayer(rightTreeBaseFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(rightTreeBaseTile);

        // leftTreeTrunk
        Frame leftTreeTrunkFrame = new FrameBuilder(getSubImage(4, 0))
                .withScale(tileScale)
                .build();
        MapTileBuilder leftTreeTrunkTile = new MapTileBuilder(grassFrame)
                .withTopLayer(leftTreeTrunkFrame);
        mapTiles.add(leftTreeTrunkTile);
        
        // rightTreeTrunk
        Frame rightTreeTrunkFrame = new FrameBuilder(getSubImage(5, 2))
                .withScale(tileScale)
                .build();
        MapTileBuilder rightTreeTrunkTile = new MapTileBuilder(grassFrame)
                .withTopLayer(rightTreeTrunkFrame);
        mapTiles.add(rightTreeTrunkTile);

        // leftMostLeaves
        Frame leftMostLeavesFrame = new FrameBuilder(getSubImage(1,4))
                .withScale(tileScale)
                .build();
        MapTileBuilder leftMostLeavesTile = new MapTileBuilder(grassFrame)
                .withTopLayer(leftMostLeavesFrame);
        mapTiles.add(leftMostLeavesTile);

        // rightMostLeaves
        Frame rightMostLeavesFrame = new FrameBuilder(getSubImage(0,4))
                .withScale(tileScale)
                .build();
        MapTileBuilder rightMostLeavesTile = new MapTileBuilder(grassFrame)
                .withTopLayer(rightMostLeavesFrame);
        mapTiles.add(rightMostLeavesTile);

        // treeLeaves01
        Frame treeLeaves01Frame = new FrameBuilder(getSubImage(1,2))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves01Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves01Frame);
        mapTiles.add(treeLeaves01Tile);

        // treeLeaves02
        Frame treeLeaves02Frame = new FrameBuilder(getSubImage(1,3))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves02Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves02Frame);
        mapTiles.add(treeLeaves02Tile);

        // treeLeaves03
        Frame treeLeaves03Frame = new FrameBuilder(getSubImage(2,1))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves03Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves03Frame);
        mapTiles.add(treeLeaves03Tile);

        // treeLeaves04
        Frame treeLeaves04Frame = new FrameBuilder(getSubImage(2,2))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves04Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves04Frame);
        mapTiles.add(treeLeaves04Tile);

        // treeLeaves05
        Frame treeLeaves05Frame = new FrameBuilder(getSubImage(2,3))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves05Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves05Frame);
        mapTiles.add(treeLeaves05Tile);

        // treeLeaves06
        Frame treeLeaves06Frame = new FrameBuilder(getSubImage(2,4))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves06Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves06Frame);
        mapTiles.add(treeLeaves06Tile);

        // treeLeaves07
        Frame treeLeaves07Frame = new FrameBuilder(getSubImage(3,1))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves07Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves07Frame);
        mapTiles.add(treeLeaves07Tile);

        // treeLeaves08
        Frame treeLeaves08Frame = new FrameBuilder(getSubImage(3,2))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves08Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves08Frame);
        mapTiles.add(treeLeaves08Tile);

        // treeLeaves09
        Frame treeLeaves09Frame = new FrameBuilder(getSubImage(3,3))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves09Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves09Frame);
        mapTiles.add(treeLeaves09Tile);

        // treeLeaves10
        Frame treeLeaves10Frame = new FrameBuilder(getSubImage(3,4))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves10Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves10Frame);
        mapTiles.add(treeLeaves10Tile);

        // treeLeaves11
        Frame treeLeaves11Frame = new FrameBuilder(getSubImage(4,1))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves11Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves11Frame);
        mapTiles.add(treeLeaves11Tile);

        // treeLeaves12
        Frame treeLeaves12Frame = new FrameBuilder(getSubImage(4,2))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves12Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves12Frame);
        mapTiles.add(treeLeaves12Tile);

        // treeLeaves13
        Frame treeLeaves13Frame = new FrameBuilder(getSubImage(4,3))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves13Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves13Frame);
        mapTiles.add(treeLeaves13Tile);

        // treeLeaves14
        Frame treeLeaves14Frame = new FrameBuilder(getSubImage(4,4))
                .withScale(tileScale)
                .build();
        MapTileBuilder treeLeaves14Tile = new MapTileBuilder(grassFrame)
                .withTopLayer(treeLeaves14Frame);
        mapTiles.add(treeLeaves14Tile);

        // door
        Frame doorFrame = new FrameBuilder(getSubImage(5, 4))
                .withScale(tileScale)
                .build();

        MapTileBuilder doorTile = new MapTileBuilder(doorFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(doorTile);

        // window thats off center for some reason
        Frame rightWindowFrame = new FrameBuilder(getSubImage(5, 3))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder rightWindowTile = new MapTileBuilder(rightWindowFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(rightWindowTile);

        //outerCornerPath
        Frame outerCornerPathFrame = new FrameBuilder(getSubImage(1,5))
                .withScale(tileScale)
                .build();
        
        MapTileBuilder outerCornerPathTile = new MapTileBuilder(outerCornerPathFrame);

        mapTiles.add(outerCornerPathTile);

        //innerCornerPath
        Frame innerCornerPathFrame = new FrameBuilder(getSubImage(1,6))
                .withScale(tileScale)
                .build();

        MapTileBuilder innerCornerPathTile = new MapTileBuilder(innerCornerPathFrame);
        mapTiles.add(innerCornerPathTile);

        //sign
        Frame signFrame = new FrameBuilder(getSubImage(0, 5))
                .withScale(tileScale)
                .withBounds(1, 2, 14, 14)
                .build();

        MapTileBuilder signTile = new MapTileBuilder(grassFrame)
                .withTopLayer(signFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(signTile);

        //leftHole
        Frame leftHoleFrame = new FrameBuilder(getSubImage(3, 6))
                .withScale(tileScale)
                .build();

        MapTileBuilder leftHoleTile = new MapTileBuilder(grassFrame)
                .withTopLayer(leftHoleFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(leftHoleTile);

        //rightHole
        Frame rightHoleFrame = new FrameBuilder(getSubImage(4, 6))
                .withScale(tileScale)
                .build();

        MapTileBuilder rightHoleTile = new MapTileBuilder(grassFrame)
                .withTopLayer(rightHoleFrame)
                .withTileType(TileType.PASSABLE);

        mapTiles.add(rightHoleTile);

        // updatedRock
        Frame updatedRockFrame = new FrameBuilder(getSubImage(0, 6))
                .withScale(tileScale)
                .build();
        MapTileBuilder updatedRockTile = new MapTileBuilder(grassFrame)
                .withMidBottomLayer(updatedRockFrame)
                .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(updatedRockTile);

        // bottomPath2
        Frame bottomPath2Frame = new FrameBuilder(getSubImage(0,0))
                .withScale(tileScale)
                //.withImageEffect(ImageEffect.FLIP_VERTICAL)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();
        MapTileBuilder bottomPath2Tile = new MapTileBuilder(bottomPath2Frame);
        mapTiles.add(bottomPath2Tile);

        //rightPath2
        Frame rightPath2Frame = new FrameBuilder(getSubImage(0,1))
                .withScale(tileScale)
                //.withImageEffect(ImageEffect.FLIP_VERTICAL)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();
        MapTileBuilder rightPath2Tile = new MapTileBuilder(rightPath2Frame);
        mapTiles.add(rightPath2Tile);

        //innerCornerPath2
        Frame innerCornerPath2Frame = new FrameBuilder(getSubImage(1,6))
                .withScale(tileScale)
                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                .build();

        MapTileBuilder innerCornerPath2Tile = new MapTileBuilder(innerCornerPath2Frame);

        //outerCorner
        Frame outerCornerFrame = new FrameBuilder(getSubImage(2,6))
                .withScale(tileScale)
                //.withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();
        MapTileBuilder outerCornerTile = new MapTileBuilder(outerCornerFrame);
        mapTiles.add(outerCornerTile);

        //innerCorner
        Frame innerCornerFrame = new FrameBuilder(getSubImage(0,6))
                .withScale(tileScale)
                //.withImageEffect(ImageEffect.FLIP_VERTICAL)
                .build();
        MapTileBuilder innerCornerTile = new MapTileBuilder(innerCornerFrame);
        mapTiles.add(innerCornerTile);







        // NOTE: Overlapping trees/special layer combinations starts here
//         MapTileBuilder mixedTreeBase01 = new MapTileBuilder(leftTreeBaseFrame).withMidBottomLayer(rightTreeBaseFrame).withTileType(TileType.NOT_PASSABLE);
//             mapTiles.add(mixedTreeBase01);
//         MapTileBuilder mixedTreeBase02 = new MapTileBuilder(rightTreeBaseFrame).withMidBottomLayer(leftTreeBaseFrame).withTileType(TileType.NOT_PASSABLE);
//             mapTiles.add(mixedTreeBase02);
//         MapTileBuilder mixedTreeTrunk01 = new MapTileBuilder(rightTreeBaseFrame).withMidBottomLayer(leftTreeTrunkFrame).withTopLayer(rightTreeTrunkFrame);
//             mapTiles.add(mixedTreeTrunk01);
//         MapTileBuilder mixedTreeTrunk02 = new MapTileBuilder(leftTreeBaseFrame).withMidBottomLayer(rightTreeTrunkFrame).withTopLayer(leftTreeTrunkFrame);
//             mapTiles.add(mixedTreeTrunk02);
//         MapTileBuilder mixedLeaves01 = new MapTileBuilder(treeLeaves13Frame).withMidBottomLayer(treeLeaves11Frame).withTopLayer(treeLeaves14Frame);
//             mapTiles.add(mixedLeaves01);
//         MapTileBuilder mixedLeaves02 = new MapTileBuilder(treeLeaves13Frame).withTopLayer(treeLeaves12Frame);
//             mapTiles.add(mixedLeaves02);
//         MapTileBuilder mixedLeaves03 = new MapTileBuilder(treeLeaves13Frame).withTopLayer(treeLeaves11Frame);
//             mapTiles.add(mixedLeaves03);
//         //MapTileBuilder mixedLeaves04 = new MapTileBuilder(treeLeaves08Frame).withTopLayer(treeConnectorFrame);
//             //mapTiles.add(mixedLeaves04);
//         MapTileBuilder mixedLeaves05 = new MapTileBuilder(treeLeaves03Frame).withTopLayer(treeLeaves06Frame);
//             mapTiles.add(mixedLeaves05);
//         MapTileBuilder mixedLeaves06 = new MapTileBuilder(treeLeaves05Frame).withTopLayer(treeLeaves03Frame);
//             mapTiles.add(mixedLeaves06);
//         MapTileBuilder mixedLeaves07 = new MapTileBuilder(treeLeaves05Frame).withMidTopLayer(treeLeaves01Frame).withTopLayer(treeLeaves02Frame).withTileType(TileType.NOT_PASSABLE);
//             mapTiles.add(mixedLeaves07);
//         MapTileBuilder mixedLeaves08 = new MapTileBuilder(treeLeaves03Frame).withMidTopLayer(treeLeaves06Frame).withTopLayer(treeLeaves02Frame).withTileType(TileType.NOT_PASSABLE);
//             mapTiles.add(mixedLeaves08);
//         MapTileBuilder mixedLeaves09 = new MapTileBuilder(treeLeaves04Frame).withTopLayer(treeLeaves01Frame).withTileType(TileType.NOT_PASSABLE);
//             mapTiles.add(mixedLeaves09);
//         MapTileBuilder mixedLeaves10 = new MapTileBuilder(treeLeaves05Frame).withMidTopLayer(treeLeaves03Frame).withTopLayer(treeLeaves02Frame).withTileType(TileType.NOT_PASSABLE);
//             mapTiles.add(mixedLeaves10);

//         // Tiles close to path/tree tops
//         MapTileBuilder leaves01AndMushrooms = new MapTileBuilder(grassFrame).withMidBottomLayer(smallMushroomsFrame).withTopLayer(treeLeaves01Frame);
//             mapTiles.add(leaves01AndMushrooms);
//         MapTileBuilder leaves03AndMushrooms = new MapTileBuilder(grassFrame).withMidBottomLayer(smallMushroomsFrame).withTopLayer(treeLeaves03Frame);
//             mapTiles.add(leaves03AndMushrooms);
//         MapTileBuilder leaves07AndTopPath = new MapTileBuilder(topPathFrame).withTopLayer(treeLeaves07Frame);
//             mapTiles.add(leaves07AndTopPath);
//         // MapTileBuilder leaves11AndMidPath = new MapTileBuilder(midPathFrame).withTopLayer(treeLeaves11Frame);
//         //     mapTiles.add(leaves11AndMidPath);
//         // MapTileBuilder leaves12AndMidPath = new MapTileBuilder(midPathFrame).withTopLayer(treeLeaves12Frame);
//         //     mapTiles.add(leaves12AndMidPath);
//         // MapTileBuilder leaves14AndMidPath = new MapTileBuilder(midPathFrame).withTopLayer(treeLeaves14Frame);
//         //     mapTiles.add(leaves14AndMidPath);
//         MapTileBuilder leftTrunkAndBottomPath = new MapTileBuilder(bottomPathFrame).withTopLayer(leftTreeTrunkFrame);
//             mapTiles.add(leftTrunkAndBottomPath);
//         MapTileBuilder rightTrunkAndBottomPath = new MapTileBuilder(bottomPathFrame).withTopLayer(rightTreeTrunkFrame);
//             mapTiles.add(rightTrunkAndBottomPath);
//         MapTileBuilder leaves02OverLeaves01 = new MapTileBuilder(grassFrame).withMidTopLayer(treeLeaves01Frame).withTopLayer(treeLeaves02Frame);
//             mapTiles.add(leaves02OverLeaves01);
//         MapTileBuilder leaves02OverLeftBase = new MapTileBuilder(grassFrame).withMidTopLayer(leftTreeBaseFrame).withTopLayer(treeLeaves02Frame).withTileType(TileType.NOT_PASSABLE);
//             mapTiles.add(leaves02OverLeftBase);
//         MapTileBuilder leaves01OverRightBase = new MapTileBuilder(grassFrame).withMidTopLayer(rightTreeBaseFrame).withTopLayer(treeLeaves01Frame).withTileType(TileType.NOT_PASSABLE);
//             mapTiles.add(leaves01OverRightBase);
//         MapTileBuilder leaves01OverLeftBase = new MapTileBuilder(grassFrame).withMidTopLayer(leftTreeBaseFrame).withTopLayer(treeLeaves01Frame).withTileType(TileType.NOT_PASSABLE);
//             mapTiles.add(leaves01OverLeftBase);
//         MapTileBuilder leavesAndRightBase = new MapTileBuilder(grassFrame).withMidBottomLayer(rightTreeBaseFrame).withMidTopLayer(treeLeaves01Frame).withTopLayer(treeLeaves02Frame).withTileType(TileType.NOT_PASSABLE);
//             mapTiles.add(leavesAndRightBase);

//         // Special Tiles for forestTwo
//         MapTileBuilder mixedLeaves101 = new MapTileBuilder(grassFrame).withMidTopLayer(treeLeaves13Frame).withTopLayer(treeLeaves14Frame);
//             mapTiles.add(mixedLeaves101);
//         MapTileBuilder mixedLeaves102 = new MapTileBuilder(grassFrame).withMidTopLayer(treeLeaves06Frame).withTopLayer(treeLeaves02Frame);
//             mapTiles.add(mixedLeaves102);
//         MapTileBuilder mixedLeaves103 = new MapTileBuilder(treeLeaves05Frame).withTopLayer(treeLeaves01Frame);
//                 mapTiles.add(mixedLeaves103);
//         MapTileBuilder leaves01OnBottomPath = new MapTileBuilder(bottomPathFrame).withTopLayer(treeLeaves01Frame);
//                 mapTiles.add(leaves01OnBottomPath);
//         MapTileBuilder leaves02OnBottomPath = new MapTileBuilder(bottomPathFrame).withTopLayer(treeLeaves02Frame);
//                 mapTiles.add(leaves02OnBottomPath);

        return mapTiles;
    }
}
