package ca.mcgill.ecse428.groupup.dto;

public class UserDTO {
	String userName;
	String email;
	String userType;
	
	public UserDTO(String email, String userName,String type){
		this.userName = userName;
		this.email = email;
		this.userType = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
