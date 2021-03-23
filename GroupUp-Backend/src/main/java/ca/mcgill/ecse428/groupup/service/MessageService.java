package ca.mcgill.ecse428.groupup.service;

import java.util.Date;
import java.util.Random;
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
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.utility.Condition;


@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private ChatRepository chatRepository;
	
	@Transactional
	public Message createMessage(Student sender, Chat chat, String content) {
		String error = "";
		if(sender == null) {
			error+="Sender can not emtpy";
		}if(chat==null) {
			error+="Chat can not be empty";
		}if(content == null || content.length() == 0) {
			error+="Conent can not be empty";
		}
		if (error.length() != 0) {
            throw new IllegalArgumentException(error);
        }
		Random r = new Random();
		Message msg = new Message();
		msg.setSendDate(new Date(System.currentTimeMillis()));
		msg.setSender(sender);
		msg.setLocation(chat);
		msg.setContent(content);
		msg = messageRepository.save(msg);
		return msg;
	}
	
	
	/**
	 * Get message by chat, this method will get the message in descending order of time. 
	 * Page 1 will be the newest messages and last page will be the oldest messages.
	 * @param chat
	 * @param pageNo
	 * @return
	 */
	@Transactional
	public Page<Message> getMessagesByChat(Chat chat, int pageNo){
		Sort sort = Sort.by(Sort.Order.desc("SendDate"));
		Pageable pgb = PageRequest.of(pageNo, 20, sort); //20 message per page
		Page<Message> page = messageRepository.findByLocation(chat,pgb);
		return page;
	}
	
	@Transactional
	public boolean deleteMessage(long id) {
		if(!messageRepository.existsById(id))return false;
		messageRepository.deleteById(id);
		return true;
	}

	@Transactional
	public List<Chat> getChatsByStudent(Student student){
		List <Chat> chats = chatRepository.findAllByMembers(student);
		return chats;
	}


	@Transactional
    public Message unsendMessage(long id, Student unsender) {
        Message unsentMessage = messageRepository.findById(id).orElse(null);
        
        //is there a better way to check if two instances of student are the same?
        if (!unsentMessage.getSender().getAccount().getEmail().equals(unsender.getAccount().getEmail())) {
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
	
}
