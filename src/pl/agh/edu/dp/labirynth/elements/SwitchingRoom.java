package pl.agh.edu.dp.labirynth.elements;

import javafx.scene.paint.Color;
import pl.agh.edu.dp.util.Direction;
import pl.agh.edu.dp.util.Vector2D;

public class SwitchingRoom extends Room {
    public SwitchingRoom(Vector2D position) {
        super(position);
    }

    @Override
    public boolean enter(Room from) {
        Direction fromDirection = Direction.fromVector(from.getPosition().subtract(this.getPosition()));
        System.out.println(fromDirection);

        for(Direction dir: Direction.values()) {
            Wall wall = this.getSide(dir);
            if(dir != fromDirection && wall != null && wall.getRoom2() != null) {
                Wall wallFrom = this.getSide(fromDirection);

                Room other = wall.getRoom1() == this ? wall.getRoom2() : wall.getRoom1();
                other.setSide(dir.opposite(), wallFrom);
                this.setSide(dir, wallFrom);
                wallFrom.setRoom1(other);
                wallFrom.setRoom2(this);

                from.setSide(fromDirection.opposite(), wall);
                this.setSide(fromDirection, wall);
                wall.setRoom1(from);
                wall.setRoom2(this);

                break;
            }
        }

        return true;
    }

    @Override
    public Color getColor() {
        return Color.DARKBLUE;
    }
}
