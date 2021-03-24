package ca.mcgill.ecse428.groupup.dto;

import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Reaction;
import java.util.Date;
import java.util.List;



public class MessageDTO {
    private long id;
    private Student sender;
    private Chat location;
    private Date sendDate;
	private String content;
    private List<Reaction> reactions;
    public MessageDTO() {};

    public MessageDTO(long id, Student sender, Chat location, Date sendDate, String content, List<Reaction> reactions) {
        this.id = id;
        this.sender = sender;
        this.sendDate = sendDate;
        this.content = content;
        this.reactions = reactions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getSender() {
        return sender;
    }

    public void setSender(Student sender) {
        this.sender = sender;
    }

    public Chat getLocation() {
        return location;
    }

    public void setLocation(Chat location) {
        this.location = location;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Reaction> getReactions(){
        return this.reactions;
    }
    public void setReactions(List<Reaction> reactions){
        this.reactions = reactions;
    }
    
}
