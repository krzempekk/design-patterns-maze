package pl.agh.edu.dp.labirynth.elements;

import javafx.scene.paint.Color;
import pl.agh.edu.dp.util.Vector2D;

public class BombedRoom extends Room {
    private boolean collapsed = false;

    public BombedRoom(Vector2D position) {
        super(position);
    }

    @Override
    public boolean enter(Room from) {
        return !this.collapsed;
    }

    public void explode() {
        this.collapsed = true;
    }

    @Override
    public void leave() {
        this.explode();
    }

    @Override
    public Color getColor() { return this.collapsed ? Color.BLACK : Color.DARKRED; }
}
