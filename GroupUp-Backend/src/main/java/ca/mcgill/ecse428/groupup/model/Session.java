package ca.mcgill.ecse428.groupup.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Session {
    @Id
	private UUID id = UUID.randomUUID();
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;
	
	
	public UUID getId() {
		return id;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
