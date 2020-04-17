package pl.agh.edu.dp.labirynth.elements;

import pl.agh.edu.dp.util.Direction;
import pl.agh.edu.dp.util.Vector2D;

import java.util.EnumMap;
import java.util.Map;

public class Room extends MapSite {
    private Vector2D position;
    private Map<Direction, Wall> sides;

    public Room(Vector2D position){
        this.position = position;
        this.sides = new EnumMap<>(Direction.class);
    }

    public Wall getSide(Direction direction){
        return this.sides.get(direction);
    }

    public void setSide(Direction direction, Wall wall){
        this.sides.put(direction, wall);
    }

    public Vector2D getPosition(){
        return this.position;
    }

    @Override
    public boolean enter(Room from){
        return true;
    }

    public void leave() {}
}
