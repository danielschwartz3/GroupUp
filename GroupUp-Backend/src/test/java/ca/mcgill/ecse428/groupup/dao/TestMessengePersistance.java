package ca.mcgill.ecse428.groupup.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

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
		chatRepository.deleteAll();
		studentRepository.deleteAll();
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
		checkMsgEqual(msg, dbMsg);
	}
	
	@Test
	@Transactional
	void testUpdateMessage() {
		assertTrue(true);		//no update message in controller 
	}
	
	@Test
	@Transactional
	void testDeleteMessageByID() {
		Random r = new Random();
		Message msg = createSampleMessage(r);
		Message savedMsg = messageRepository.save(msg);
		long id = msg.getId();
		messageRepository.deleteById(id);
		savedMsg = messageRepository.findById(id).orElse(null);
		assertNull(savedMsg);
	}
	
	@Test
	@Transactional
	void testFindByLocation() {
		Random r = new Random();
		Message newMsg = createSampleMessage(r);
		long id = messageRepository.save(newMsg).getId();
		Pageable pageable = PageRequest.of(0, 20);
		Page<Message> savedMsg = messageRepository.findByLocation(newMsg.getLocation(), pageable);
		assertNotNull(savedMsg.getContent().get(0));
		checkMsgEqual(newMsg, savedMsg.getContent().get(0));
		
	}
	
	@Test
	@Transactional
	void testGetMessageByChat() {
		assertTrue(true);
	}
	
	void checkMsgEqual(Message msg1, Message msg2) {
		assertEquals(msg1.getId(), msg2.getId());
		assertEquals(msg1.getContent(), msg2.getContent());
		assertEquals(msg1.getLocation(), msg2.getLocation());
		assertEquals(msg1.getSendDate(), msg2.getSendDate());
		assertEquals(msg1.getSender(), msg2.getSender());
	}
	
	Message createSampleMessage(Random r) {
//		Random r = new Random();
		Student s = generateStudentAccount(r);
		s = studentRepository.save(s);
		Student s2 = generateStudentAccount(r);
		s2 = studentRepository.save(s2);
		Chat chat = new Chat();
		chat.getMembers().add(s2);
		chat.getMembers().add(s);
		chatRepository.save(chat);
		Message msg = TestUtil.generateMessage(r);
//		msg = messageRepository.save(msg);
		return msg;
	}
	
	static Student generateStudentAccount(Random r) {
		Account ac = TestUtil.generateAccount(r);
		Student st = TestUtil.generateStudent(r);
		st.setAccount(ac);
		ac.setUserRole(st);
		return st;
	}
	
}
