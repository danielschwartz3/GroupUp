package ca.mcgill.ecse428.groupup.dto;

import ca.mcgill.ecse428.groupup.model.Student;
import java.util.Set;



public class ChatDTO {

    private long id;
    private Set<Student> members;
    private String name;
    
    public ChatDTO() {};

    public ChatDTO(long id, Set<Student> members) {
        this.id = id;
        this.members = members;
    }

    public ChatDTO(long id, String name, Set<Student> members) {
        this.id = id;
        this.members = members;
        this.name = name;
    }
    
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Set<Student> getMembers() {
		return members;
	}
	public void setMembers(Set<Student> members) {
		this.members = members;
	}
    
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
