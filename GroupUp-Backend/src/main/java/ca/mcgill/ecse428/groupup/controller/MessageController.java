package ca.mcgill.ecse428.groupup.controller;

import ca.mcgill.ecse428.groupup.dto.MessageDTO;
import ca.mcgill.ecse428.groupup.dto.ReactionDTO;
import ca.mcgill.ecse428.groupup.dto.MessageRequestBody;
import ca.mcgill.ecse428.groupup.dto.AccountDTO;
import ca.mcgill.ecse428.groupup.dto.ChatDTO;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.service.AccountService;
import ca.mcgill.ecse428.groupup.service.ChatService;
import ca.mcgill.ecse428.groupup.service.MessageService;
import static ca.mcgill.ecse428.groupup.utility.DTOUtil.*;
import ca.mcgill.ecse428.groupup.model.Reaction;
import ca.mcgill.ecse428.groupup.service.StudentService;
import ca.mcgill.ecse428.groupup.service.ReactionService;
import ca.mcgill.ecse428.groupup.utility.DTOUtil;
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

  @Autowired
  private ReactionService reactionService;


  // Date sendDate
  @PostMapping(value = {"/newmessage", "/newmessage/"})
  public MessageDTO createMessage(@RequestBody MessageRequestBody body) {
    Account sender = accountService.getAccountByUserName(body.getUserName());
    Chat chat = chatService.findChatByID(body.getChatId());
    Message message = messageService.createMessage(sender, chat, body.getContent());
    MessageDTO messageDTO = convertToDTO(message);
    return messageDTO;
  }

  @PostMapping(value = {"/unsend/message", "/unsend/message/"})
  public MessageDTO unsendMessage(@RequestParam("id") long id,
      @RequestParam("unsender") String username) {
    Message message = messageService.unsendMessage(id, username);
    MessageDTO messageDTO = convertToDTO(message);
    return messageDTO;
  }

  @GetMapping(value = {"/chats/{chatid}/messages", "/chats/{chatid}/messages/"}, params = "before")
  public List<MessageDTO> getMessageByChatBeforeId(@PathVariable("chatid") long chatId,
      @RequestParam(value = "before") Long messageId) {
    Chat chat = chatService.findChatByID(chatId);
    List<Message> messages = messageService.getMessagesByChatBeforeMessageId(chat, messageId);
    return convertToMessageDTOs(messages);
  }

  @GetMapping(value = {"/chats/{chatid}/messages", "/chats/{chatid}/messages/"})
  public List<MessageDTO> getMessageByChatAfterId(@PathVariable("chatid") long chatId,
      @RequestParam(value = "after", required = false) Long messageId) {
    Chat chat = chatService.findChatByID(chatId);
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

  @PostMapping(value = {"/message/react", "/message/react/"})
  public MessageDTO reactToMessage(@RequestParam("email") String email,
      @RequestParam("reaction") String reaction, @RequestParam("messageId") long id)
      throws IllegalArgumentException {
    Account user = accountService.getAccountByID(email);
    Message message = messageService.getMessageById(id);
    reactionService.reactToMessage(reaction, user, message);
    List<Reaction> reactions  = reactionService.getAllReactionsToMessage(message);
    List<ReactionDTO> reactionsDTO = convertToReactionDTOs(reactions);
    MessageDTO messageDTO = convertToDTO(message);
    messageDTO.setReactions(reactionsDTO);
    return messageDTO;
  }

  @PostMapping(value = {"/message/unreact", "/message/unreact/"})
  public MessageDTO unReactToMessage(@RequestParam("email") String email,
      @RequestParam("messageId") long id) throws IllegalArgumentException {
    Account student = accountService.getAccountByID(email);
    Message message = messageService.getMessageById(id);
    reactionService.unReactToMessage(student, message);
    List<Reaction> reactions = reactionService.getAllReactionsToMessage(message);
    List<ReactionDTO> reactionsDTO = convertToReactionDTOs(reactions);
    MessageDTO messageDTO = convertToDTO(message);
    messageDTO.setReactions(reactionsDTO);
    return messageDTO;
  }

  @GetMapping(value = {"/message/reactors", "/message/reactors/"})
  public List<AccountDTO> getReactorsToMessage(@RequestParam("messageId") long id)
      throws IllegalArgumentException {
    Message message = messageService.getMessageById(id);
    List<Account> reactors = reactionService.getReactorsToMessage(message);
    List<AccountDTO> reactorsDto = new ArrayList<>();
    for (Account student : reactors)
      reactorsDto.add(convertToDTO(student));
    return reactorsDto;
  }
}
