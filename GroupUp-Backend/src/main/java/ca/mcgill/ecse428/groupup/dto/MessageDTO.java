package ca.mcgill.ecse428.groupup.dto;

import java.util.Date;
import java.util.List;



public class MessageDTO {
  private long id;
  private String sender;
  private long location;
  private Date sendDate;
  private String content;
  private List<ReactionDTO> reactions;

  public MessageDTO() {};

  public MessageDTO(long id, String sender, long location, Date sendDate, String content,
      List<ReactionDTO> reactions) {
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

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public long getLocation() {
    return location;
  }

  public void setLocation(long location) {
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

  public List<ReactionDTO> getReactions() {
    return this.reactions;
  }

  public void setReactions(List<ReactionDTO> reactions) {
    this.reactions = reactions;
  }

}
