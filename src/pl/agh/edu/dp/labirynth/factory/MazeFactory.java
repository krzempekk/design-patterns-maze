package pl.agh.edu.dp.labirynth.factory;

import pl.agh.edu.dp.labirynth.elements.Door;
import pl.agh.edu.dp.labirynth.elements.Room;
import pl.agh.edu.dp.labirynth.elements.Wall;
import pl.agh.edu.dp.util.Vector2D;

public class MazeFactory {
    private static MazeFactory instance;

    protected MazeFactory() { }

    public static MazeFactory getInstance() {
        if(MazeFactory.instance == null)
            MazeFactory.instance = new MazeFactory();
        return MazeFactory.instance;
    }

    public Room createRoom(Vector2D position) {
        return new Room(position);
    }

    public Wall createWall(Room room) {
        return new Wall(room);
    }

    public Door createDoors(Room room1, Room room2) {
        return new Door(room1, room2);
    }
}
