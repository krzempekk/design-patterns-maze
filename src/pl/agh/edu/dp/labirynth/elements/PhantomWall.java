package pl.agh.edu.dp.labirynth.elements;

public class PhantomWall extends Wall {
    public PhantomWall(Room room1, Room room2) {
        super(room1, room2);
    }

    public PhantomWall(Room room) {
        super(room);
    }

    @Override
    public boolean enter(Room from) {
        return room2 != null;
    }
}
