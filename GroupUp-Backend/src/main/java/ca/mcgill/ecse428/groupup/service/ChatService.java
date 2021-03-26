package ca.mcgill.ecse428.groupup.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse428.groupup.dao.ChatRepository;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Chat;

@Service
public class ChatService {
  @Autowired
  ChatRepository chatRepository;

  @Transactional
  public Chat createChat(List<Account> members, String name) {
    if (members == null || members.size() < 2) {
      throw new IllegalArgumentException("Chat member can not be less than 2.");
    }
    Chat chat = new Chat();
    chat.setName(name);
    for (Account s : members)
      chat.getMembers().add(s);
    chat = chatRepository.save(chat);
    return chat;
  }

  @Transactional
  public Chat findChatByID(long id) throws IllegalArgumentException {
    Chat chat = chatRepository.findById(id).orElse(null);
    if (chat == null) {
      throw new IllegalArgumentException("Chat cannot be found.");
    }
    return chat;
  }
}
