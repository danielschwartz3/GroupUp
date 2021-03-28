package ca.mcgill.ecse428.groupup.dto;

import java.util.Date;
import java.util.UUID;

import ca.mcgill.ecse428.groupup.model.Account;

public class SessionDTO {
  private UUID uuid;
  private Date creationTime;

  public SessionDTO() {};

  public SessionDTO(UUID uuid, Account user, Date creationTime) {
    this.setUuid(uuid);
    this.setCreationTime(creationTime);
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }


  public Date getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(Date creationTime) {
    this.creationTime = creationTime;
  }
}
