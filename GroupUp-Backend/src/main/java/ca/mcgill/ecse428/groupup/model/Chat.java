package ca.mcgill.ecse428.groupup.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Chat {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long id;

  @ManyToMany(fetch = FetchType.EAGER)
  @JsonManagedReference
  private Set<Account> members;

  String name;

  public Chat() {
    members = new HashSet<>();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Set<Account> getMembers() {
    return members;
  }

  public void setMembers(Set<Account> members) {
    this.members = members;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
