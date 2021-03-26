package ca.mcgill.ecse428.groupup.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "RoleType")
public abstract class UserRole{
	private int id;
    public Account account;

    public void setAccount(Account account){
        this.account = account;
    }

    @OneToOne(mappedBy="userRole", optional = false, cascade = CascadeType.ALL)
    @JsonBackReference
    public Account getAccount(){
        return this.account;
    }

    public void setId(int id){
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){
        return this.id;
    }
    
    public String userType() {
      return "General";
    }
}