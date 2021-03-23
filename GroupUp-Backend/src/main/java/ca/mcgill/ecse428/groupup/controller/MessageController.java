package ca.mcgill.ecse428.groupup.controller;


import ca.mcgill.ecse428.groupup.dto.MessageDTO;
import ca.mcgill.ecse428.groupup.dto.ChatDTO;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.service.StudentService;
import ca.mcgill.ecse428.groupup.service.ChatService;
import ca.mcgill.ecse428.groupup.service.MessageService;
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


    //Date sendDate
    @PostMapping(value = {"/newmessage", "/newmessage/"})
    public MessageDTO createMessage(@RequestParam("sender") Student sender,
                                  @RequestParam("location") Chat location, @RequestParam("content") String content)
                                 {
        Message message = messageService.createMessage(sender, location, content);
        MessageDTO messageDTO = new MessageDTO(message.getId(), message.getSender(), message.getLocation(), message.getSendDate(), message.getContent());
        return messageDTO;
    }

    @PostMapping(value = {"/unsend/message", "/unsend/message/"})
    public MessageDTO unsendMessage(@RequestParam("id") long id,
    								@RequestParam("unsender") Student unsender){
        Message message = messageService.unsendMessage(id, unsender);
        MessageDTO messageDTO = new MessageDTO(message.getId(), message.getSender(), message.getLocation(), message.getSendDate(), message.getContent());
        return messageDTO;
    }

    @PostMapping(value = {"/newchat", "/newchat/"})
    public ChatDTO createChat(@RequestParam("members") List<Student> students) {
    	Chat chat = chatService.createChatWithoutName(students);
    	ChatDTO chatDTO = new ChatDTO(chat.getId(), chat.getMembers());
    	return chatDTO;
    }
    

    @GetMapping(value = {"/chats/{email}", "/chats/{email}/"})
    public List<ChatDTO> getChatsForStudent(@PathVariable("email") String email) {
    	Student student = studentService.getStudentByEmail(email);
        List<Chat> chats = messageService.getChatsByStudent(student);
        List<ChatDTO> chatDTOs = new ArrayList<>();
        for (Chat chat : chats){
            chatDTOs.add(new ChatDTO(chat.getId(), chat.getMembers()));
        }
        return chatDTOs;
    }
    
}
