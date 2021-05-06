package chat.service;

import chat.model.Message;
import chat.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messages;

    public MessageService(MessageRepository messages) {
        this.messages = messages;
    }

    public List<Message> getMessagesByRoom(int roomId) {
        return messages.findAllByRoom_Id(roomId);
    }

    public Message postMessage(Message message) {
        return messages.save(message);
    }
}
