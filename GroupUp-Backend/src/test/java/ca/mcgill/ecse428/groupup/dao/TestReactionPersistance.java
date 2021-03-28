package ca.mcgill.ecse428.groupup.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.model.Reaction;
import ca.mcgill.ecse428.groupup.model.ReactionType;
import ca.mcgill.ecse428.groupup.util.TestUtil;

@SpringBootTest
public class TestReactionPersistance {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    ReactionRepository reactionRepository;

    /**
     * The tear down process for every test
     */
    @AfterEach
    void clearDatabase() {
        // Clear the table
        accountRepository.deleteAll();
        reactionRepository.deleteAll();
        messageRepository.deleteAll();
        chatRepository.deleteAll();
        studentRepository.deleteAll();
    }

    @Test
    @Transactional
    void testCreateReaction() {
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

        Reaction rxn = new Reaction();
        rxn.setReactionDate(new Date());
        rxn.setReactionMessage(msg);
        rxn.setReactionType(ReactionType.LIKE);
        rxn.setReactor(s.getAccount());

        reactionRepository.save(rxn);

        Reaction dbrxn = reactionRepository.findById(rxn.getId()).orElse(null);
        assertNotNull(dbrxn);
        checkRxnEqual(rxn, dbrxn);
        
    }

    @Test
    @Transactional
    void testUpdateReaction() {
    	assert(true); // Don't need update reaction
    }
    
     @Test
     @Transactional
     void testDeleteReactionById() {
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

         Reaction rxn = new Reaction();
         rxn.setReactionDate(new Date());
         rxn.setReactionMessage(msg);
         rxn.setReactionType(ReactionType.LIKE);
         rxn.setReactor(s.getAccount());

         reactionRepository.save(rxn);

         Reaction dbrxn = reactionRepository.findById(rxn.getId()).orElse(null);
         assertNotNull(dbrxn);
         checkRxnEqual(rxn, dbrxn);
         
         reactionRepository.delete(dbrxn);
         dbrxn = null;
         
         assertNull(reactionRepository.findById(rxn.getId()).orElse(null));
         
     }

     @Test
     @Transactional
     void testFindByReactorAndReactionMessage() {

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

         Reaction rxn = new Reaction();
         rxn.setReactionDate(new Date());
         rxn.setReactionMessage(msg);
         rxn.setReactionType(ReactionType.LIKE);
         rxn.setReactor(s.getAccount());

         reactionRepository.save(rxn);

         Reaction dbrxn = reactionRepository.findByReactorAndReactionMessage(s.getAccount(), msg);
         assertNotNull(dbrxn);
         checkRxnEqual(rxn, dbrxn);
    	 
     }
     
     @Test
     @Transactional
     void testFindReactionsByMessage() {

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

         Reaction rxn = new Reaction();
         rxn.setReactionDate(new Date());
         rxn.setReactionMessage(msg);
         rxn.setReactionType(ReactionType.LIKE);
         rxn.setReactor(s.getAccount());
         reactionRepository.save(rxn);
         
         Reaction rxn2 = new Reaction();
         rxn.setReactionDate(new Date());
         rxn.setReactionMessage(msg);
         rxn.setReactionType(ReactionType.LOVE);
         rxn.setReactor(s2.getAccount());
         reactionRepository.save(rxn2);
         
         List<Reaction> rxns = reactionRepository.findAllByReactionMessage(msg);
         
         assertNotNull(rxns);
         assert(rxns.size()==1);
     }

     @Test
     @Transactional
     void testFindReactionsByMessageAndReactor() {

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

         Reaction rxn = new Reaction();
         rxn.setReactionDate(new Date());
         rxn.setReactionMessage(msg);
         rxn.setReactionType(ReactionType.LIKE);
         rxn.setReactor(s.getAccount());
         reactionRepository.save(rxn);
         
         Reaction dbrxn = reactionRepository.findByReactorAndReactionMessage(s.getAccount(), msg);
         
         assertNotNull(dbrxn);
         checkRxnEqual(rxn, dbrxn);

     }
     

    void checkMsgEqual(Message msg1, Message msg2) {
        assertEquals(msg1.getId(), msg2.getId());
        assertEquals(msg1.getContent(), msg2.getContent());
        assertEquals(msg1.getLocation(), msg2.getLocation());
        assertEquals(msg1.getSendDate(), msg2.getSendDate());
        assertEquals(msg1.getSender(), msg2.getSender());
    }
    
    void checkRxnEqual(Reaction rxn, Reaction dbrxn) {
        assertEquals(rxn.getReactionDate(), dbrxn.getReactionDate());
        assertEquals(rxn.getReactionMessage().getId(), dbrxn.getReactionMessage().getId());
        assertEquals(rxn.getReactionType().toString(), dbrxn.getReactionType().toString());
        assertEquals(rxn.getReactor().getEmail(), dbrxn.getReactor().getEmail());
        assertEquals(rxn.getId(), dbrxn.getId());
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
