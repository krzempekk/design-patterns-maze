package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.elements.Room;
import pl.agh.edu.dp.util.Direction;
import pl.agh.edu.dp.util.Vector2D;

public class Player {
    private final Maze maze;
    private Vector2D position;

    public Player(Vector2D position, Maze maze) {
        this.position = position;
        this.maze = maze;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void move(Direction direction)  {
        Room room = this.maze.getRoom(this.position);
        Vector2D newPosition = this.position.add(direction.toVector());
        if(room.getSide(direction).enter(room) && maze.getRoom(newPosition).enter(room)) {
            room.leave();
            this.position = newPosition;
        }
    }
}
