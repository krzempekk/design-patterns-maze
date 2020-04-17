package pl.agh.edu.dp.labirynth.elements;

import javafx.scene.paint.Color;

public class Wall extends MapSite {
    protected Room room1;
    protected Room room2;

    public Wall(Room room1, Room room2){
        this.room1 = room1;
        this.room2 = room2;
    }

    public Wall(Room room) {
        this(room, null);
    }

    @Override
    public boolean enter(Room from){
        return false;
    }

    @Override
    public Color getColor() { return Color.color(0, 0, 0); }

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
