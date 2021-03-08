package ca.mcgill.ecse428.groupup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.mcgill.ecse428.groupup.dto.ChatDTO;
import ca.mcgill.ecse428.groupup.dto.MessageDTO;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.service.ChatService;
import ca.mcgill.ecse428.groupup.service.MessageService;
import ca.mcgill.ecse428.groupup.service.StudentService;

public class ChatController {

	@Autowired
    private ChatService chatService;
	
	@Autowired
    private StudentService studentService;
	
	
    @PostMapping(value = {"/newchat", "/newchat/"}) // ************ NOTE: this creates a chat without a name
    public ChatDTO createChat(@RequestParam("members") List<String> emails) {
    	
    	List<Student> students = new ArrayList<Student>();
    	Student s;
    	
    	// Get the list of students from their emails
    	for (String email : emails) {
    		s = studentService.getStudentByEmail(email);
    		students.add(s);
    	}
    	
    	Chat chat = chatService.createChatWithoutName(students);
    	
    	ChatDTO cDTO= new ChatDTO(chat.getId(), chat.getMembers());
    	
    	return cDTO;
    }
    
    @PostMapping(value = {"/newchat/{name}", "/newchat/{name}/"})
    public ChatDTO createChat(@PathVariable("name") String name, @RequestParam("members") List<String> emails) {
    	
    	List<Student> students = new ArrayList<Student>();
    	Student s;
    	
    	// Get the list of students from their emails
    	for (String email : emails) {
    		s = studentService.getStudentByEmail(email);
    		students.add(s);
    	}
    	
    	Chat chat = chatService.createChat(name, students);
    	
    	ChatDTO cDTO= new ChatDTO(chat.getId(), chat.getName(), chat.getMembers());
    	
    	return cDTO;
    }
    
}
