package ca.mcgill.ecse428.groupup.controller;


import ca.mcgill.ecse428.groupup.dto.MessageDTO;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
// import ca.mcgill.ecse428.groupup.service.CourseService;
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


    //Date sendDate
    @PostMapping(value = {"/newmessage", "/newmessage/"})
    public MessageDTO createCourse(@RequestParam("sender") Student sender,
                                  @RequestParam("location") Chat location, @RequestParam("content") String content)
                                 {
        Message message = messageService.createMessage(sender, location, content);
        MessageDTO messageDTO = new MessageDTO(message.getId(), message.getSender(), message.getLocation(), message.getSendDate(), message.getContent());
        return messageDTO;
    }
    
}
