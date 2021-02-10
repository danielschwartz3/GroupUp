package ca.mcgill.ecse428.groupup.dto;

public class AccountDTO {

    String userRole;
    String user_name;
    String name;
    String email;
    String user_institution;

    public AccountDTO() {};

    public AccountDTO(String userRole, String user_name, String name, String email, String user_institution) {
        this.userRole = userRole;
        this.user_name = user_name;
        this.name = name;
        this.email = email;
        this.user_institution = user_institution;
    }

    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return user_name;
    }
    public void setUserName(String user_name) {
        this.user_name = user_name;
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
        return user_institution;
    }
    public void setUserInstitution(String user_institution) {
        this.user_institution = user_institution;
    }






}
