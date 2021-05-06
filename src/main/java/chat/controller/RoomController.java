package chat.controller;

import chat.model.Person;
import chat.model.Room;
import chat.service.PersonService;
import chat.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final PersonService persons;
    private final RoomService rooms;

    public RoomController(final PersonService persons, final RoomService rooms) {
        this.persons = persons;
        this.rooms = rooms;
    }

    @GetMapping("/")
    public List<Room> findAllRooms() {
        return this.rooms.findAllRooms();
    }

    @PostMapping("/")
    public ResponseEntity<Room> create(@RequestBody Room room) {
        return new ResponseEntity<>(
                this.rooms.createRoom(room),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> delete(@PathVariable int roomId) {
        final Room room = new Room();
        room.setId(roomId);
        this.rooms.deleteRoom(room);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/enter/{roomId}/{personId}")
    public ResponseEntity<Room> enter(@PathVariable int roomId, @PathVariable int personId) {
        return action(roomId, personId, (room, person) -> {
            room.addPerson(person);
            return room;
        });
    }

    @PutMapping("/leave/{roomId}/{personId}")
    public ResponseEntity<Room> leave(@PathVariable int roomId, @PathVariable int personId) {
        return action(roomId, personId, (room, person) -> {
            room.getPerson().remove(person);
            return room;
        });
    }

    private ResponseEntity<Room> action(int roomId, int personId, BiFunction<Room, Person, Room> function) {
        final Optional<Room> room = rooms.findRoomById(roomId);
        final Optional<Person> person = persons.findPersonById(personId);
        if (room.isEmpty() || person.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        function.apply(room.get(), person.get());
        return new ResponseEntity<>(
                this.rooms.createRoom(room.get()),
                HttpStatus.OK
        );
    }
}
