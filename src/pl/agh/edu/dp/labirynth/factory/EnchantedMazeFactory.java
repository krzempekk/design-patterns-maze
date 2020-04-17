package pl.agh.edu.dp.labirynth.factory;

import pl.agh.edu.dp.labirynth.elements.*;
import pl.agh.edu.dp.util.Vector2D;

public class EnchantedMazeFactory extends MazeFactory {
    private static EnchantedMazeFactory instance;

    protected EnchantedMazeFactory() { }

    public static MazeFactory getInstance() {
        if(EnchantedMazeFactory.instance == null)
            EnchantedMazeFactory.instance = new EnchantedMazeFactory();
        return EnchantedMazeFactory.instance;
    }

    @Override
    public Room createRoom(Vector2D position) {
        double d = Math.random();
        if(d < 0.1)
            return new SwitchingRoom(position);
        if(d < 0.2)
            return new BombedRoom(position);
        return new Room(position);
    }

    @Override
    public Wall createWall(Room room) {
        double d = Math.random();
        if(d < 0.1)
            return new PhantomWall(room);
        if(d < 0.2)
            return new OneWayWall(room);
        if(d < 0.3)
            return new BombedWall(room);
        return new Wall(room);
    }

    @Override
    public Door createDoors(Room room1, Room room2) {
        return super.createDoors(room1, room2);
    }
}
