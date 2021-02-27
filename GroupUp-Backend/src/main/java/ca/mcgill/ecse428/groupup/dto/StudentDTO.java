package ca.mcgill.ecse428.groupup.dto;

public class StudentDTO {
	String userName;
	String institution;
	String email;
	
	public StudentDTO(String email, String userName, String institution){
		this.userName = userName;
		this.institution = institution;
		this.email = email;
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

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}
}
