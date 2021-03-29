package ca.mcgill.ecse428.groupup.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Session {
  @Id
  private UUID id = UUID.randomUUID();

  @CreationTimestamp
  private Calendar loginTime;


  public UUID getId() {
    return id;
  }

  public Calendar getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Calendar loginTime) {
    this.loginTime = loginTime;
  }
}
