package pl.agh.edu.dp.labirynth.builder;

import pl.agh.edu.dp.util.Vector2D;

public class CountingMazeBuilder implements MazeBuilder {
    private int roomsCount;
    private int doorsCount;
    private int wallsCount;

    public int getRoomsCount() { return this.roomsCount; }

    public int getDoorsCount() { return this.doorsCount; }

    public int getWallsCount() { return this.wallsCount; }

    public CountingMazeBuilder() {
        this.roomsCount = 0;
        this.doorsCount = 0;
        this.wallsCount = 0;
    }

    @Override
    public void createRoom(Vector2D position) {
        this.roomsCount++;
        this.wallsCount += 4;
    }

    @Override
    public void createDoors(Vector2D position1, Vector2D position2) {
        this.wallsCount--;
        this.doorsCount++;
    }


}
