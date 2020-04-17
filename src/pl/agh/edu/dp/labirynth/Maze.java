package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.elements.Room;
import pl.agh.edu.dp.util.Vector2D;

import java.util.HashMap;

public class Maze {
    private HashMap<Vector2D, Room> rooms;

    public Maze() {
        this.rooms = new HashMap<>();
    }

    public void addRoom(Room room){
        rooms.put(room.getPosition(), room);
    }

    public void setRooms(HashMap<Vector2D, Room> rooms) {
        this.rooms = rooms;
    }

    public int getRoomNumber()
    {
        return this.rooms.size();
    }

    public Room getRoom(Vector2D position) { return this.rooms.get(position); }

    public HashMap<Vector2D, Room> getRooms() { return this.rooms; }
}
