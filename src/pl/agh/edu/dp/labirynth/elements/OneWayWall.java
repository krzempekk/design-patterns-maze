package pl.agh.edu.dp.labirynth.elements;

import javafx.scene.paint.Color;

public class OneWayWall extends Wall {
    public OneWayWall(Room room1, Room room2) {
        super(room1, room2);
    }

    public OneWayWall(Room room) {
        super(room);
    }

    @Override
    public boolean enter(Room from) { return (from == room1 && room2 != null); }

    @Override
    public Color getColor() { return Color.DARKGRAY; }
}
