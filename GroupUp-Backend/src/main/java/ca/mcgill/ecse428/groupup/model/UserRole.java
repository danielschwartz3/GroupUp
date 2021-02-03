package ca.mcgill.ecse428.groupup.model;

public abstract class UserRole{
    private int id;
    public Account acc;

    public void setAccount(Account account){
        this.acc = account;
    }

    public Account getAccount(){
        return this.acc;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }


}