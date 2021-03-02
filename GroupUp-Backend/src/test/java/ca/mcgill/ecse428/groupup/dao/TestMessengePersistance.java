package ca.mcgill.ecse428.groupup.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.util.TestUtil;

@SpringBootTest
public class TestMessengePersistance {
	
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	ChatRepository chatRepository;
	
	/**
	 * The tear down process for every test
	 */
	@AfterEach
	void clearDatabase() {
		//Clear the table
		accountRepository.deleteAll();
		messageRepository.deleteAll();
		studentRepository.deleteAll();
		chatRepository.deleteAll();
	}
	
	@Test
	@Transactional
	void testCreateMessage() {
		Random r = new Random();
		Student s = generateStudentAccount(r);
		s = studentRepository.save(s);
		Student s2 = generateStudentAccount(r);
		s2 = studentRepository.save(s2);
		Chat chat = new Chat();
		chat.getMembers().add(s2);
		chat.getMembers().add(s);
		chatRepository.save(chat);
		Message msg = TestUtil.generateMessage(r);
		msg = messageRepository.save(msg);
		assertNotNull(msg);
		Message dbMsg = messageRepository.findById(msg.getId()).orElse(null);
		assertEquals(msg,dbMsg);
	}
	
	@Test
	@Transactional
	void testUpdateMessage() {
		assertTrue(true);
	}
	
	@Test
	@Transactional
	void testDeleteMessage() {
		assertTrue(true);
	}
	
	@Test
	@Transactional
	void testGetMessageByChat() {
		assertTrue(true);
	}
	
	static Student generateStudentAccount(Random r) {
		Account ac = TestUtil.generateAccount(r);
		Student st = TestUtil.generateStudent(r);
		st.setAccount(ac);
		ac.setUserRole(st);
		return st;
	}
	
}
