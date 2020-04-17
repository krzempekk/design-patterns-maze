package pl.agh.edu.dp.labirynth.factory;

import pl.agh.edu.dp.labirynth.elements.*;
import pl.agh.edu.dp.util.Vector2D;

public class BombedMazeFactory extends MazeFactory {
    private static BombedMazeFactory instance;

    protected BombedMazeFactory() { }

    public static MazeFactory getInstance() {
        if(BombedMazeFactory.instance == null)
            BombedMazeFactory.instance = new BombedMazeFactory();
        return BombedMazeFactory.instance;
    }

    @Override
    public Room createRoom(Vector2D position) { return new BombedRoom(position); }

    @Override
    public Wall createWall(Room room) { return new BombedWall(room); }

}
