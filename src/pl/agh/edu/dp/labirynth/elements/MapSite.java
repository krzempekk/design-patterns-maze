package pl.agh.edu.dp.labirynth.elements;

import javafx.scene.paint.Color;

public abstract class MapSite {
    public abstract boolean enter(Room from);
    public Color getColor() { return Color.WHITE; }
}
