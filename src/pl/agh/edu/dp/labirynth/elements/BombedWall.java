package pl.agh.edu.dp.labirynth.elements;

import javafx.scene.paint.Color;

public class BombedWall extends Wall {
    private boolean collapsed = false;

    public BombedWall(Room room1, Room room2) {
        super(room1, room2);
    }

    public BombedWall(Room room) {
        super(room);
    }

    @Override
    public Color getColor() { return this.collapsed ? Color.BLACK : Color.RED; }

    @Override
    public boolean enter(Room from) {
        Room room = room1 == from ? room2 : room1;
        if(room instanceof BombedRoom) {
            ((BombedRoom) room).explode();
        }
        this.collapsed = true;
        return false;
    }
}
