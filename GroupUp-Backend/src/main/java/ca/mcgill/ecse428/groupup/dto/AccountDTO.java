package ca.mcgill.ecse428.groupup.dto;

public class AccountDTO {

    String userRole;
    String userName;
    String name;
    String userEmail;
    String userInstitution;

    public AccountDTO() {};

    public AccountDTO(String userRole, String userName, String name, String userEmail, String userInstitution) {
        this.userRole = userRole;
        this.userName = userName;
        this.name = name;
        this.userEmail = userEmail;
        this.userInstitution = userInstitution;
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

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserInstitution() {
        return userInstitution;
    }
    public void setUserInstitution(String userInstitution) {
        this.userInstitution = userInstitution;
    }
}
