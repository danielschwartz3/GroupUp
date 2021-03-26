package ca.mcgill.ecse428.groupup.model;
import javax.persistence.*;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends UserRole{
//attributes unique to this class added later
  @Override
  public String userType() {
    return "Admin";
  }
}