package ca.mcgill.ecse428.groupup.dto;

import java.util.Set;



public class ChatDTO {

    private long id;
    private Set<String> members;
    private String chatName;
    public ChatDTO() {};

    public ChatDTO(long id, Set<String> members) {
        this.id = id;
        this.members = members;
    }

    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Set<String> getMembers() {
		return members;
	}
	public void setMembers(Set<String> members) {
		this.members = members;
	}

    public String getChatName() {
      return chatName;
    }
  
    public void setChatName(String chatName) {
      this.chatName = chatName;
    }
}
