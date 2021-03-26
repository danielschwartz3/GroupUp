package ca.mcgill.ecse428.groupup.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reaction {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long id;

  @Enumerated(EnumType.STRING)
  private ReactionType reactionType;

  @ManyToOne
  private Message reactionMessage;

  @ManyToOne
  private Account reactor;

  @Basic
  @Temporal(TemporalType.TIMESTAMP)
  private Date reactionDate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public ReactionType getReactionType() {
    return reactionType;
  }

  public void setReactionType(ReactionType reactionType) {
    this.reactionType = reactionType;
  }

  public Message getReactionMessage() {
    return reactionMessage;
  }

  public void setReactionMessage(Message reactionMessage) {
    this.reactionMessage = reactionMessage;
  }

  public Account getReactor() {
    return reactor;
  }

  public void setReactor(Account reactor) {
    this.reactor = reactor;
  }

  public Date getReactionDate() {
    return reactionDate;
  }

  public void setReactionDate(Date reactionDate) {
    this.reactionDate = reactionDate;
  }



}
