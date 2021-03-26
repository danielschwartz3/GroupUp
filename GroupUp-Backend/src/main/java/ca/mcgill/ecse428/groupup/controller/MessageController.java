package ca.mcgill.ecse428.groupup.controller;

import ca.mcgill.ecse428.groupup.dto.MessageDTO;
import ca.mcgill.ecse428.groupup.dto.StudentDTO;
import ca.mcgill.ecse428.groupup.dto.ChatDTO;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.model.Reaction;
import ca.mcgill.ecse428.groupup.service.StudentService;
import ca.mcgill.ecse428.groupup.service.ChatService;
import ca.mcgill.ecse428.groupup.service.MessageService;
import ca.mcgill.ecse428.groupup.service.ReactionService;
import ca.mcgill.ecse428.groupup.utility.DTOUtil;
// import ca.mcgill.ecse428.groupup.utility.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ReactionService reactionService;

    // Date sendDate
    @PostMapping(value = { "/newmessage", "/newmessage/" })
    public MessageDTO createMessage(@RequestParam("sender") Student sender, @RequestParam("chatID") long chatID,
            @RequestParam("content") String content) {
        Chat location = chatService.findChatByID(chatID);
        Message message = messageService.createMessage(sender, location, content);
        MessageDTO messageDTO = new MessageDTO(message.getId(), message.getSender(), message.getLocation(),
                message.getSendDate(), message.getContent(), null);
        return messageDTO;
    }

    @PostMapping(value = { "/unsend/message", "/unsend/message/" })
    public MessageDTO unsendMessage(@RequestParam("id") long id, @RequestParam("unsender") Student unsender) {
        Message message = messageService.unsendMessage(id, unsender);
        MessageDTO messageDTO = new MessageDTO(message.getId(), message.getSender(), message.getLocation(),
                message.getSendDate(), message.getContent(), null);
        return messageDTO;
    }

    @GetMapping(value = { "/chats/{email}", "/chats/{email}/" })
    public List<ChatDTO> getChatsForStudent(@PathVariable("email") String email) {
        Student student = studentService.getStudentByEmail(email);
        List<Chat> chats = messageService.getChatsByStudent(student);
        List<ChatDTO> chatDTOs = new ArrayList<>();
        for (Chat chat : chats) {
            chatDTOs.add(new ChatDTO(chat.getId(), chat.getMembers()));
        }
        return chatDTOs;
    }

    @PostMapping(value = { "/message/react", "/message/react/" })
    public MessageDTO reactToMessage(@RequestParam("email") String email, @RequestParam("reaction") String reaction,
            @RequestParam("messageId") long id) throws IllegalArgumentException {
        Student student = studentService.getStudentByEmail(email);
        Message message = messageService.getMessageById(id);
        reactionService.reactToMessage(reaction, student, message);
        List<Reaction> reactions = reactionService.getAllReactionsToMessage(message);
        MessageDTO messageDTO = new MessageDTO(message.getId(), message.getSender(), message.getLocation(),
                message.getSendDate(), message.getContent(), reactions);
        return messageDTO;
    }

    @PostMapping(value = { "/message/unreact", "/message/unreact/" })
    public MessageDTO unReactToMessage(@RequestParam("email") String email, @RequestParam("messageId") long id)
            throws IllegalArgumentException {
        Student student = studentService.getStudentByEmail(email);
        Message message = messageService.getMessageById(id);
        reactionService.unReactToMessage(student, message);
        List<Reaction> reactions = reactionService.getAllReactionsToMessage(message);
        MessageDTO messageDTO = new MessageDTO(message.getId(), message.getSender(), message.getLocation(),
                message.getSendDate(), message.getContent(), reactions);
        return messageDTO;
    }

    @GetMapping(value = { "/message/reactors", "/message/reactors/" })
    public List<StudentDTO> getReactorsToMessage(@RequestParam("messageId") long id) throws IllegalArgumentException {
        Message message = messageService.getMessageById(id);
        List<Student> reactors = reactionService.getReactorsToMessage(message);
        List<StudentDTO> reactorsDto = new ArrayList<>();
        for (Student student : reactors)
            reactorsDto.add(DTOUtil.convertToDTO(student));
        return reactorsDto;
    }
}
