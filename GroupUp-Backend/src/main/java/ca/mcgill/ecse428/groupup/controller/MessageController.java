package ca.mcgill.ecse428.groupup.controller;


import ca.mcgill.ecse428.groupup.dto.MessageDTO;
import ca.mcgill.ecse428.groupup.dto.MessageRequestBody;
import ca.mcgill.ecse428.groupup.dto.ChatDTO;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.service.AccountService;
import ca.mcgill.ecse428.groupup.service.ChatService;
import ca.mcgill.ecse428.groupup.service.MessageService;
import static ca.mcgill.ecse428.groupup.utility.DTOUtil.*;
// import ca.mcgill.ecse428.groupup.utility.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class MessageController {
  @Autowired
  private MessageService messageService;
  @Autowired
  private AccountService accountService;
  @Autowired
  private ChatService chatService;
  

  // Date sendDate
  @PostMapping(value = {"/newmessage", "/newmessage/"})
  public MessageDTO createMessage(@RequestBody MessageRequestBody body) {
    Account sender = accountService.getAccountByUserName(body.getUserName());
    Chat chat = chatService.findChatById(body.getChatId());
    Message message = messageService.createMessage(sender, chat, body.getContent());
    MessageDTO messageDTO = convertToDTO(message);
    return messageDTO;
  }

  @GetMapping(value = {"/chats/{chatid}/messages", "/chats/{chatid}/messages/"}, params = "before")
  public List<MessageDTO> getMessageByChatBeforeId(@PathVariable("chatid") long chatId,
      @RequestParam(value = "before") Long messageId) {
    Chat chat = chatService.findChatById(chatId);
    List<Message> messages = messageService.getMessagesByChatBeforeMessageId(chat, messageId);
    return convertToMessageDTOs(messages);
  }

  @GetMapping(value = {"/chats/{chatid}/messages", "/chats/{chatid}/messages/"})
  public List<MessageDTO> getMessageByChatAfterId(@PathVariable("chatid") long chatId,
      @RequestParam(value = "after", required = false) Long messageId) {
    Chat chat = chatService.findChatById(chatId);
    List<Message> messages;
    if (messageId == null)
      messages = messageService.getLatestMessagesByChat(chat);
    else
      messages = messageService.getMessagesByChatAfterMessageId(chat, messageId);
    return convertToMessageDTOs(messages);
  }

  @GetMapping(value = {"/chats/{username}", "/chats/{username}/"})
  public List<ChatDTO> getChatsForMember(@PathVariable("username") String username) {
    Account user = accountService.getAccountByUserName(username);
    List<Chat> chats = messageService.getChatsByUser(user);
    List<ChatDTO> chatDTOs = new ArrayList<>();
    for (Chat chat : chats) {
      chatDTOs.add(convertToDTO(chat));
    }
    return chatDTOs;
  }

}
