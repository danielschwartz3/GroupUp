package ca.mcgill.ecse428.groupup.dto;

public class AccountDTO {

    String userRole;
    String userName;
    String name;
    String email;
    String institution;

    public AccountDTO() {};

    public AccountDTO(String userRole, String userName, String name, String email, String institution) {
        this.userRole = userRole;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.institution = institution;
    }

    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserInstitution() {
        return institution;
    }
    public void setUserInstitution(String institution) {
        this.institution = institution;
    }
}
