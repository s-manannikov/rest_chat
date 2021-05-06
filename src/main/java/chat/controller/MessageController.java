package chat.controller;

import chat.model.Message;
import chat.model.Person;
import chat.model.Room;
import chat.service.MessageService;
import chat.service.PersonService;
import chat.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final PersonService persons;
    private final RoomService rooms;
    private final MessageService messages;

    public MessageController(final PersonService persons,
                             final RoomService rooms,
                             final MessageService messages) {
        this.persons = persons;
        this.rooms = rooms;
        this.messages = messages;
    }

    @GetMapping("/{roomId}")
    public List<Message> getMessages(@PathVariable int roomId) {
        return this.messages.getMessagesByRoom(roomId);
    }

    @PostMapping("/{roomId}/{personId}")
    public ResponseEntity<Message> postMessage(@RequestBody Message message,
                                               @PathVariable int personId,
                                               @PathVariable int roomId) {
        final Optional<Room> room = rooms.findRoomById(roomId);
        final Optional<Person> person = persons.findPersonById(personId);
        if (room.isEmpty() || person.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        message.setRoom(room.get());
        message.setPerson(person.get());
        return new ResponseEntity<>(
                this.messages.postMessage(message),
                HttpStatus.CREATED
        );
    }
}
