package ca.mcgill.ecse428.groupup.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.groupup.dao.MessageRepository;
import ca.mcgill.ecse428.groupup.dao.ChatRepository;
import ca.mcgill.ecse428.groupup.dao.ReactionRepository;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.utility.Condition;

@Service
public class MessageService {
  @Autowired
  private MessageRepository messageRepository;
  @Autowired
  private ChatRepository chatRepository;
  @Autowired
  private ReactionRepository reactionRepository;

  @Transactional
  public Message createMessage(Account sender, Chat chat, String content) {
    String error = "";
    if (sender == null) {
      error += "Sender can not emtpy";
    }
    if (chat == null) {
      error += "Chat can not be empty";
    }
    if (content == null || content.length() == 0) {
      error += "Conent can not be empty";
    }
//    for(Account account : chat.getMembers())
//      System.out.println(account);
//    System.out.println(sender);
    if (!chat.getMembers().contains(sender)) {
      error += "sender not authorized in this chat.";
    }
    if (error.length() != 0) {
      throw new IllegalArgumentException(error);
    }
    Message msg = new Message();
    msg.setSendDate(new Date(System.currentTimeMillis()));
    msg.setSender(sender);
    msg.setLocation(chat);
    msg.setContent(content);
    msg = messageRepository.save(msg);
    return msg;
  }


  /**
   * Get message by chat, this method will get the message in descending order of time. Page 1 will
   * be the newest messages and last page will be the oldest messages.
   * 
   * @param chat
   * @param pageNo
   * @return
   */
  @Transactional
  public List<Message> getMessagesByChatBeforeMessageId(Chat chat, long id) {
    Sort sort = Sort.by(Sort.Order.desc("id"));
    Pageable pgb = PageRequest.of(0, 40, sort); // 40 message per page
    Page<Message> pages = messageRepository.findByLocationAndIdLessThan(chat, id, pgb);
    List<Message> messages = new ArrayList<Message>();
    for (Message message : pages.getContent())
      messages.add(message);
    Collections.reverse(messages);
    return messages;
  }

  @Transactional
  public List<Message> getMessagesByChatAfterMessageId(Chat chat, long id) {
    Sort sort = Sort.by(Sort.Order.asc("id"));
    Pageable pgb = PageRequest.of(0, 40, sort); // 40 message per page
    Page<Message> messages = messageRepository.findByLocationAndIdGreaterThan(chat, id, pgb);
    return messages.getContent();
  }

  @Transactional
  public List<Message> getLatestMessagesByChat(Chat chat) {
    Sort sort = Sort.by(Sort.Order.desc("id"));
    Pageable pgb = PageRequest.of(0, 40, sort); // 40 message per page
    Page<Message> pages = messageRepository.findByLocation(chat, pgb);
    List<Message> messages = new ArrayList<Message>();
    for (Message message : pages.getContent())
      messages.add(message);
    Collections.reverse(messages);
    return messages;
  }

  @Transactional
  public boolean deleteMessage(long id) {
    if (!messageRepository.existsById(id))
      return false;
    Message msg = messageRepository.findById(id).orElse(null);
    deleteAllReactions(msg);
    messageRepository.deleteById(id);
    return true;
  }

  @Transactional
  public void deleteAllReactions(Message reactionMessage){
    if(reactionMessage == null){
      throw new IllegalArgumentException("Reaction cannot be deleted from null message");
    }
    reactionRepository.deleteAllByReactionMessage(reactionMessage);
  }

  @Transactional
  public List<Chat> getChatsByUser(Account user) {
    List<Chat> chats = chatRepository.findAllByMembers(user);
    return chats;
  }

  @Transactional
  public Message unsendMessage(long id, String userName) {
    Message unsentMessage = messageRepository.findById(id).orElse(null);
    // is there a better way to check if two instances of student are the same?
    if (!unsentMessage.getSender().getUserName().equals(userName)) {
      String errorMessage = "You do not have permission to unsend this message";
      throw new IllegalArgumentException(errorMessage);
    }
    if (!Condition.isValid(unsentMessage)) {
      throw new IllegalArgumentException("Message with id: " + id + " does not exist");
    }
    unsentMessage.setContent("This message has been unsent.");
    messageRepository.save(unsentMessage);
    return unsentMessage;
  }

  @Transactional
  public Message getMessageById(long id) {
    return messageRepository.findById(id).orElse(null);
  }
}
