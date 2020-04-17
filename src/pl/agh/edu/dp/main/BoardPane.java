package pl.agh.edu.dp.main;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pl.agh.edu.dp.labirynth.*;
import pl.agh.edu.dp.labirynth.elements.MapSite;
import pl.agh.edu.dp.labirynth.elements.Room;
import pl.agh.edu.dp.labirynth.elements.Wall;
import pl.agh.edu.dp.util.Direction;
import pl.agh.edu.dp.util.Vector2D;

import java.util.HashMap;
import java.util.Map;

public class BoardPane extends Pane {
    int width;
    int height;
    Canvas canvas;
    Vector2D lowerLeft;
    Vector2D upperRight;
    Maze maze;
    Player player;
    private final int roomSize;
    private final double playerWidth = (1 / 3.0);
    private final double wallWidth = (1 / 10.0);

    public BoardPane(Maze maze, Player player, int width, int height, Vector2D lowerLeft, Vector2D upperRight) {
        this.width = width;
        this.height = height;
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.canvas = new Canvas(this.width, this.height);
        this.maze = maze;
        this.player = player;
        this.roomSize = this.width / (upperRight.x - lowerLeft.x + 1);
        this.getChildren().add(this.canvas);
    }

    public void setKeyHandlers() {
        this.getScene().addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            Direction direction = null;
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case UP:
                    direction = Direction.North;
                    break;
                case RIGHT:
                    direction = Direction.East;
                    break;
                case DOWN:
                    direction = Direction.South;
                    break;
                case LEFT:
                    direction = Direction.West;
                    break;
            }
            if(direction != null) {
                this.player.move(direction);
                this.update();
            }
            event.consume();
        });
    }

    public void update() {
        this.layoutChildren();
    }

    @Override
    protected void layoutChildren() {
        Platform.runLater(() -> {
            super.layoutChildren();

            GraphicsContext gc = this.canvas.getGraphicsContext2D();
            gc.setFill(Color.color(0, 0, 0));
            gc.fillRect(0, 0, this.width, this.height);

            HashMap<Vector2D, Room> rooms = this.maze.getRooms();
            for (Map.Entry<Vector2D, Room> entry : rooms.entrySet()) {
                Vector2D position = entry.getKey();
                Room room = entry.getValue();

                gc.setFill(room.getColor());
                gc.fillRect(
                        (position.x + this.wallWidth) * this.roomSize,
                        this.height - (position.y + 1 - this.wallWidth) * this.roomSize,
                        this.roomSize * (1 - 2 * this.wallWidth),
                        this.roomSize * (1 - 2 * this.wallWidth)
                );

                MapSite mapSite;

                mapSite = room.getSide(Direction.North);
                if(mapSite != null) {
                    gc.setFill(mapSite.getColor());
                    gc.fillRect(
                            (position.x + this.wallWidth) * this.roomSize,
                            this.height - (position.y + 1) * this.roomSize,
                            this.roomSize * (1 - 2 * this.wallWidth),
                            this.roomSize * this.wallWidth
                    );
                }

                mapSite = room.getSide(Direction.East);
                if(mapSite != null) {
                    gc.setFill(mapSite.getColor());
                    gc.fillRect(
                            (position.x + (1 - this.wallWidth)) * this.roomSize,
                            this.height - (position.y + 1 - this.wallWidth) * this.roomSize,
                            this.roomSize * this.wallWidth,
                            this.roomSize * (1 - 2 * this.wallWidth)
                    );
                }

                mapSite = room.getSide(Direction.South);
                if(mapSite != null) {
                    gc.setFill(mapSite.getColor());
                    gc.fillRect(
                            (position.x + this.wallWidth) * this.roomSize,
                            this.height - (position.y + this.wallWidth) * this.roomSize,
                            this.roomSize * (1 - 2 * this.wallWidth),
                            this.roomSize * this.wallWidth
                    );
                }

                mapSite = room.getSide(Direction.West);
                if(mapSite != null) {
                    gc.setFill(mapSite.getColor());
                    gc.fillRect(
                            position.x * this.roomSize,
                            this.height - (position.y + 1 - this.wallWidth) * this.roomSize,
                            this.roomSize * this.wallWidth,
                            this.roomSize * (1 - 2 * this.wallWidth)
                    );
                }
            }

            Vector2D playerPosition = this.player.getPosition();
            gc.setFill(Color.color(1, 0, 0));

            gc.fillOval(
                    (playerPosition.x + ((1 - this.playerWidth) / 2.0)) * this.roomSize,
                    this.height - (playerPosition.y + ((1 + this.playerWidth) / 2.0)) * this.roomSize,
                    this.playerWidth * this.roomSize,
                    this.playerWidth * this.roomSize
            );

        });
    }
}
