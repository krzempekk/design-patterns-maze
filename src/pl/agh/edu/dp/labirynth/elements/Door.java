package pl.agh.edu.dp.labirynth.elements;

import javafx.scene.paint.Color;

public class Door extends Wall {
    private Room room1;
    private Room room2;

    public Door(Room room1, Room room2){
        super(room1, room2);
    }

    @Override
    public boolean enter(Room from){
        return true;
    }

    @Override
    public Color getColor() { return Color.color(1, 1, 1); }

    public Room getRoom1() {
        return room1;
    }

    public void setRoom1(Room room1) {
        this.room1 = room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public void setRoom2(Room room2) {
        this.room2 = room2;
    }
}
