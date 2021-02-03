package ca.mcgill.ecse428.groupup.model;

public class Account{
    private UserRole role;
    public String user_name;
    public String student_name; //student Full-name
    public String user_institution;
    public String password;

    public void setUserRole(UserRole userRole){
        this.role = userRole;
    }

    public UserRole getUserRole(){
        return this.role;
    }

    public void setUsername(String username){
        this.user_name = username;
    }

    public String getUsername(){
        return user_name;
    }

    public void setFullName(String fullname){
        this.student_name = fullname;
    }

    public String getFullName(){
        return this.student_name;
    }

    public void setInstitution(String institution){
        this.user_institution = institution;
    }

    public String getInstitution(){
        return this.user_institution;
    }

    public void setPassword(String pass){
        this.password = pass;
    }

    public String getPassword(){
        return this.password;
    }
}