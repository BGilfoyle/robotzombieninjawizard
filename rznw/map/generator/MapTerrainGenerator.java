package rznw.map.generator;

import rznw.map.Map;
import rznw.map.generator.area.MapArea;
import rznw.map.generator.area.MapAreaCollision;
import rznw.map.generator.area.OpenAreaPadder;
import rznw.map.generator.area.RoomRenderer;
import rznw.utility.RandomNumberGenerator;

import java.util.List;
import java.util.Vector;

public class MapTerrainGenerator
{
    private static int MIN_ROOM_SIZE = 5;
    private static int MAX_ROOM_SIZE = 15;

    private static int ROOM_BUFFER_SIZE = 3;

    private MapAreaCollision collision;
    private OpenAreaPadder padder;
    private RoomRenderer roomRenderer;

    public MapTerrainGenerator()
    {
        this.collision = new MapAreaCollision();
        this.padder = new OpenAreaPadder();
        this.roomRenderer = new RoomRenderer();
    }

    public List<MapArea> generateTerrain(Map map)
    {
        this.roomRenderer.renderVoid(map);

        Vector<MapArea> rooms = new Vector<MapArea>();

        MapArea largestOpenArea = new MapArea(0, 0, Map.NUM_COLUMNS - 1, Map.NUM_ROWS - 1);
        int maxRoomSize = largestOpenArea.getSmallestDimensionSize();

        while (maxRoomSize >= MapTerrainGenerator.MIN_ROOM_SIZE)
        {
            MapArea room = this.generateRoom(map, largestOpenArea, maxRoomSize);
            rooms.add(room);

            largestOpenArea = this.calculateLargestOpenArea(map, rooms);
            maxRoomSize = largestOpenArea.getSmallestDimensionSize();
        }

        return rooms;
    }

    private MapArea generateRoom(Map map, MapArea largestOpenArea, int maxRoomSize)
    {
        maxRoomSize = Math.min(maxRoomSize, MapTerrainGenerator.MAX_ROOM_SIZE);
        int width = RandomNumberGenerator.randomInteger(MapTerrainGenerator.MIN_ROOM_SIZE, maxRoomSize);
        int height = RandomNumberGenerator.randomInteger(MapTerrainGenerator.MIN_ROOM_SIZE, maxRoomSize);

        int startX = RandomNumberGenerator.randomInteger(largestOpenArea.getStartX(), largestOpenArea.getMaxStartXForRectangle(width));
        int startY = RandomNumberGenerator.randomInteger(largestOpenArea.getStartY(), largestOpenArea.getMaxStartYForRectangle(height));
        int endX = startX + width - 1;
        int endY = startY + height - 1;

        this.roomRenderer.renderRoom(map, startX, startY, endX, endY);

        return new MapArea(startX, startY, endX, endY);
    }

    private MapArea calculateLargestOpenArea(Map map, List<MapArea> rooms)
    {
        for (int width = Map.NUM_ROWS; width >= 0; width--)
        {
            for (int row = 0; row < Map.NUM_ROWS - width; row++) {
                for (int column = 0; column < Map.NUM_COLUMNS - width; column++) {

                    MapArea openArea = new MapArea(column, row, column + width - 1, row + width - 1);
                    MapArea openAreaWithBorders = this.padder.addBordersToOpenArea(openArea, MapTerrainGenerator.ROOM_BUFFER_SIZE);
                    if (!this.collision.wallExistsWithinRectangle(map, openAreaWithBorders) && !this.collision.areaFallsWithinAnotherArea(openArea, rooms))
                    {
                        return openArea;
                    }
                }
            }
        }

        return null;
    }
}
