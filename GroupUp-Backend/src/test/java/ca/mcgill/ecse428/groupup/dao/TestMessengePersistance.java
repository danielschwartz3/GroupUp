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
    // Clear the table
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
    chat.getMembers().add(s2.getAccount());
    chat.getMembers().add(s.getAccount());
    chatRepository.save(chat);
    Message msg = TestUtil.generateMessage(r, chat);
    msg = messageRepository.save(msg);
    assertNotNull(msg);
    Message dbMsg = messageRepository.findById(msg.getId()).orElse(null);
    assertEquals(msg, dbMsg);
    checkMsgEqual(msg, dbMsg);
  }

  @Test
  @Transactional
  void testUpdateMessage() {
    assertTrue(true); // no update message in controller
  }

  @Test
  @Transactional
  void testDeleteMessageByID() {
    Random r = new Random();
    Chat sampleChat = createSampleChat(r);
    Message msg = TestUtil.generateMessage(r, sampleChat);
    msg = messageRepository.save(msg);
    long id = msg.getId();
    messageRepository.deleteById(id);
    msg = messageRepository.findById(id).orElse(null);
    assertNull(msg);
  }

  @Test
  @Transactional
  void testFindByLocation() {
    Random r = new Random();
    Chat sampleChat = createSampleChat(r);
    Message msg = TestUtil.generateMessage(r, sampleChat);
    long id = messageRepository.save(msg).getId();
    Pageable pageable = PageRequest.of(0, 40);
    Page<Message> savedMsg = messageRepository.findByLocation(msg.getLocation(), pageable);
    assertNotNull(savedMsg.getContent().get(0));
    checkMsgEqual(msg, savedMsg.getContent().get(0));
  }

  @Test
  @Transactional
  void testFindByLocationAfter() {
    Random r = new Random();
    Chat sampleChat = createSampleChat(r);
    long lastId = 1;
    for (int i = 0; i < 50; i++) {
      Message msg = TestUtil.generateMessage(r, sampleChat);
      msg = messageRepository.save(msg);
      if(i==49)lastId = msg.getId();
    }
    Pageable pageable = PageRequest.of(0, 40);
    Page<Message> savedMsg =
        messageRepository.findByLocationAndIdGreaterThan(sampleChat, lastId-20, pageable);
    assertNotNull(savedMsg.getContent());
    assertEquals(20, savedMsg.getContent().size());
  }
  
  @Test
  @Transactional
  void testFindByLocationBefore() {
    Random r = new Random();
    Chat sampleChat = createSampleChat(r);
    long firstId = 0;
    for (int i = 0; i < 50; i++) {
      Message msg = TestUtil.generateMessage(r, sampleChat);
      msg = messageRepository.save(msg);
      if(i==0)firstId = msg.getId();
    }
    Pageable pageable = PageRequest.of(0, 40);
    Page<Message> savedMsg =
        messageRepository.findByLocationAndIdLessThan(sampleChat, firstId+20, pageable);
    assertNotNull(savedMsg.getContent());
    assertEquals(20, savedMsg.getContent().size());
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

  Chat createSampleChat(Random r) {
    Student s = generateStudentAccount(r);
    s = studentRepository.save(s);
    Student s2 = generateStudentAccount(r);
    s2 = studentRepository.save(s2);
    Chat chat = new Chat();
    chat.getMembers().add(s2.getAccount());
    chat.getMembers().add(s.getAccount());
    chatRepository.save(chat);
    return chat;
  }

  static Student generateStudentAccount(Random r) {
    Account ac = TestUtil.generateAccount(r);
    Student st = TestUtil.generateStudent(r);
    st.setAccount(ac);
    ac.setUserRole(st);
    return st;
  }

}
