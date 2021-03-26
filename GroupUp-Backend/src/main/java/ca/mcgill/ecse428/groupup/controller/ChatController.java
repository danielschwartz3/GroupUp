package ca.mcgill.ecse428.groupup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse428.groupup.dto.ChatDTO;
import ca.mcgill.ecse428.groupup.dto.ChatRequestBody;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.service.AccountService;
import ca.mcgill.ecse428.groupup.service.ChatService;
import ca.mcgill.ecse428.groupup.utility.DTOUtil;


@CrossOrigin(origins = "*")
@RestController
public class ChatController {

  @Autowired
  private ChatService chatService;

  @Autowired
  private AccountService accountService;

  @PostMapping(value = {"/newchat", "/newchat/"})
  public ChatDTO createChat(@RequestBody ChatRequestBody body) {
    List<Account> members = new ArrayList<Account>();
    // Get the list of students from their emails
    for (String username : body.getMembers()) {
      Account s = accountService.getAccountByUserName(username);
      members.add(s);
    }
    Chat chat = chatService.createChat(members, body.getName());
    ChatDTO cDTO = DTOUtil.convertToDTO(chat);
    return cDTO;
  }
}
