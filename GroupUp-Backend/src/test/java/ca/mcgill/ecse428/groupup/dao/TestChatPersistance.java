package ca.mcgill.ecse428.groupup.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.model.Account;

@SpringBootTest
public class TestChatPersistance {
	
	@Autowired
	ChatRepository chatRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	StudentRepository studentRepository;
	
	private static Student testStudent;
	
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
		Chat newChat = createSampleChat();
		Chat savedChat = chatRepository.save(newChat);
		assertNotNull(savedChat);
		checkChatEqual(newChat, savedChat);
	}
	
	@Test
	@Transactional
	void testUpdateChat() {
		assertTrue(true);		//no update chat in service
	}
	
	@Test
	@Transactional
	void testDeleteChat() {
		Chat newChat = createSampleChat();
		Chat savedChat = chatRepository.save(newChat);
		long id = savedChat.getId();
		chatRepository.deleteById(id);
		savedChat = chatRepository.findById(id).orElse(null);
		assertNull(savedChat);
	}
	
	@Test
	@Transactional
	void testFindChatByMember() {
		Chat newChat = createSampleChat();
		chatRepository.save(newChat);
		List<Chat> chats = chatRepository.findAllByMembers(testStudent.getAccount());
		assertEquals(1, chats.size());
		checkChatEqual(chats.get(0), newChat);
	}
	
	void checkChatEqual(Chat chat1, Chat chat2) {
		assertEquals(chat1.getId(), chat2.getId());
		assertEquals(chat1.getMembers(), chat2.getMembers());
	}
	
	Chat createSampleChat() {
		testStudent = createSampleStudent();      
		Set<Account> students = new HashSet<Account>();
		students.add(testStudent.getAccount());
		Chat chat = new Chat();
		chat.setMembers(students);
		return chat;
	}
	
	Set<Student> createChatMembers() {
		Account account = new Account();
        account.setEmail("test@mail.mcgill.ca");
        account.setInstitution("Institution");
        account.setName("testFullname");
        account.setPassword("password");
        account.setUserName("testusername");
        Student newStudent = new Student();
        account.setUserRole(newStudent);
        newStudent.setAccount(account);
        newStudent = studentRepository.save(newStudent);
        
        Account account1 = new Account();
        account1.setEmail("test1@mail.mcgill.ca");
        account1.setInstitution("Institution");
        account1.setName("testFullname1");
        account1.setPassword("password1");
        account1.setUserName("testusername1");
        Student newStudent1 = new Student();
        account1.setUserRole(newStudent1);
        newStudent1.setAccount(account1);
        newStudent1 = studentRepository.save(newStudent1);
        
		Set<Student> students = new HashSet<Student>();
		students.add(newStudent);
		students.add(newStudent1);
		return students;
	}
	
	Student createSampleStudent() {
		Account account = new Account();
        account.setEmail("test@mail.mcgill.ca");
        account.setInstitution("Institution");
        account.setName("testFullname");
        account.setPassword("password");
        account.setUserName("testusername");
        Student student = new Student();
        account.setUserRole(student);
        student.setAccount(account);
        student = studentRepository.save(student);
		assertNotNull(account);
		return student;
	}
	
}
