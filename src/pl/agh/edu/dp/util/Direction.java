package pl.agh.edu.dp.util;

import java.util.Random;

public enum Direction {
    North, East, South, West;

    public Direction opposite() {
        return Direction.values()[(this.ordinal() + 2) % 4];
    }

    public static Direction random() {
        int random = new Random().nextInt(Direction.values().length);
        return Direction.values()[random];
    }

    public static Direction fromVector(Vector2D vec) {
        if(North.toVector().equals(vec)) return North;
        if(East.toVector().equals(vec)) return East;
        if(South.toVector().equals(vec)) return South;
        if(West.toVector().equals(vec)) return West;
        return null;
    }

    public Vector2D toVector() {
        switch (this) {
            case North: return new Vector2D(0, 1);
            case East: return new Vector2D(1, 0);
            case South: return new Vector2D(0, -1);
            case West: return new Vector2D(-1, 0);
            default: return new Vector2D(0, 0);
        }
    }
}