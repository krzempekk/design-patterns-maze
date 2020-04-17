package pl.agh.edu.dp.labirynth.builder;

import pl.agh.edu.dp.util.Vector2D;

public interface MazeBuilder {
    void createRoom(Vector2D position);
    void createDoors(Vector2D position1, Vector2D position2);
}
