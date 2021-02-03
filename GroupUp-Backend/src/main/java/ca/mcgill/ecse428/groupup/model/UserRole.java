package ca.mcgill.ecse428.groupup.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
public abstract class UserRole{
    private int id;
    public Account acc;

    public void setAccount(Account account){
        this.acc = account;
    }

    @OneToOne(optional = false)
    @JsonBackReference
    public Account getAccount(){
        return this.acc;
    }

    public void setId(int id){
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){
        return this.id;
    }


}