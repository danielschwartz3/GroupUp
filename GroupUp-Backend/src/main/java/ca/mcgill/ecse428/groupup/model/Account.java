package ca.mcgill.ecse428.groupup.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

@Entity
public class Account{
    private UserRole userRole;
    public String user_name;
    public String student_name; //student Full-name
    public String user_email;
    public String user_institution;
    public String password;

    public void setUserRole(UserRole userRole){
        this.userRole = userRole;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    public UserRole getUserRole(){
        return this.userRole;
    }

    public void setUsername(String username){
        this.user_name = username;
    }

    @Id
    public String getUsername(){
        return user_name;
    }

    public void setFullName(String fullname){
        this.student_name = fullname;
    }

    public String getFullName(){
        return this.student_name;
    }
    
    public void setEmail(String email){
        this.user_email = email;
    }

    public String geEmail(){
        return this.user_email;
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