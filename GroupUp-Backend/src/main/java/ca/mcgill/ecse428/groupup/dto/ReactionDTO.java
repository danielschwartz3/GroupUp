package ca.mcgill.ecse428.groupup.dto;

import java.util.Date;

public class ReactionDTO{
    private long id;
    private String reactor;
    private String reactionType;
    private Date reactionDate;

    public ReactionDTO(){};

    public ReactionDTO(long id, String reactor, String reactionType, Date reactionDate){
        this.id = id;
        this.reactor = reactor;
        this.reactionType = reactionType;
        this.reactionDate = reactionDate;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getReactor(){
        return reactor;
    }

    public void setReactor(String reactor){
        this.reactor = reactor;
    }

    public String getReactionType(){
        return reactionType;
    }

    public void setReactionType(String reactionType){
        this.reactionType = reactionType;
    }

    public Date getReactionDate() {
        return reactionDate;
      }
    
    public void setReactionDate(Date reactionDate) {
    this.reactionDate = reactionDate;
    }

}