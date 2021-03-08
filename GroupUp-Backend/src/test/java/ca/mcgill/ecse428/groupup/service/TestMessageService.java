package ca.mcgill.ecse428.groupup.service;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.groupup.dao.*;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.util.TestUtil;

@SpringBootTest
public class TestMessageService {
	//service dependency
	@Autowired
    private MessageService messageService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ChatService chatService;
	//dao dependency
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	ChatRepository chatRepository;
	
	@BeforeEach
	@AfterEach
	void clearDatabase() {
		//Clear the table
		messageRepository.deleteAll();
		chatRepository.deleteAll();
		studentRepository.deleteAll();
		accountRepository.deleteAll();
	}
	
	@Test
	void testGetMessagePage() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Random r = new Random();
		Student s = TestUtil.generateStudentAccount(r);
		s = studentService.createStudent(s);
		Student s2 = TestUtil.generateStudentAccount(r);
		s2 = studentService.createStudent(s2);
		Chat chat = chatService.createChatWithoutName(Arrays.asList(new Student[] {s,s2}));
		ArrayList<Message> history = new ArrayList<>(); 
		for(int i = 0 ; i < 50 ; i++) {
			String cnt = TestUtil.generateString(r, 20);
			Message msg = messageService.createMessage(s, chat, cnt);
			history.add(msg);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {}
		}
		Collections.sort(history,(a,b)->{return ((Message)b).getSendDate().compareTo(((Message)a).getSendDate());});
		List<Message> page = messageService.getMessagesByChat(chat, 0).getContent();
		int i = 0;
		for(Message msg: page) {
			assertEquals(sdf.format(history.get(i).getSendDate()),sdf.format(msg.getSendDate()));
			i++;
		}
	}
	

}
