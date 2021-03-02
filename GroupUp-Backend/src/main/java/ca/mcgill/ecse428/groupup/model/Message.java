package ca.mcgill.ecse428.groupup.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Message {
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
	private long id;
	
	@ManyToOne
	private Student sender;
	
	@ManyToOne
	private Chat location;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendDate;
	
	private String content;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Student getSender() {
		return sender;
	}
	
	public void setSender(Student sender) {
		this.sender = sender;
	}
	
	public Chat getLocation() {
		return location;
	}

	public void setLocation(Chat location) {
		this.location = location;
	}

	public Date getSendDate() {
		return sendDate;
	}
	
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
