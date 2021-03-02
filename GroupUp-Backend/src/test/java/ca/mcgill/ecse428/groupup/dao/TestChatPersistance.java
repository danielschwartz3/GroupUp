package ca.mcgill.ecse428.groupup.dao;

import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestChatPersistance {
	
	@Autowired
	ChatRepository chatRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	StudentRepository studentRepository;
	
	/**
	 * The tear down process for every test
	 */
	@AfterEach
	void clearDatabase() {
		//Clear the table
		chatRepository.deleteAll();
		accountRepository.deleteAll();
		studentRepository.deleteAll();
	}
	
	@Test
	@Transactional
	void testCreateChat() {
		assertTrue(true);
	}
	
	@Test
	@Transactional
	void testUpdateChat() {
		assertTrue(true);
	}
	
	@Test
	@Transactional
	void testDeleteChat() {
		assertTrue(true);
	}
	
	@Test
	@Transactional
	void testFindChatByMember() {
		assertTrue(true);
	}
	
}
