package pl.agh.edu.dp.main;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.agh.edu.dp.labirynth.*;
import pl.agh.edu.dp.labirynth.factory.*;
import pl.agh.edu.dp.util.Vector2D;

public class Main extends Application {
    Maze maze;
    Player player;
    private final int width = 800;
    private final int height = 800;
    private final Vector2D lowerLeft = new Vector2D(0, 0);
    private final Vector2D upperRight = new Vector2D(19, 19);

    private boolean isSingleton() {
        MazeFactory factory = MazeFactory.getInstance();
        MazeFactory _factory = MazeFactory.getInstance();

        return factory == _factory;
    }

    public Main() {
        MazeGame mazeGame = new MazeGame(this.lowerLeft, this.upperRight);

        MazeFactory factory = MazeFactory.getInstance();

        this.maze = mazeGame.createMaze(factory);

        this.player = new Player(new Vector2D(0, 0), this.maze);
    }

    public static void main(String[] args) {
        new Main();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Maze game");

        BoardPane root = new BoardPane(this.maze, this.player, this.width, this.height, this.lowerLeft, this.upperRight);

        Scene primaryScene = new Scene(root, width, height);
        primaryStage.setScene(primaryScene);
        primaryStage.show();

        root.setKeyHandlers();
    }
}



