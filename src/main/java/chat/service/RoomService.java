package chat.service;

import chat.model.Room;
import chat.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository rooms;

    public RoomService(RoomRepository rooms) {
        this.rooms = rooms;
    }

    public List<Room> findAllRooms() {
        return rooms.findAll();
    }

    public Room createRoom(Room room) {
        return rooms.save(room);
    }

    public void deleteRoom(Room room) {
        rooms.delete(room);
    }

    public Optional<Room> findRoomById(int roomId) {
        return rooms.findById(roomId);
    }
}
