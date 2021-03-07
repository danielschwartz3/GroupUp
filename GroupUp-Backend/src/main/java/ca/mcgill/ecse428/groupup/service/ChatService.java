package ca.mcgill.ecse428.groupup.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.groupup.dao.ChatRepository;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Student;

@Service
public class ChatService {
	@Autowired
	ChatRepository chatRepository;
	
	@Transactional
	public Chat createChat(List<Student> students) {
		if(students == null || students.size() < 2) {
			throw new IllegalArgumentException("Chat member can not be less than 2.");
		}
		Chat chat = new Chat();
		for(Student s : students)chat.getMembers().add(s);
		chat = chatRepository.save(chat);
		return chat;
	}
}
