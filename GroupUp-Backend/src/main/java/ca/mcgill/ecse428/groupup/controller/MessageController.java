package ca.mcgill.ecse428.groupup.controller;


import ca.mcgill.ecse428.groupup.dto.MessageDTO;
import ca.mcgill.ecse428.groupup.dto.ChatDTO;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.service.StudentService;
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


    //Date sendDate
    @PostMapping(value = {"/newmessage", "/newmessage/"})
    public MessageDTO createCourse(@RequestParam("sender") Student sender,
                                  @RequestParam("location") Chat location, @RequestParam("content") String content)
                                 {
        Message message = messageService.createMessage(sender, location, content);
        MessageDTO messageDTO = new MessageDTO(message.getId(), message.getSender(), message.getLocation(), message.getSendDate(), message.getContent());
        return messageDTO;
    }

    @GetMapping(value = {"/chats/{id}", "/chats/{id}/"})
    public List<ChatDTO> getChatsForStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudentByID(id);
        List<Chat> chats = messageService.getChatsByStudent(student);
        List<ChatDTO> chatDTOs = new ArrayList<>();
        for (Chat chat : chats){
            chatDTOs.add(new ChatDTO(chat.getId(), chat.getMembers()));
        }
        return chatDTOs;
    }
    
}
