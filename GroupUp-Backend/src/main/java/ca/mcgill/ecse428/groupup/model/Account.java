package ca.mcgill.ecse428.groupup.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

@Entity
public class Account{
    private UserRole userRole;
    private String userName;
    private String name; //student Full-name
    private String email;
    private String institution;
    private String password;
    private Session session;

    public void setUserRole(UserRole userRole){
        this.userRole = userRole;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    public UserRole getUserRole(){
        return this.userRole;
    }

    public void setUserName(String username){
        this.userName = username;
    }


    public String getUserName(){
        return userName;
    }

    public void setName(String fullname){
        this.name = fullname;
    }

    public String getName(){
        return this.name;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    @Id
    public String getEmail(){
        return this.email;
    }

    public void setInstitution(String institution){
        this.institution = institution;
    }

    public String getInstitution(){
        return this.institution;
    }

    public void setPassword(String pass){
        this.password = pass;
    }

    public String getPassword(){
        return this.password;
    }

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}