package pl.agh.edu.dp.labirynth.builder;

import pl.agh.edu.dp.util.Direction;
import pl.agh.edu.dp.labirynth.Maze;
import pl.agh.edu.dp.labirynth.factory.MazeFactory;
import pl.agh.edu.dp.labirynth.elements.Door;
import pl.agh.edu.dp.labirynth.elements.Room;
import pl.agh.edu.dp.labirynth.elements.Wall;
import pl.agh.edu.dp.util.Vector2D;

public class FactoryMazeBuilder implements MazeBuilder {
    private Maze currentMaze;
    private MazeFactory factory;

    public FactoryMazeBuilder(MazeFactory factory) {
        this.currentMaze = new Maze();
        this.factory = factory;
    }

    public Maze getCurrentMaze() {
        return currentMaze;
    }

    private Direction commonWall(Vector2D from, Vector2D to) {
        Vector2D diff = to.subtract(from);
        return Direction.fromVector(diff);
    }

    @Override
    public void createRoom(Vector2D position) {
        Room room = factory.createRoom(position);
        for(Direction dir: Direction.values()) {
            Wall wall;

            Vector2D neighborPosition = position.add(dir.toVector());
            Room neighborRoom = this.currentMaze.getRoom(neighborPosition);
            if(neighborRoom == null) {
                wall = factory.createWall(room);
            } else {
                wall = neighborRoom.getSide(dir.opposite());
                wall.setRoom2(room);
            }

            room.setSide(dir, wall);
        }
        this.currentMaze.addRoom(room);
    }

    @Override
    public void createDoors(Vector2D position1, Vector2D position2) {
        Room room1 = currentMaze.getRoom(position1);
        Room room2 = currentMaze.getRoom(position2);
        Direction dir1 = this.commonWall(position1, position2);
        if(room1 != null && room2 != null && dir1 != null) {
            Direction dir2 = dir1.opposite();
            Door door = factory.createDoors(room1, room2);
            room1.setSide(dir1, door);
            room2.setSide(dir2, door);
        }
    }
}
