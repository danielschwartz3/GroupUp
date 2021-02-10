package ca.mcgill.ecse428.groupup.dto;

public class AccountDTO {

    String userRole;
    String user_name;
    String name;
    String user_email;
    String user_institution;

    public AccountDTO() {};

    public AccountDTO(String userRole, String user_name, String name, String user_email, String user_institution) {
        this.userRole = userRole;
        this.user_name = user_name;
        this.name = name;
        this.user_email = user_email;
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

    public String getUserEmail() {
        return user_email;
    }
    public void setUserEmail(String user_email) {
        this.user_email = user_email;
    }

    public String getUserInstitution() {
        return user_institution;
    }
    public void setUserInstitution(String user_institution) {
        this.user_institution = user_institution;
    }






}
