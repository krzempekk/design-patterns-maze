package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.factory.MazeFactory;
import pl.agh.edu.dp.util.Direction;
import pl.agh.edu.dp.util.Vector2D;
import pl.agh.edu.dp.labirynth.builder.*;

import java.util.HashSet;

public class MazeGame {
    private MazeBuilder builder;
    private final Vector2D lowerLeft;
    private final Vector2D upperRight;

    public MazeGame(Vector2D lowerLeft, Vector2D upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public Maze createMaze(StandardMazeBuilder builder){
        builder.createRoom(new Vector2D(1, 1));
        builder.createRoom(new Vector2D(1, 2));
        builder.createDoors(new Vector2D(1, 1), new Vector2D(1, 2));
        builder.createRoom(new Vector2D(2, 1));
        builder.createRoom(new Vector2D(2, 2));
        builder.createDoors(new Vector2D(1,2), new Vector2D(2,2));
        builder.createDoors(new Vector2D(2,1), new Vector2D(2,2));

        return builder.getCurrentMaze();
    }

    public void generateMaze(HashSet<Vector2D> visited, Vector2D currentPos) {
        int notVisitedNeighbors = 0;
        for(Direction dir: Direction.values()) {
            Vector2D pos = currentPos.add(dir.toVector());
            if(!visited.contains(pos) && !pos.precedes(this.lowerLeft) && !pos.follows(this.upperRight)) notVisitedNeighbors++;
        }
        if(notVisitedNeighbors == 0) return;

        Direction dir;
        Vector2D newPos;

        while (notVisitedNeighbors > 0) {
            do {
                dir = Direction.random();
                newPos = currentPos.add(dir.toVector());
            } while(visited.contains(newPos) || newPos.precedes(this.lowerLeft) || newPos.follows(this.upperRight));

            this.builder.createDoors(currentPos, newPos);
            visited.add(newPos);
            this.generateMaze(visited, newPos);

            notVisitedNeighbors = 0;
            for(Direction d: Direction.values()) {
                Vector2D pos = currentPos.add(d.toVector());
                if(!visited.contains(pos) && !pos.precedes(this.lowerLeft) && !pos.follows(this.upperRight)) notVisitedNeighbors++;
            }
        }

    }

    public Maze createMaze(MazeFactory factory) {
        FactoryMazeBuilder builder = new FactoryMazeBuilder(factory);
        this.builder = builder;

        for(int x = lowerLeft.x; x <= upperRight.x; x++) {
            for(int y = lowerLeft.y; y <= upperRight.y; y++) {
                builder.createRoom(new Vector2D(x, y));
            }
        }

        HashSet<Vector2D> visited = new HashSet<>();
        Vector2D currentPos = new Vector2D(5, 5);
        this.generateMaze(visited, currentPos);

        return builder.getCurrentMaze();
    }
}
